package br.uni.unibet.UniBet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uni.unibet.UniBet.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    Usuario findByLogin(String login);
}