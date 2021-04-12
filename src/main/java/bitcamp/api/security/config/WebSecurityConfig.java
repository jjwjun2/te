package bitcamp.api.security.config;

import bitcamp.api.security.domain.SecurityProvider;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private SecurityProvider provider;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Disable CSRF (cross site request forgery)
//        http.csrf().disable();
//
//        // No session will be created or used by spring security
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()//
//                .antMatchers("/usr/signin").permitAll()//
//                .antMatchers("/usr/signup").permitAll()//
//                .antMatchers("/board/blogAll").permitAll()//
//                .antMatchers("/paging").permitAll()//
//                .antMatchers("/h2-console/**/**").permitAll()
//                // Disallow everything else..
//                .anyRequest().authenticated();
//
//        // If a user try to access a resource without having enough permissions
//        http.exceptionHandling().accessDeniedPage("/login");
//
//        // Apply JWT
//        http.apply(new SecurityConfig(provider));
//
//        // Optional, if you want to test the API from a browser
//        // http.httpBasic();
//    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//
//                // allow anonymous resource requests
//                .antMatchers(
//                        "/",
//                        "/h2-console/**"
//                );
//    }
//
//
//}


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProvider provider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()//
                .antMatchers("/usr/signin").permitAll()//
                .antMatchers("/usr/signup").permitAll()//
                .antMatchers("/board/blogAll").permitAll()//
                .antMatchers("/usr/paging").permitAll()//
                .antMatchers("/usr/all").permitAll()//
                .antMatchers("/uae/admin/statistic").permitAll()//
                .antMatchers("/usr/*/*").permitAll()//
                .antMatchers("/h2-console/**/**").permitAll()
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

        // Apply JWT
        http.apply(new SecurityConfig(provider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")

                // allow anonymous resource requests
                .antMatchers(
                        "/",
                        "/h2-console/**"
                );
    }


}

