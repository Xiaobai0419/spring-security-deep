package com.xiaobai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**官网：
 * The first step is to create our Spring Security Java Configuration. The configuration creates
 * a Servlet Filter known as the springSecurityFilterChain which is responsible for all the security
 * (protecting the application URLs, validating submitted username and passwords, redirecting to the
 * log in form, etc) within your application. You can find the most basic example of a Spring Security
 * Java Configuration below:
 */
@EnableWebSecurity
@EnableWebMvc//这个注解用于启动SpringMVC一系列服务：转发Servlet,地址映射，处理器适配，视图解析，如果不配置，和Spring Security默认无关的一切地址映射、访问、转发和渲染均无效！包括自定义登录页面！SpringBoot不需要是因为只要它引入了Web依赖，会在启动时根据依赖自动配置SpringMVC模块！
@ComponentScan(basePackages = "com.xiaobai")
public class WebSecurityConfig
//        implements WebMvcConfigurer
{

    @Bean(name="securityMvc")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("xiaobai").password("654321").roles("ADMIN").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("dba").password("123456").roles("ADMIN","DBA").build());
        return manager;
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/signup", "/about").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()//所有请求需要认证，包括web项目首页，除了loginPage
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll();
        }
        /**官网：
         1 There are multiple children to the http.authorizeRequests() method each matcher is considered in the order they were declared.
         2 We specified multiple URL patterns that any user can access. Specifically, any user can access a request if the URL starts with "/resources/", equals "/signup", or equals "/about".
         3 Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
         4 Any URL that starts with "/db/" requires the user to have both "ROLE_ADMIN" and "ROLE_DBA". You will notice that since we are using the hasRole expression we do not need to specify the "ROLE_" prefix.
         5 Any URL that has not already been matched on only requires that the user be authenticated
         */
    }
}
