package br.uni.unibet.UniBet.model.DTO;

import java.time.LocalDateTime;
import br.uni.unibet.UniBet.model.ETipoResultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoUpdateDTO {
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA;
    private double oddsVitoriaTimeB;
    private double oddsEmpate;
    private int pontuacaoTimeA;
    private int pontuacaoTimeB;
    private ETipoResultado resultado;
}