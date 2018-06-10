package com.arquillian;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public interface RestEndpointInterface {

    @GET
    String helloWorld();

    @Path("/getHeaders")
    @GET
    String getAuthorizationHeaderReceived();

}
