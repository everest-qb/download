package tw.housemart.stock.download.domain.obj;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TypeLink {

	/**
	 * 檔案格式
	 */
	private String type;
	/**
	 * URL
	 */
	private String href;
}
