package com.javashitang.autoconfigure.sso;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author lilimin
 * @since 2020-05-30
 */
@ConditionalOnClass({LoginInterceptor.class})
@ConditionalOnWebApplication
@AutoConfigureBefore({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
@EnableConfigurationProperties({SsoConfiguration.JavashitangLoginInterceptorProperties.class})
@Import(SsoConfiguration.class)
public class SsoAutoConfiguration {
}
