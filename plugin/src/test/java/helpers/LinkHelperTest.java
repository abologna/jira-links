package helpers;

import junit.framework.*;
import representations.*;

public class LinkHelperTest extends TestCase{
  
  public void setUp(){
    LinkHelper.LINKS_FILE = "./src/main/resources/links.txt";
    LinkHelper.TEMP_FILE = "./src/main/resources/temp.txt";
    LinkHelper.createLink("TST", "http://link.com", "test links");
    LinkHelper.createLink("TST", "http://link.com", "test links");
    LinkHelper.createLink("TST", "http://link.com", "test links");
  }
  
  public void testGetLinks(){
    LinksRepresentation rep =  LinkHelper.getLinksForProject("TST");
    assertEquals(3,rep.getLinks().size());
  }
  
  public void testRemoveLink(){
    LinkHelper.createLink("NEW", "http://sarasa.com", "test");
    
    assertEquals(1, LinkHelper.getLinksForProject("NEW").getLinks().size());
    
    LinkHelper.removeLink("NEW", "http://sarasa.com");
    
    assertEquals(0,LinkHelper.getLinksForProject("NEW").getLinks().size());
  }
  
  public void tearDown(){
    LinkHelper.removeLink("TST", "http://link.com");
  }
}
