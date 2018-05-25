package tw.housemart.stock.download.domain;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;
import tw.housemart.stock.download.domain.obj.FileInfo;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgWebTest {
	

	@Autowired
	private OrgWeb dao;
	
	
	@Test
	public void testDownload() {
		//String r=dao.download("http://smart.tdcc.com.tw/opendata/getOD.ashx?id=1-5");
		//System.out.println(r);
	}

	@Test
	public void testContent() throws IOException {
		//FileInfo info=dao.findFileInfo("https://data.gov.tw/dataset/11452");
		//log.info(info);
	}
	
	@Test
	public void testSaveFile() throws Exception{
		//FileInfo info=dao.findFileInfo("https://data.gov.tw/dataset/11452");
		//dao.saveFile(info);
	}
}
