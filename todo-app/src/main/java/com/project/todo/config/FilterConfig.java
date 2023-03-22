package com.project.todo.config;
//package com.project.todo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig {
//	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder)
//	{
//		UserDetails admin=User.withUsername("sri")
//				.password(encoder.encode("sri"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user=User.withUsername("aju")
//				.password(encoder.encode("aju"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//	{
//		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/home/welcome").permitAll()
//		.and()
//		.authorizeHttpRequests().requestMatchers("/api/v1/**").authenticated()
//		.and().formLogin()
//		.and().build();
//	}
//	@Bean
//	public PasswordEncoder PasswordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
//	
//
//}




import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
       filter.addUrlPatterns("/api/v1/blog/restricted");
    return filter;
    }
}