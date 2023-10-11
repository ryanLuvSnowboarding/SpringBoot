package com.laioffer.onlineorder;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.sql.DataSource;
//We are using configuration class to add all dependencies
@Configuration
public class AppConfig {

    //place all dependencies under AppConfig class
    //加上@Bean之后会自动生成PasscodeEncoder object

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //cross-site request foergy. Purpose is that check valid request
                //The port is different between front-end and back-end. So need to disable to connect
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(HttpMethod.GET, "/", "/index.html", "/*.json", "/*.png", "/static/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login", "/logout", "/signup").permitAll()
                                .requestMatchers(HttpMethod.GET, "/restaurants/**", "/restaurant/**").permitAll()
                                .anyRequest().authenticated()
                )
                //To make some requests public
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                //do not redirect to authentication request
                .and()
                .formLogin()
                //specify login authentication method (could be replaced by .oauth2Login())
                //.formLogin() is a session-based login
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
                //will be redirected to login page if not specified
        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        //It is a JDBC UserDetailsManager from PostgreSQL
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setCreateUserSql("INSERT INTO customers (email, password, enabled) VALUES (?,?,?)");
        userDetailsManager.setCreateAuthoritySql("INSERT INTO authorities (email, authority) values (?,?)");
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email = ?");
        return userDetailsManager;
    }

    //The reason for PasswordEncoder is for protect user information.
    //Prevent staff from the company see customer password.
    //The type of encoding we use is bcrypt.
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

