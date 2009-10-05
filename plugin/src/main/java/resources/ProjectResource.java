package resources;

import java.util.*;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import representations.*;

import com.atlassian.jira.project.*;
import com.atlassian.jira.security.*;
import com.atlassian.jira.user.util.*;
import com.atlassian.plugins.rest.common.security.*;
import com.atlassian.sal.api.user.UserManager;
import com.opensymphony.user.*;

@Path("/projects")
public class ProjectResource extends BaseResource{

  public ProjectResource(UserManager userManager, PermissionManager permissionManager, UserUtil userUtil) {
    super(userManager,permissionManager,userUtil);
  }

  @GET
  @AnonymousAllowed
  @Produces (MediaType.APPLICATION_JSON)
  public Response getProjects(@Context HttpServletRequest request) {
    
    Collection<ProjectRepresentation> projectRepresentations = new LinkedList<ProjectRepresentation>();
    
    for (Project project : getProjectsForUser(getCurrentUser(request), request))
      projectRepresentations.add(new ProjectRepresentation(project));
    
    ProjectsRepresentation allProjects = new ProjectsRepresentation(projectRepresentations);
    return Response.ok(allProjects).build();
  }
  
  public Collection<Project> getProjectsForUser(User user, HttpServletRequest request){
    return (request.getParameter("owned") != null) ?
           permissionManager.getProjectObjects(Permissions.ADMINISTER, user) :
           permissionManager.getProjectObjects(Permissions.BROWSE, user);
  }
}
