//package org.example.configuration;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.DispatcherType;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfiguration {
//
//    @Resource(name="myAppUserDetailsService")
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    DaoAuthenticationProvider authProvider(){
//        // for database users
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
//
//        // for in-memory users
//        //DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
//
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);
//
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        // Next line iss added to allow Spring reach .jsp pages
//                        // that should be visible to all users according to the following rules.
//                        // The reason is that in 6.0, the authorization filter is run for all dispatcher types,
//                        // including FORWARD. This means that the JSP that is forwarded, also needs to be permitted.
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/appUsers").permitAll()
//                        .requestMatchers("/addAppUser").permitAll()
//                        .requestMatchers("/activate").permitAll()
//                )
//                // login with default login page
//                //  .formLogin(form -> form
//                //          .permitAll()
//                //          )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .failureUrl("/login?error")
//                        .defaultSuccessUrl("/",true) //use wisely
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login?logout")
//                )
//                .exceptionHandling(accessDenied -> accessDenied
//                        .accessDeniedPage("/accessDenied")
//                )
//                //.httpBasic(); // httpBasic() was deprecated since 6.1
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
