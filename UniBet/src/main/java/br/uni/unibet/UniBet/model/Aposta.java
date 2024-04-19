package br.uni.unibet.UniBet.model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private double valorAposta;

    @ManyToOne
    @JoinColumn(name = "jogadorId")
    private Usuario jogador;

    @ManyToOne
    @JoinColumn(name = "jogoId")
    private Jogo jogo;

    private ETipoResultado aposta;
}