package helpers;

import junit.framework.*;
import representations.*;

public class LinkHelperTest extends TestCase{
  
  public void setUp(){}
  
  public void testGetLinks(){
    LinkHelper.LINKS_FILE = "./src/main/resources/links.txt";
    LinksRepresentation rep =  LinkHelper.getLinksForProject("ID3");
    assertEquals(1,rep.getLinks().size());
    
    LinksRepresentation rep2 =  LinkHelper.getLinksForProject("IDS");
    assertEquals(1,rep2.getLinks().size());
  }
}
