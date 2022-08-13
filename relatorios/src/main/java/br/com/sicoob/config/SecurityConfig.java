package br.com.sicoob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sicoob.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/v1**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/home**")
                 	.permitAll()
                 .antMatchers("/login**")
                 	.permitAll()
                .antMatchers("/monitorar**")
                 	.hasRole("USER") 	
                .anyRequest().authenticated()	
                 
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
             .and()    
             	.csrf()
             	.disable();
    }
}
