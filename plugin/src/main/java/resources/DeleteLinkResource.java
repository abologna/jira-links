package resources;

import helpers.*;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.atlassian.jira.security.*;
import com.atlassian.jira.user.util.*;
import com.atlassian.plugins.rest.common.security.*;
import com.atlassian.sal.api.user.UserManager;

@Path("/link/remove")
public class DeleteLinkResource extends BaseResource{

  public DeleteLinkResource(UserManager userManager, PermissionManager permissionManager, UserUtil userUtil) {
    super(userManager, permissionManager, userUtil);
  }

  @POST @AnonymousAllowed
  @Produces(MediaType.APPLICATION_JSON)
  public Response removeLink(@Context HttpServletRequest request){
    if(!paramsArePresent(request,"project","url"))
      return Response.status(400).build();
    
    String project = request.getParameter("project");
    String url = request.getParameter("url");
    
    LinkHelper.removeLink(project, url);
    
    return Response.ok().build();
  }
  
}
