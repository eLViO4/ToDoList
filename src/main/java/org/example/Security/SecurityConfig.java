package org.example.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final WebAuthenticationProvider webAuthenticationProvider;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/home", "/login","/error","/404","/create-user").permitAll()
                                .requestMatchers("/**").hasRole("ADMIN")
                                .requestMatchers("/all-user-todos", "/todo-info", "/all-todos-tasks").hasRole("USER")
                                .requestMatchers("/update-todo", "/update-task").hasRole("USER")
                                .requestMatchers("/create-task", "/create-todo").hasRole("USER")
                                .requestMatchers("/user-info", "/update-user").authenticated()
                                .anyRequest().authenticated()
                ).exceptionHandling(exception -> exception
                        .accessDeniedPage("/error")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .userDetailsService(userDetailsService);

        return http.build();

    }

}
