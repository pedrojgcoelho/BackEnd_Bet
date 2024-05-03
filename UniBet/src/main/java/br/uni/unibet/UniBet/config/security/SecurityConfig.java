package br.uni.unibet.UniBet.config.security;

import br.uni.unibet.UniBet.config.security.filter.JwtAutenticarFilter;
import br.uni.unibet.UniBet.config.security.filter.JwtValidarFilter;
import br.uni.unibet.UniBet.config.security.service.UserLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    UserLogadoService uServ;

    //Autenticação - Verificar se usuário é valido
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( uServ ).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("user").password( passwordEncoder().encode("123") ).roles("APOSTADOR")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("123") ).roles("ADMIN");
    }

    //Autorização - O que o usuário pode usar
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //time jogo user aposta desenv
        http.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/desenv/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/aposta/**").hasRole("APOSTADOR")
                .antMatchers("/aposta/usuario/*/count").hasAnyRole("ADMIN", "APOSTADOR")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAutenticarFilter(authenticationManager()))
                .addFilter(new JwtValidarFilter(authenticationManager()));
//                .httpBasic();
    }
}
