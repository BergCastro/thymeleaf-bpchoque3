package br.com.fireware.bpchoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/home").permitAll()		
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/cargos/**").hasRole("ADMIN")
				.antMatchers("/bairros/**").hasRole("ADMIN")
				.antMatchers("/cidades/**").hasRole("ADMIN")
				.antMatchers("/opms/**").hasRole("ADMIN")
				.antMatchers("/pessoas/**").hasRole("ADMIN")
				.antMatchers("/testesFisicos/**").hasAnyRole("USER_DEF, ADMIN")
				.antMatchers("/avaliacoesIndividuais/**").hasAnyRole("USER_DEF, ADMIN")
				.and()
				.exceptionHandling().accessDeniedPage("/acessoNegado")
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");	
					
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				
				.withUser("user2").password("password").roles("USER_DEF").and()
				.withUser("admin").password("admin").roles("ADMIN");
	}
}
