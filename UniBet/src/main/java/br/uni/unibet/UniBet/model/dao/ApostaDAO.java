package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.ETipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {
    
    public List<Aposta> findbyJogadorIdAndJogoresultado(int id, ETipoResultado tipo); 
    
}
