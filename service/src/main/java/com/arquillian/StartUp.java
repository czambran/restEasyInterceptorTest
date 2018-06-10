package com.arquillian;

import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.client.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartUp {

    private static Logger log = LoggerFactory.getLogger(StartUp.class);

    @PostConstruct
    private void init() {
        try {
            RestEndpointInterface restEasyClient = ProxyFactory.create(RestEndpointInterface.class, "http://localhost:8080/arquillian");
            log.info("The authorization header included in the request was: {}", restEasyClient.getAuthorizationHeaderReceived());
        } catch (ClientResponseFailure e) {
            log.error("Couldn't issue rest call on startup EJB");
        }
    }
}
