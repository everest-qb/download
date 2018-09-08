package tw.housemart.stock.download;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tw.housemart.stock.download.service.Job;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	//System.setProperty("javax.net.ssl.trustStore", "jssecacerts"); 
    	ConfigurableApplicationContext context=SpringApplication.run(App.class, args);
    	Job service=context.getBean(Job.class);
    	service.dayRule();
    	service.weekRule();
    	service.monthRule();
    	
    }
}
