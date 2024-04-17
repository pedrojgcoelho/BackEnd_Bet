package br.uni.unibet.UniBet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uni.unibet.UniBet.model.Usuario;
import br.uni.unibet.UniBet.model.DTO.UsuarioCreateDTO;
import br.uni.unibet.UniBet.model.DTO.UsuarioUpdateDTO;
import br.uni.unibet.UniBet.model.DTO.UsuarioViewDTO;
import br.uni.unibet.UniBet.model.dao.UsuarioDAO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO dUsuario;

    public Usuario verificaSalvamento(UsuarioCreateDTO usuario) throws Exception {
        if (usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            String errorMessage = "Nome de Usuário Invalido";
            throw new Exception(errorMessage);
        }

        if (usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) {
            String errorMessage = "Email do Usuário Invalido.";
            throw new Exception(errorMessage);
        }

        if (usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
            String errorMessage = "Senha Incorreta.";
            throw new Exception(errorMessage);
        }

        if (usuario.getLogin().isEmpty() || usuario.getLogin().isBlank()) {
            String errorMessage = "Login Invalido.";
            throw new Exception(errorMessage);
        }

        Usuario usuarioComMesmoEmail = dUsuario.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail != null) {
            throw new Exception("Email já cadastrado");
        }

        Usuario usuarioComMesmoLogin = dUsuario.findByLogin(usuario.getLogin());
        if (usuarioComMesmoLogin != null) {
            throw new Exception("Usuário já cadastrado");
        }

        Usuario us = new Usuario();
        us.setNome(usuario.getNome());
        us.setLogin(usuario.getLogin());
        us.setEmail(usuario.getEmail());
        us.setSenha(usuario.getSenha());
        us.setSaldo(usuario.getSaldo());
        us.setEhAdmin(usuario.isEhAdmin());

        return dUsuario.save(us);
    }

    public Object getAllUsers() {
        return dUsuario.findAll();
    }

    public void deleteUser(int id) throws Exception {
        Usuario existinUser = dUsuario.findById(id).orElse(null);

        if (existinUser == null) {
            throw new Exception("Usuário não encontado");
        }

        dUsuario.delete(existinUser);
    }

    public UsuarioViewDTO getUser(int id) throws Exception {
        Usuario usuarioExistente = dUsuario.findById(id).orElse(null);

        if (usuarioExistente == null) {
            throw new Exception("Usuário não encontado.");
        }

        UsuarioViewDTO us = new UsuarioViewDTO();
        us.setId(usuarioExistente.getId());
        us.setNome(usuarioExistente.getNome());
        us.setSaldo(usuarioExistente.getSaldo());
        us.setLogin(usuarioExistente.getLogin());
        us.setEmail(usuarioExistente.getEmail());
        us.setEhAdmin(usuarioExistente.isEhAdmin());

        return us;
    }

    public void updateUser(int id, UsuarioUpdateDTO usuario) throws Exception {
        Optional<Usuario> optionalUsuario = dUsuario.findById(id);
        if (!optionalUsuario.isPresent()) {
            throw new Exception("Usuario " + id + " não existe");
        }

        Usuario usuarioExistente = optionalUsuario.get();

        if (usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            String errorMessage = "Nome de Usuário Invalido";
            throw new Exception(errorMessage);
        }

        if (usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()) {
            String errorMessage = "Email Invalido";
            throw new Exception(errorMessage);
        }

        if (usuario.getSenha().isEmpty() || usuario.getSenha().isBlank()) {
            String errorMessage = "Senha incorreta";
            throw new Exception(errorMessage);
        }

        if (usuario.getLogin().isEmpty() || usuario.getLogin().isBlank()) {
            String errorMessage = "Login incorreto";
            throw new Exception(errorMessage);
        }

        Usuario usuarioComMesmoEmail = dUsuario.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail != null) {
            throw new Exception("já existe um usuário cadastrado com esse e-mail");
        }

        Usuario usuarioComMesmoLogin = dUsuario.findByLogin(usuario.getLogin());
        if (usuarioComMesmoLogin != null) {
            throw new Exception("já existe um usuário cadastrado com esse login");
        }

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEhAdmin(usuario.isEhAdmin());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setSaldo(usuario.getSaldo());
        usuarioExistente.setSenha(usuario.getSenha());

        dUsuario.save(usuarioExistente);
    }


}