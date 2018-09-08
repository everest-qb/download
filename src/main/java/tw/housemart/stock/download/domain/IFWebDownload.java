package tw.housemart.stock.download.domain;

import java.io.File;
import java.net.URL;

public interface IFWebDownload {

	public void downloadFile(URL url,File file) throws Exception;
	
}
