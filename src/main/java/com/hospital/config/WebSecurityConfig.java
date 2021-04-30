package com.hospital.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private JwtAuthenticationEP jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.cors().disable();
		
		
	
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll().antMatchers("/register").permitAll().
				antMatchers("/addDonorRequest").permitAll().
				antMatchers("/addPlasmaDonotor").permitAll().
				antMatchers("/updateUserRequest").permitAll().
				antMatchers("/updatePlasma").permitAll().
				
				antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/glyphicons-halflings-regular.448c34a56d699c29117a.woff2",
                        "/glyphicons-halflings-regular.89889688147bd7575d63.svg",
                        "/glyphicons-halflings-regular.e18bbf611f2a2e43afc0.ttf",
                        "/glyphicons-halflings-regular.f4769f9bdb7466be6508.eot",
                        "/glyphicons-halflings-regular.fa2772327f55d8198301.woff",
                        "/fontawesome-webfontba72.b526f0637e912fae979b.svg",
                        "/fontawesome-webfontba72.3293616ec0c605c7c2db.woff",
                        "/fontawesome-webfontba72.dcb26c7239d850266941.ttf",
                        "/*/.html",
                        "/*/.css",
                        "/*/.js",
                        "/assets/css/**",
                        "/assets/images/**",
                        "/assets/font-awesome/fonts/**",
                        "/assets/javascript/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/swagger.json",
                        "/main.e9c60402263ff04c446b.js",
                        "/polyfills.94daefd414b8355106ab.js",
                        "/runtime.7b63b9fd40098a2e8207.js",
                        "/styles.7b24d4c0c7d7e30102d9.css"
                        
                ).permitAll().
				
				
				
				// all other requests need to be authenticated
				anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	

}

