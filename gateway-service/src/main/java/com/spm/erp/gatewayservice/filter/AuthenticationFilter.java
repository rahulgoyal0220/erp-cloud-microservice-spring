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

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

@Component
public class AuthenticationFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;

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
        if (!request.getRequestURI().contains("/signin")) {
            String bearerToken = request.getHeader("Authorization");

            ResponseEntity<CustomResponse> response = authService.validateToken(bearerToken);

            if (response.getBody().getMessage().equalsIgnoreCase("Unauthorized")) {
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                context.setSendZuulResponse(false);
            }
        }
        return null;
    }
}
