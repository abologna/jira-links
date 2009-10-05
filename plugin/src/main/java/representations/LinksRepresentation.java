package representations;

import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;

import net.jcip.annotations.*;

import com.sun.xml.txw2.annotation.*;

@XmlRootElement @Immutable
@SuppressWarnings("unused")
public class LinksRepresentation {

  @XmlElement
  private Collection<LinkRepresentation> links;
  
  private LinksRepresentation(){}
  
  public LinksRepresentation(Collection<LinkRepresentation> links){
    this.links = new HashSet<LinkRepresentation>();
    for(LinkRepresentation link : links)
      this.links.add(link);
  }
  public Collection<LinkRepresentation> getLinks(){
    return links;
  }
}
