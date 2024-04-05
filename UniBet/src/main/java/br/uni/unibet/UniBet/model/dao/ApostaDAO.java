package br.uni.unibet.UniBet.model.dao;

import br.uni.unibet.UniBet.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {
}
