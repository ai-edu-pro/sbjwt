
package org.zerock.sbjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/doAll").permitAll()
                .antMatchers("/sample/doMod").hasRole("MODERATOR");

//        http.exceptionHandling()
//            .authenticationEntryPoint((request, response, e) ->
//                {
//                    e.printStackTrace();
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    response.getWriter().write("{\"msg\":\"403\"}");
//                });


        http.formLogin();
    }



}
