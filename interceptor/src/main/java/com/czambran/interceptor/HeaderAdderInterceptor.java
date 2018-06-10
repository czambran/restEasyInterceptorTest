package com.czambran.interceptor;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

@ClientInterceptor
@Provider
@Precedence("HEADER_DECORATOR")
public class HeaderAdderInterceptor implements ClientExecutionInterceptor {

    private static Logger log = LoggerFactory.getLogger(HeaderAdderInterceptor.class);

    public HeaderAdderInterceptor() {
        log.info("Created");
    }

    @Override
    public ClientResponse execute(ClientExecutionContext clientExecutionContext) throws Exception {
        log.info("Intercepting");
        clientExecutionContext.getRequest().header("Authorization", "token");
        return clientExecutionContext.proceed();
    }
}
