package representations;

import java.util.*;

import javax.xml.bind.annotation.*;

import net.jcip.annotations.*;

@XmlRootElement @Immutable
@SuppressWarnings("unused")
public class ProjectsRepresentation {

  @XmlElement
  private Collection<ProjectRepresentation> projects;
  
  private ProjectsRepresentation(){}
  
  public ProjectsRepresentation(Collection<ProjectRepresentation> projects){
    this.projects =  new HashSet<ProjectRepresentation>();
    for(ProjectRepresentation p : projects)
      this.projects.add(p);
  }
}
