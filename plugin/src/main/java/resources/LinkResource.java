package resources;

import helpers.*;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import representations.*;

import com.atlassian.jira.config.properties.*;
import com.atlassian.jira.security.*;
import com.atlassian.jira.user.util.*;
import com.atlassian.plugins.rest.common.security.*;
import com.atlassian.sal.api.user.UserManager;

@Path("/links")
public class LinkResource extends BaseResource{

  private ApplicationProperties props;
  public LinkResource(UserManager userManager, PermissionManager permissionManager, UserUtil userUtil, ApplicationProperties props) {
    super(userManager, permissionManager, userUtil);
    this.props = props;
  }
  
  @GET
  @AnonymousAllowed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLinksForProject(@Context HttpServletRequest request){
    
    System.out.println(props.getString("jira.home"));
    if(!paramIsPresent("project",request))
      return Response.status(400).build();
    
    String projectId = request.getParameter("project");
    LinksRepresentation links = LinkReader.getLinksForProject(projectId);
    
    return Response.ok(links).build();
  }
}
