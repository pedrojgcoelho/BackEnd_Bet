package br.uni.unibet.UniBet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.annotation.Generated;
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
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA;
    private double oddsVitoriaTimeB;
    private double oddsEmpate;

    @ManyToOne
    @JoinColumn(name = "id_time_a")
    private Time timeA;

    @ManyToOne
    @JoinColumn(name = "id_time_b")
    private Time timeB;

    private int pontuacaoTimeA;
    private int pontuacaoTimeB;
    private ETipoResultado resultado;
}