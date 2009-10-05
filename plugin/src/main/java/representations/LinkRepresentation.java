package representations;

import javax.xml.bind.annotation.*;

import net.jcip.annotations.*;

@XmlRootElement @Immutable
@SuppressWarnings("unused")
public class LinkRepresentation {

  @XmlElement private String id;
  @XmlElement private String description;
  @XmlElement private String url;
  
  private LinkRepresentation(){}
  public LinkRepresentation(String id, String description, String url){
    this.id  = id;
    this.description = description;
    this.url = url;
  }
}
