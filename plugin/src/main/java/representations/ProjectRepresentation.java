package representations;

import javax.xml.bind.annotation.*;

import com.atlassian.jira.project.*;

import resources.*;

import net.jcip.annotations.*;

@XmlRootElement
@Immutable
@SuppressWarnings("unused")
public class ProjectRepresentation {

  @XmlElement private Long id;
  @XmlElement private String key;
  @XmlElement private String name;
  @XmlElement private String projectUrl;
  @XmlElement private String projectLead;
  @XmlElement private String description;
  
  private ProjectRepresentation(){}
  
  public ProjectRepresentation(Project resource){
    this.id = resource.getId();
    this.key = resource.getKey();
    this.name = resource.getName();
    this.projectUrl = resource.getUrl();
    this.projectLead = resource.getLeadUserName();
    this.description = resource.getDescription();
  }
}
