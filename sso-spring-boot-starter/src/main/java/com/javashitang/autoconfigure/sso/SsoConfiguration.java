package com.javashitang.autoconfigure.sso;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lilimin
 * @since 2020-05-30
 */
@ConditionalOnProperty(value = "javashitang.sso.enable", havingValue = "true", matchIfMissing = true)
@EnableFeignClients(basePackages = "com.javashitang.autoconfigure.sso")
@ComponentScan("com.javashitang.autoconfigure.sso")
public class SsoConfiguration {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Bean
    protected HandlerInterceptor ssoInterceptor(JavashitangLoginInterceptorProperties properties) {
        return new MappedInterceptor(properties.getIncludePattern(), properties.getExcludePattern(), loginInterceptor);
    }

    @ConfigurationProperties(prefix = "javashitang.sso")
    public static class JavashitangLoginInterceptorProperties {

        public Set<String> includePattern = new HashSet<>();
        public Set<String> excludePattern = new HashSet<>();
        public boolean enable = true;

        public String[] getIncludePattern() {
            return includePattern.toArray(new String[]{});
        }

        public void setIncludePattern(Set<String> includePattern) {
            this.includePattern = includePattern;
        }

        public String[] getExcludePattern() {
            return excludePattern.toArray(new String[]{});
        }

        public void setExcludePattern(Set<String> excludePattern) {
            this.excludePattern = excludePattern;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
