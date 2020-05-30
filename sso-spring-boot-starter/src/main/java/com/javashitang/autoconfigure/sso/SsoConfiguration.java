package com.javashitang.autoconfigure.sso;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lilimin
 * @since 2020-05-30
 */
@ConditionalOnProperty(value = "javashitang.sso.enable", havingValue = "true", matchIfMissing = true)
public class SsoConfiguration {


    @ConfigurationProperties(prefix = "javashitang.sso")
    public static class JavashitangLoginInterceptorProperties {

        public Set<String> includePattern = new HashSet<>();
        public Set<String> encludePattern = new HashSet<>();

        public void setIncludePattern(Set<String> includePattern) {
            this.includePattern = includePattern;
        }

        public void setEncludePattern(Set<String> encludePattern) {
            this.encludePattern = encludePattern;
        }
    }
}
