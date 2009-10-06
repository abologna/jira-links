package resources;

import helpers.*;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import representations.*;

import com.atlassian.jira.security.*;
import com.atlassian.jira.user.util.*;
import com.atlassian.plugins.rest.common.security.*;
import com.atlassian.sal.api.user.UserManager;

@Path("/links")
public class LinkResource extends BaseResource{

  public LinkResource(UserManager userManager, PermissionManager permissionManager, UserUtil userUtil) {
    super(userManager, permissionManager, userUtil);
  }
  
  @GET
  @AnonymousAllowed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLinksForProject(@Context HttpServletRequest request){
    
    if(!paramIsPresent("project",request))
      return Response.status(400).build();
    
    String projectId = request.getParameter("project");
    LinksRepresentation links = LinkHelper.getLinksForProject(projectId);
    
    return Response.ok(links).build();
  }
  
  @POST
  @AnonymousAllowed
  public Response createNewLink(@Context HttpServletRequest request){
    if(!paramsArePresent(request,"url","description","project"))
        return Response.status(400).build();
    
    String project = request.getParameter("project");
    String url = request.getParameter("url");
    String description = request.getParameter("description");
    
    LinkHelper.createLink(project,url,description);
   
    return Response.status(201).build();
  }
}
