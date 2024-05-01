package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.ETipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {

//    @Query("SELECT a FROM Aposta a WHERE a.jogador.id = :id AND a.jogo.tipoResultado = :tipo")

    public List<Aposta> findByJogadorIdAndJogoResultado(int id, ETipoResultado tipo);
    
    public Integer countByJogadorId(Integer id);
    
    @Query("select count(a) from Aposta a where a.jogador.id = :id ")
    public Integer quantidadeApostasJogador(Integer id);
    
    
}
