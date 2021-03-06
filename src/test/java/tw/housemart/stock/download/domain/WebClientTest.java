package tw.housemart.stock.download.domain;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebClientTest {

	@Autowired
	private WebClient client;
	

	@Test
	public void testDownloadFile() throws Exception {
	   String url="https://www.cbc.gov.tw/public/data/OpenData/%E7%B6%93%E7%A0%94%E8%99%95/EFA1Y01.csv";
	   Path p=Files.createTempFile("download", ".tmp");
	   client.downloadFile(new URL(url), p.toFile());
	   
	   Files.lines(p).forEach(System.out::println);
	   
	   Files.deleteIfExists(p);
	   
	}
	
	

}
