package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.ETipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {

    public List<Aposta> findbyJogadorAndJogoResultado(int id, ETipoResultado tipo);

    public Integer countByJogadorId(Integer id);

    @Query("select count(a) from Aposta a where a.jogador.id = :id")
    public Integer quantidadeApostasJogador(Integer id);

}
