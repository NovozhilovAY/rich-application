package com.example.richapplication.security;

import com.example.richapplication.api.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    private final AuthEntryPointJwt unauthorizedHandler;

    private final JwtUtils jwtUtils;

    public SecurityConfig(UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/img/{fileName}").permitAll()
                .antMatchers("/api/registration").permitAll()
                .antMatchers("/api/**/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return  super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter authenticationJWTTokenFilter(){
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }
}
