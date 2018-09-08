package tw.housemart.stock.download.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WebClient implements IFWebDownload{
	
	private static KeyStore ks; 
	private static HttpClientConnectionManager cm;
	
	public void downloadFile(URL url,File file) throws Exception{
		@Cleanup
		CloseableHttpClient httpclient = HttpClients.custom()
		        .setConnectionManager(mg())
		        .build();	
	
		
		HttpGet httpget = new HttpGet(url.toURI());							
		httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		httpget.addHeader("Host", url.getHost());
		httpget.addHeader("Accept-Language", "zh-TW,zh;q=0.9,zh-CN;q=0.8,en-US;q=0.7,en;q=0.6");
		httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");						
		httpget.addHeader("Connection", "close");	//disable keep alive
		HttpResponse response = httpclient.execute(httpget);												
        HttpEntity entity = response.getEntity();
        if (entity != null) {			               
            InputStream inputStream = entity.getContent();
            FileUtils.copyInputStreamToFile(inputStream, file); 
        }
	}
	
	private HttpClientConnectionManager mg() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		if(cm==null) {
			ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
			SSLContext sslContext = SSLContexts.custom()
					.loadKeyMaterial(genKeyStore(), "changeit".toCharArray())		       
			        .build();
			LayeredConnectionSocketFactory sslsf =new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
			        .register("http", plainsf)
			        .register("https", sslsf)
			        .build();
			cm = new PoolingHttpClientConnectionManager(r);
		}		
		return cm;
	}
	
	private KeyStore genKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		if(ks==null) {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password="changeit".toCharArray();
			File f=ResourceUtils.getFile("classpath:cacerts");
			try(FileInputStream fi=new FileInputStream(f)){
				ks.load(fi, password);
			}			
		}
		return ks;		
	}
}
