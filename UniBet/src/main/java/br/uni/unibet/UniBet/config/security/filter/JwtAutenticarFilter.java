package br.uni.unibet.UniBet.config.security.filter;

import br.uni.unibet.UniBet.config.security.model.UserLogado;
import br.uni.unibet.UniBet.model.DTO.UsuarioLoginDto;
import br.uni.unibet.UniBet.model.Usuario;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Elements;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import com.auth0.jwt.JWT;


public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TIME_EXPIRATION_MS = 300000;
    public static final String TOKEN_KEY = "a@sd#rw434esFSDFwser324rfcdrferfre-ewsrfwerrfrdfgferfef";

    AuthenticationManager authenticationManager;

    public JwtAutenticarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) 
            throws AuthenticationException {

        try {
            UsuarioLoginDto u = new ObjectMapper().readValue(
                    request.getInputStream(), UsuarioLoginDto.class);

            UserLogado uLogin = new UserLogado(new Usuario());
            uLogin.getUser().setLogin(u.getLogin());
            uLogin.getUser().setSenha(u.getSenha());

            return  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    uLogin.getUsername(),
                    uLogin.getPassword(),
                    uLogin.getAuthorities()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha na autenticação", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        UserLogado user = (UserLogado) authResult.getPrincipal();

        long dt = new Date().getTime() + TIME_EXPIRATION_MS;
        String token = JWT.create()
                .withExpiresAt(new Date(dt))
                .withSubject( user.getUser().getNome())
                .withClaim("nome", user.getUser().getLogin() )
                .withClaim("email", user.getUser().getEmail() )
                .withClaim("saldo", user.getUser().getSaldo() )
                .withClaim("ROLES", user.getAuthorities().toString()  )
                .sign(Algorithm.HMAC512( TOKEN_KEY ));
        System.out.println(" TOKEN :: "+token);
        response.getWriter().print(token);
        response.getWriter().flush();
    }
}
