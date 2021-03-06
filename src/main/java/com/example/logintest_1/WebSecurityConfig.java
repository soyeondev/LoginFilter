package com.example.logintest_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.logintest_1.filter.LoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()				
				.antMatchers("/", "/home").permitAll()
//				.antMatchers(HttpMethod.POST, "/login?logout").authenticated()
				.anyRequest().authenticated()
				.and()
			.formLogin()
//				.loginPage("/login?logout")
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/hello", true)
				.and()
			.logout()
//				.logoutSuccessUrl("/login")
				.permitAll();
		http.addFilterBefore(new LoginFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("soyeon")
				.password("1234")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}