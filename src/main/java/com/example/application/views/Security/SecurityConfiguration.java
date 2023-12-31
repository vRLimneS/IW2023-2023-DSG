package com.example.application.views.Security;

import com.example.application.views.login.LoginBasic;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    public static final String LOGOUT_URL = "/web/";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"));

        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/images/*.png"),
                        new AntPathRequestMatcher("/swagger-ui.html"),
                        new AntPathRequestMatcher("/swagger-ui/**"))
                .permitAll()

                .requestMatchers(
                        new AntPathRequestMatcher("/api/**"))
                .anonymous();


        super.configure(http);

        setLoginView(http, LoginBasic.class, LOGOUT_URL);
    }

     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/images/*.png")).permitAll();
        super.configure(http);
        setLoginView(http, LoginBasic.class, LOGOUT_URL);
    }


}