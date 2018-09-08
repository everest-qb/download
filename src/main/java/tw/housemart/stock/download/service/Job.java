package tw.housemart.stock.download.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import tw.housemart.stock.download.domain.OrgWeb;
import tw.housemart.stock.download.domain.obj.FileInfo;
import tw.housemart.stock.download.domain.obj.TypeLink;

@Log4j2
@Service
public class Job {

	@Autowired
	@Qualifier("daily")
	private List<TypeLink> day;
	@Autowired
	@Qualifier("weekly")
	private List<TypeLink> week;
	@Autowired
	@Qualifier("monthly")
	private List<TypeLink> month;
	@Autowired
	@Qualifier("yearly")
	private List<TypeLink> yearly;
	@Autowired
	private OrgWeb dao;
	
	public void dayRule() {
		Calendar cal = Calendar.getInstance();
		if(!((cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)||(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY))) {
			runDay();
		}
	}
	
	public void runDay() {
		day.forEach(tl->{
			try {
				FileInfo info=dao.findFileInfo(tl.getHref());
				dao.saveFile(info);
			} catch (Exception e) {
				log.warn(e);				
			}
			log.info(tl);
		});
	}
	
	public void weekRule() {
		Calendar cal = Calendar.getInstance();
		if( cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			runWeek();
	}
	
	public void runWeek() {
		week.forEach(tl -> {
			try {
				FileInfo info = dao.findFileInfo(tl.getHref());
				dao.saveFile(info);
			} catch (Exception e) {
				log.warn(e);
			}
			log.info(tl);
		});

	}
	
	public void monthRule(){
		Calendar cal = Calendar.getInstance();
		if( cal.get(Calendar.DAY_OF_MONTH)==1)
			runMonth();
	}
	
	public void runMonth() {	
		month.forEach(tl->{
			try {
				FileInfo info=dao.findFileInfo(tl.getHref());
				dao.saveFile(info);
			} catch (Exception e) {
				log.warn(e);				
			}
			log.info(tl);
		});
	}
	
	public void runYear() {
		yearly.forEach(tl->{
			try {
				FileInfo info=dao.findFileInfo(tl.getHref());
				dao.saveFile(info);
			} catch (Exception e) {
				log.warn(e);				
			}
			log.info(tl);
		});
	}
}
