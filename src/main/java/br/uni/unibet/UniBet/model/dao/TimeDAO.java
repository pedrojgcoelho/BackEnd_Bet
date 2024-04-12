package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDAO extends JpaRepository<Time, Integer> {

    Time findByNome(String nome);
    
    
}
