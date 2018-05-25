package tw.housemart.stock.download.domain.obj;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TypeLinkTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetType() {	
		TypeLink tl=TypeLink.builder().type("type").href("href").build();
		assertTrue(tl.getType().equals("type"));
	}

	@Test
	public void testGetHref() {
		TypeLink tl=TypeLink.builder().type("type").href("href").build();
		assertTrue(tl.getHref().equals("href"));
	}

}
