package org.tech3.analytics.config;

import org.apache.http.HttpRequest;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class KibanaProxyServletConfiguration implements EnvironmentAware {


    private static final String ENV_PROXY_SOLR = "proxy.kibana.";

    @Bean
    public ServletRegistrationBean kibanaServletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(){
            @Override
            protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {

                String method = servletRequest.getMethod();
                log("The requested method = " + method);
                //if(Boolean.valueOf(propertyResolver.getProperty("is_auth_enabled"))) {
                    //proxyRequest.addHeader("Authorization", "Basic dXNlcjp1c2Vy");
                //}

                super.copyRequestHeaders(servletRequest, proxyRequest);
            }
        }, "/test/");
        servletRegistrationBean.addInitParameter("targetUri", "http://http-patch-vidhya.1d35.starter-us-east-1.openshiftapps.com/");
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, "true");
        return servletRegistrationBean;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}