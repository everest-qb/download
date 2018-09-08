package tw.housemart.stock.download.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Log4j2
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Ok3Client implements IFWebDownload {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private static KeyStore ks; 
	private OkHttpClient client=null;

	@Override
	public void downloadFile(URL url, File file) throws Exception {
		init();
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {			
			if(response.isSuccessful()) {
				FileUtils.copyInputStreamToFile(response.body().byteStream(), file); 
			}else{
				System.out.println("FAIL:"+url.toString());
			}
		}
	}

	private void init() throws Exception {
		if(client==null) {
		SSLContext context = SSLContext.getInstance("TLS");  
        TrustManagerFactory tmf = TrustManagerFactory  
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        tmf.init(genKeyStore());  
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf  
                .getTrustManagers()[0];  
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);  
        context.init(null, new TrustManager[] { tm }, null);  
        SSLSocketFactory factory = context.getSocketFactory();  
        client=  new OkHttpClient.Builder().sslSocketFactory(factory, defaultTrustManager).build();
		}
	}
	
	
	private KeyStore genKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		if(ks==null) {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password="changeit".toCharArray();			
			
			try(InputStream fi=resourceLoader.getResource("classpath:cacerts").getInputStream()){
				ks.load(fi, password);
			}			
		}
		return ks;		
	}
	
    private static class SavingTrustManager implements X509TrustManager {  
    	  
        private final X509TrustManager tm;  
        private X509Certificate[] chain;  
  
        SavingTrustManager(X509TrustManager tm) {  
            this.tm = tm;  
        }  
  
        public X509Certificate[] getAcceptedIssuers() { 
        	return tm.getAcceptedIssuers();
            //throw new UnsupportedOperationException();  
        }  
  
        public void checkClientTrusted(X509Certificate[] chain, String authType)  
                throws CertificateException {  
            throw new UnsupportedOperationException();  
        }  
  
        public void checkServerTrusted(X509Certificate[] chain, String authType)  
                throws CertificateException {  
            this.chain = chain;  
            tm.checkServerTrusted(chain, authType);  
        }  
    }  
}
