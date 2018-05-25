package tw.housemart.stock.download.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j2;
//import tw.housemart.stock.download.domain.OrgWebTest;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

	@Autowired
	private Job service;
	
	@Test
	public void testRunDay() {
		service.runDay();
	}

	@Test
	public void testRunWeek() {
		//service.runWeek();
	}
	
	@Test
	public void testRunMonth() {
		//service.runMonth();
	}
}
