package resources;

import javax.servlet.http.*;

import com.atlassian.jira.security.*;
import com.atlassian.jira.user.util.*;
import com.atlassian.sal.api.user.UserManager;
import com.opensymphony.user.*;

public abstract class BaseResource {
  
  protected UserManager userManager;
  protected UserUtil userUtil;
  protected PermissionManager permissionManager;

  public BaseResource(UserManager userManager, PermissionManager permissionManager, UserUtil userUtil) {
    this.userManager = userManager;
    this.permissionManager = permissionManager;
    this.userUtil = userUtil;
  }

  public User getCurrentUser(HttpServletRequest request) {
    String name = userManager.getRemoteUsername(request);
    return userUtil.getUser(name);
  }
  
  protected boolean paramIsPresent(String name, HttpServletRequest request){
    return request.getParameter(name) != null && request.getParameter(name).length() > 0;
  }
  
  protected boolean paramsArePresent(HttpServletRequest request,String... params){
    boolean ok = true;
    for(String param : params)
      ok = ok && paramIsPresent(param,request);
    return ok;
  }
}
