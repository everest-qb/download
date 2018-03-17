package tw.housemart.stock.download.domain.obj;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FileInfo {

	/**
	 * title
	 */
	private String title;
	/**
	 * 更新頻率
	 */
	private String cycle;
	
	/**
	 *  檔案格式+URL
	 */
	private List<TypeLink> list;
	
	
	public void addTypeLink(TypeLink tl) {
		if(list==null)list=new ArrayList<TypeLink>();
		list.add(tl);
	}
}
