package com.snake.web.boot.security;

import com.snake.web.boot.security.jwt.JwtAuthenticationTokenFilter;
import com.snake.web.boot.security.jwt.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/10/23.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private FailureAuthorizeEntryPoint failureAuthorizeEntryPoint;

    @Autowired
    private DatabaseSecurityInterceptor databaseSecurityInterceptor;

    @Autowired
    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/all/**").permitAll()
                .antMatchers("/upload/").permitAll()
                .antMatchers("/businessuser/register").permitAll()
                .antMatchers("/v1/**").permitAll()
//                .antMatchers("/attachment/download/*").permitAll()
//                .antMatchers("/swagger-resources/configuration/ui").permitAll()
//                .antMatchers("/swagger-resources").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().authenticated().and()
                .headers().cacheControl()
        ;
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(databaseSecurityInterceptor, FilterSecurityInterceptor.class);

        http.exceptionHandling().authenticationEntryPoint(failureAuthorizeEntryPoint).accessDeniedHandler(restAccessDeniedHandler);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/favicon.ico", "/images/**", "/webjars/**", "/static/**").antMatchers(IGNORE_PATHS.toArray(new String[IGNORE_PATHS.size()]));
    }

    public static final List<String> IGNORE_PATHS;
    static {
        IGNORE_PATHS = new ArrayList<>();
        //swagger-ui
        IGNORE_PATHS.add("/v2/api-docs");
        IGNORE_PATHS.add("/swagger-ui.html");
        IGNORE_PATHS.add("/swagger-resources/**");
        IGNORE_PATHS.add("/webjars/**");
    }

}
