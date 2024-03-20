package br.uni.unibet.UniBet.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogo {
    private int idJogo;
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA, oddsVitoriaTimeB, oddsEmpate;
    private Time timeA, timeB;
    private int pontuacaoTimeA, pontuacaoTimeB;
    private ETipoResultado resultado;
}
