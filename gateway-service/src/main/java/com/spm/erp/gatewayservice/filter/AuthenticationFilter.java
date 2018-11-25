package com.spm.erp.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.spm.erp.gatewayservice.model.CustomResponse;
import com.spm.erp.gatewayservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

@Component
public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;

    @Resource(name = "authorizedUri")
    private HashMap<String, HashMap<String, List<String>>> authorizedUri;

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //Check if user token is valid or not
        if (!request.getRequestURI().contains("/signin")) {
            String bearerToken = request.getHeader("Authorization");

            ResponseEntity<CustomResponse> response = authService.validateToken(bearerToken);
            if (response.getBody().getMessage().equalsIgnoreCase("Unauthenticated")) {
                setUnauthorizedRequest(context);
            } else {
                //If token is valid check if user is authorized to access the URI
                List<String> roles = response.getBody().getRoles();
                boolean isUriAuth = false;
                for (String rec : roles) {
                    if (authorizedUri.containsKey(rec)) {
                        HashMap<String, List<String>> authUri = authorizedUri.get(rec);
                        if (authUri.containsKey(request.getMethod())) {
                            List<String> uriList = authUri.get(request.getMethod());
                            String requestUri = request.getRequestURI();
                            for (String validUri : uriList) {
                                UriTemplate template = new UriTemplate(validUri);
                                if (template.matches(requestUri)) {
                                    isUriAuth = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        setUnauthorizedRequest(context);
                    }
                }
                if (!isUriAuth) {
                    setUnauthorizedRequest(context);
                }
            }
        }
        return null;
    }

    private void setUnauthorizedRequest(RequestContext context) {
        context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        context.setSendZuulResponse(false);
    }
}
