package br.uni.unibet.UniBet.service;

import br.uni.unibet.UniBet.model.Usuario;
import br.uni.unibet.UniBet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    public void criarUsuario(Usuario usuario) throws Exception {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new Exception("O nome do usuário não pode ser vazio.");
        }

        if (usuarioDAO.existsByNome(usuario.getNome())) {
            throw new Exception("Já existe um usuário com o nome informado.");
        }

        usuarioDAO.save(usuario);
    }

    public Usuario getUsuario(int id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.findAll();
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    public void deletarUsuario(int id) {
        usuarioDAO.deleteById(id);
    }
}
