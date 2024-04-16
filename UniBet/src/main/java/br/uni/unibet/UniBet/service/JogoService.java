package br.uni.unibet.UniBet.service;

import br.uni.unibet.UniBet.model.Jogo;
import br.uni.unibet.UniBet.model.dao.JogoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    JogoDAO jogoDAO;

    public void criarJogo(Jogo jogo) {
        jogoDAO.save(jogo);
    }

    public Jogo getJogo(int id) {
        return jogoDAO.findById(id).orElse(null);
    }

    public List<Jogo> listarJogos() {
        return jogoDAO.findAll();
    }

    public void atualizarJogo(Jogo jogo) {
        jogoDAO.save(jogo);
    }

    public void deletarJogo(int id) {
        jogoDAO.deleteById(id);
    }
}
