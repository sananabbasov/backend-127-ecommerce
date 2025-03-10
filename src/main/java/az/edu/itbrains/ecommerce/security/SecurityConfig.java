package az.edu.itbrains.ecommerce.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(c->c.disable())
                .authorizeHttpRequests((request) -> {
                    request.requestMatchers("/contact").authenticated();
                    request.anyRequest().permitAll();
                })
                .formLogin((form) ->{
                    form.defaultSuccessUrl("/dashboard");
                    form.loginPage("/login");
                    form.failureForwardUrl("/login");
                });


        return http.build();
    }

}
