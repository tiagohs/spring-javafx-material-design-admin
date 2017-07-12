package com.tiagohs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private AuthSuccessHandler authHandler;
	
	@Value("${spring.queries.users}")
	private String usersQuery;
	
	@Value("${spring.queries.roles}")
	private String rolesQuery;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/auth/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
				.antMatchers("/user/**").hasAuthority("USER")
				.anyRequest().authenticated()
				.and().csrf().disable()
			.formLogin()
				.loginPage("/auth/login").failureUrl("/auth/login?error=true")
				.successHandler(authHandler)
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied")
			.and()
				.csrf().disable();
						
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		    .jdbcAuthentication()
			    .usersByUsernameQuery(usersQuery)
			    .authoritiesByUsernameQuery(rolesQuery)
		    	.dataSource(dataSource)
		    	.passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}

}
