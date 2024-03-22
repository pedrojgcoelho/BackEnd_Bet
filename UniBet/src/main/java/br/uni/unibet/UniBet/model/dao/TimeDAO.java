package br.uni.unibet.UniBet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uni.unibet.UniBet.model.Time;

public interface TimeDAO extends JpaRepository<Time, Integer> {
    
}
