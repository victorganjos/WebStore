package com.phantomthieves.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.phantomthieves.api.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private UserDetailsServiceImpl userDetailsService;
	 

	String[] resources = new String[]{
	            "/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**","C:/**"
	};
		
	private static final String[] PUBLIC_MATHCERS = {
			"//**",
			"/usuarios/**",
			"/produtos/detalheProduto2",
			"/cliente/inserir-dados-cliente",
			"/cliente/inserir-usuario-cliente"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATHCERS).permitAll()
			.antMatchers(resources).permitAll()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.POST, "/index").permitAll()
			.antMatchers(HttpMethod.GET, "/carrinho/**").permitAll()
			.antMatchers(HttpMethod.POST, "/carrinho/**").permitAll()
			.antMatchers(HttpMethod.GET, "/produtos/detalheProduto2").permitAll()
			.antMatchers(HttpMethod.POST, "/produtos/detalheProduto2").permitAll()
			.antMatchers(HttpMethod.GET, "/produtos/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/produtos/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/cliente/meus-dados").hasRole("CLIENT")
			.antMatchers(HttpMethod.POST, "/cliente/meus-dados").hasRole("CLIENT")
			.anyRequest().authenticated()
			.and().formLogin().permitAll().defaultSuccessUrl("/")
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());     
    }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
}
