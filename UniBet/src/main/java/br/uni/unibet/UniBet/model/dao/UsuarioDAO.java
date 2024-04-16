package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    public static final UsuarioDAO usuarioDAO = null;

    public boolean existsByNome(String nome);

}
