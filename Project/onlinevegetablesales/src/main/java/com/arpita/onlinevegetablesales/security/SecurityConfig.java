    package com.arpita.onlinevegetablesales.security;

    import com.arpita.onlinevegetablesales.service.UserService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

    @Configuration
    public class SecurityConfig{
        @Bean
        public BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        @Bean
        public DaoAuthenticationProvider authenticationProvider(UserService userService){
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(userService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception{
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/register/**").permitAll()
                            .requestMatchers("/img/**").permitAll()
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/fonts/**").permitAll()
                            .requestMatchers("/js/**").permitAll()
                            .requestMatchers("/sass/**").permitAll()
                            .requestMatchers("/Source/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .formLogin(form ->
                    form
                            .loginPage("/showMyLoginPage")
                            .loginProcessingUrl("/authenticateTheUser")
                            .successHandler(customAuthenticationSuccessHandler)
                            .permitAll()
                    )
                    .logout(logout->logout.permitAll()
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/showMyLoginPage")
                    )
                    .exceptionHandling(configurer ->
                            configurer.accessDeniedPage("/access-denied")
                    );
            return http.build();
        }

    }
