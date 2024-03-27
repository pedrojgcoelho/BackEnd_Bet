package br.uni.unibet.UniBet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aposta {
    @Id
    @GeneratedValue
    private int id;
    private double valorAposta;
    @ManyToOne
    private Usuario jogador;
    @ManyToOne
    private Jogo jogo;
    private ETipoResultado aposta;
}
