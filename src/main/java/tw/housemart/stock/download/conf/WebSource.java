package tw.housemart.stock.download.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tw.housemart.stock.download.domain.obj.TypeLink;

@Configuration
public class WebSource {

	@Bean
	public List<TypeLink> daily(){
		List<TypeLink> returnValue=new ArrayList<>();
		returnValue.add(TypeLink.builder().type("每日外幣參考匯率").href("https://data.gov.tw/dataset/11339").build());
		returnValue.add(TypeLink.builder().type("上市公司每日重大訊息").href("https://data.gov.tw/dataset/18415").build());
		returnValue.add(TypeLink.builder().type("上櫃公司每日重大訊息").href("https://data.gov.tw/dataset/18418").build());
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-選擇權買賣權分計-依日期").href("https://data.gov.tw/dataset/11600").build());
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-區分各選擇權契約-依日期").href("https://data.gov.tw/dataset/11598").build());
		returnValue.add(TypeLink.builder().type("每日第一上市外國股票成交量值").href("https://data.gov.tw/dataset/11759").build());
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-區分各期貨契約-依日期").href("https://data.gov.tw/dataset/11596").build());	
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-區分期貨與選擇權二類-依日期").href("https://data.gov.tw/dataset/11594").build());
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-總表-依日期").href("https://data.gov.tw/dataset/11592").build());
		returnValue.add(TypeLink.builder().type("每日市場成交資訊").href("https://data.gov.tw/dataset/11672").build());
		returnValue.add(TypeLink.builder().type("每日上市上櫃跨市場成交資訊").href("https://data.gov.tw/dataset/11668").build());
		returnValue.add(TypeLink.builder().type("每日成交量前二十名證券").href("https://data.gov.tw/dataset/11673").build());
		returnValue.add(TypeLink.builder().type("選擇權每日Delta值").href("https://data.gov.tw/dataset/11321").build());
		returnValue.add(TypeLink.builder().type("選擇權每日交易行情").href("https://data.gov.tw/dataset/11320").build());
		returnValue.add(TypeLink.builder().type("期貨每日交易行情").href("https://data.gov.tw/dataset/11319").build());
		returnValue.add(TypeLink.builder().type("每日收盤行情-大盤統計資訊").href("https://data.gov.tw/dataset/11555").build());
		returnValue.add(TypeLink.builder().type("證券投資信託基金每日淨值").href("https://data.gov.tw/dataset/11109").build());
		returnValue.add(TypeLink.builder().type("上櫃認購(售)權證每日成交資料檔").href("https://data.gov.tw/dataset/52795").build());
		returnValue.add(TypeLink.builder().type("每日選擇權每筆成交資料").href("https://data.gov.tw/dataset/20671").build());
		returnValue.add(TypeLink.builder().type("個股日本益比、殖利率及股價淨值比").href("https://data.gov.tw/dataset/11764").build());
		returnValue.add(TypeLink.builder().type("個股日成交資訊").href("https://data.gov.tw/dataset/11549").build());
		returnValue.add(TypeLink.builder().type("上櫃股票三大法人買賣明細資訊").href("https://data.gov.tw/dataset/11856").build());
		returnValue.add(TypeLink.builder().type("上櫃股票三大法人買賣金額").href("https://data.gov.tw/dataset/11743").build());
		returnValue.add(TypeLink.builder().type("資及陸資持股前 20 名").href("https://data.gov.tw/dataset/11656").build());
		returnValue.add(TypeLink.builder().type("外資及陸資投資類股持股比率").href("https://data.gov.tw/dataset/11655").build());
		//returnValue.add(TypeLink.builder().type("").href("").build());
		return returnValue;
	}
	
	@Bean
	public List<TypeLink> weekly(){
		List<TypeLink> returnValue=new ArrayList<>();
		returnValue.add(TypeLink.builder().type("集保戶股權分散表").href("https://data.gov.tw/dataset/11452").build());
		returnValue.add(TypeLink.builder().type("期貨市場三大法人-總表").href("https://data.gov.tw/dataset/11593").build());
		return returnValue;
	}
	
	@Bean
	public List<TypeLink> monthly(){
		List<TypeLink> returnValue=new ArrayList<>();
		returnValue.add(TypeLink.builder().type("個股月成交資訊").href("https://data.gov.tw/dataset/11550").build());
		returnValue.add(TypeLink.builder().type("上櫃公司每月營業收入").href("https://data.gov.tw/dataset/56510").build());
		returnValue.add(TypeLink.builder().type("上市公司每月營業收入").href("https://data.gov.tw/dataset/18420").build());
		returnValue.add(TypeLink.builder().type("重要金融指標").href("https://data.gov.tw/dataset/10746").build());
		//returnValue.add(TypeLink.builder().type("").href("").build());
		return returnValue;
	}
	
	@Bean
	public List<TypeLink> season(){
		List<TypeLink> returnValue=new ArrayList<>();
		//returnValue.add(TypeLink.builder().type("").href("").build());
		return returnValue;
	}
	
	@Bean
	public List<TypeLink> yearly(){
		List<TypeLink> returnValue=new ArrayList<>();
		returnValue.add(TypeLink.builder().type("個股年成交資訊").href("https://data.gov.tw/dataset/11551").build());
		returnValue.add(TypeLink.builder().type("我國金融暨經濟發展重要指標").href("https://data.gov.tw/dataset/14269").build());
		//returnValue.add(TypeLink.builder().type("").href("").build());
		return returnValue;
	}
}
