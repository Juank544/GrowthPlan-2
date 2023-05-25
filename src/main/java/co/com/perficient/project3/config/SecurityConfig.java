package co.com.perficient.project3.config;

import co.com.perficient.project3.utils.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static co.com.perficient.project3.utils.constant.StadiumConstants.STADIUM;
import static co.com.perficient.project3.utils.constant.TeamConstants.TEAM;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST).hasRole(Roles.ADMIN.name())
                        .requestMatchers(HttpMethod.GET).authenticated()
                        .requestMatchers(HttpMethod.PUT, STADIUM + "/**", TEAM + "/**")
                        .hasAnyRole(Roles.ADMIN.name(), Roles.PRESIDENT.name())
                        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
