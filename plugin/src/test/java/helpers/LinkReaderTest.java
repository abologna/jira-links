package helpers;

import junit.framework.*;
import representations.*;

public class LinkReaderTest extends TestCase{
  
  public void setUp(){}
  
  public void testGetLinks(){
    LinksRepresentation rep =  LinkReader.getLinksForProject("noproject");
    assertEquals(2,rep.getLinks().size());
  }
}
