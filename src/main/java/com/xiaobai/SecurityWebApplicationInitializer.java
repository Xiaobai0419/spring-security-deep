package com.xiaobai;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**官网：
 * If we were using Spring elsewhere in our application we probably already had a WebApplicationInitializer
 * that is loading our Spring Configuration. If we use the previous configuration we would get an error.
 * Instead, we should register Spring Security with the existing ApplicationContext. For example,
 * if we were using Spring MVC our SecurityWebApplicationInitializer would look something like the following:
 */
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

//    public SecurityWebApplicationInitializer() {
//        super(WebSecurityConfig.class);
//    }
}
/**（接上）官网：
 This would simply only register the springSecurityFilterChain Filter for every URL in your application.
 After that we would ensure that WebSecurityConfig was loaded in our existing ApplicationInitializer.
 For example, if we were using Spring MVC it would be added in the getRootConfigClasses()
 */