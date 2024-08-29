package com.turkcell.pair3.customerservice.configuration;

import com.turkcell.pair3.core.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        baseSecurityService.configureCoreSecurity(http);

        http.authorizeHttpRequests(
                (req)->req
                        //.requestMatchers("/api/customers/**").hasAnyAuthority("admin")
                        //.requestMatchers("/api/cities/**").hasAnyAuthority("admin")
                        //.requestMatchers("/api/addresses/**").hasAnyAuthority("admin")
                        //.requestMatchers("/api/invoices/**").hasAnyAuthority("admin")
                        .requestMatchers("/api/**").hasAnyAuthority("admin")
        );

        return http.build();
    }
}
