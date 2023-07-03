package ch.bbw.m183.vulnerapp.config;

import ch.bbw.m183.vulnerapp.datamodel.UserEntity;
import ch.bbw.m183.vulnerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {


    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserEntity> user = userRepository.findById(username);
                if (user.isEmpty()) {
                    return null;
                }
                UserDetails userDetails = User.withUsername(username)
                        .password(user.get().getPassword())
                        .roles("USER")
                        .build();
                if (user.get().getUsername().equals("admin")) {
                    userDetails = User.withUsername(username)
                            .password(user.get().getPassword())
                            .roles("ADMIN")
                            .build();
                }
                return userDetails;
            }
        };
    }


/*
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
 */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(basic -> basic.realmName("vulnerApp"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(autherize -> autherize
                        //.requestMatchers(HttpMethod.GET, "api/blog").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/blog").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
