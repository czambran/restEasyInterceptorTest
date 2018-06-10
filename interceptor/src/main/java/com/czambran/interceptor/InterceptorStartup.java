package com.czambran.interceptor;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InterceptorStartup {

    @Inject
    private HeaderAdder2Interceptor headerAdder2Interceptor;

    @PostConstruct
    private void init() {
        ResteasyProviderFactory.getInstance().getClientExecutionInterceptorRegistry().register(headerAdder2Interceptor);
    }
}
