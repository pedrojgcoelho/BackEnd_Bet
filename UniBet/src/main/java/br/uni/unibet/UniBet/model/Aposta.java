package br.uni.unibet.UniBet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aposta {
    private int id;
    private double valorAposta;
    private Usuario jogador;
    private Jogo jogo;
    private ETipoResultado aposta;
}
