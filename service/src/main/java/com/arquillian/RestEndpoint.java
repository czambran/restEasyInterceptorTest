package com.arquillian;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

public class RestEndpoint implements RestEndpointInterface {

    @Context
    private HttpServletRequest servletRequest;


    public String helloWorld() {
        return "Hello World";
    }

    public String getAuthorizationHeaderReceived() {
        return servletRequest.getHeader("Authorization");
    }

}
