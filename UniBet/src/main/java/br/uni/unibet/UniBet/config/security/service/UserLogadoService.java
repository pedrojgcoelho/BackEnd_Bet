package br.uni.unibet.UniBet.config.security.service;

import br.uni.unibet.UniBet.config.security.model.UserLogado;
import br.uni.unibet.UniBet.model.Usuario;
import br.uni.unibet.UniBet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLogadoService implements UserDetailsService {

    @Autowired
    UsuarioDAO uDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = uDao.findByLogin(username);
        if (u == null){
            throw  new UsernameNotFoundException("usuário ou senha incorreta!!!");
        }else {
            return new UserLogado( u );
        }
    }
}
