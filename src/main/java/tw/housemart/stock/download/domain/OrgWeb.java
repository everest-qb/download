package tw.housemart.stock.download.domain;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/*import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import lombok.val;
import lombok.extern.log4j.Log4j2;
import tw.housemart.stock.download.domain.obj.FileInfo;
import tw.housemart.stock.download.domain.obj.TypeLink;

@Log4j2
@Component
public class OrgWeb {

	private SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
	
/*	private Client client = ClientBuilder.newClient();
	
	public String download(String url) {	
		
		WebTarget webTarget = client.target(url);
		Invocation.Builder builder=webTarget
				.request(MediaType.TEXT_PLAIN_TYPE)
				.accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);
		String response=builder.buildGet().invoke(String.class);
		return response;
	}*/
	
	public void saveFile(FileInfo info) {
		String today=f.format(new Date());
		File dir=new File("./data/"+info.getCycle()+"/"+info.getTitle()+"/"+today);
		if(!dir.exists())dir.mkdirs();
		int count=0;		
		for(TypeLink tl:info.getList()) {
			if("CSV".equals(tl.getType())) {
				try {
					File file = new File(dir, "f" + count + ".csv");
					if (!file.exists()) {
						FileUtils.copyURLToFile(new URL(tl.getHref()), file);
						count++;
					}
				} catch (MalformedURLException e) {
					log.error(e);					
				} catch (IOException e) {
					log.error(e);					
				}
				
			}
		}		
	}
	
	/**
	 * @param url content link
	 * @return file information
	 * @throws IOException
	 */
	public FileInfo findFileInfo(String url)throws IOException{
		final Map<String,String> returnValue=new HashMap<String,String>();
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla")
				.validateTLSCertificates(false)
				.get();

		FileInfo info=new FileInfo();
		
		String title=doc.select("h1.node-title").first().text();
		info.setTitle(title.replaceAll("\\p{Punct}", ""));
		log.trace(title);
		
		Element e=doc.select("div.node-content").first();
				
		e.children().forEach(ee->{
			if(ee.childNodeSize()==2){
				Element e1=ee.child(0);
				Element e2=ee.child(1);
				
				if(e1.text().startsWith("更新頻率")){					
					String cycle=e2.text();
					info.setCycle(cycle);
					log.trace("更新頻率:"+cycle);				
				}else if(e1.text().startsWith("資料資源")){
					log.trace("資料資源:");
					e2.select("a.ff-icon").forEach(a->{
						String type=a.text();
						String href=a.attr("href");
						TypeLink tl=TypeLink.builder().href(href).type(type).build();
						info.addTypeLink(tl);						
						log.trace(type+":"+href);
					});
				}
			}		
		});
		return info;
	}
}
