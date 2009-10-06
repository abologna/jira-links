package helpers;

import junit.framework.*;
import representations.*;

public class LinkReaderTest extends TestCase{
  
  public void setUp(){}
  
  public void testGetLinks(){
    LinkReader.LINKS_FILE = "./src/main/resources/links.txt";
    LinksRepresentation rep =  LinkReader.getLinksForProject("noproject");
    assertEquals(2,rep.getLinks().size());
  }
}
