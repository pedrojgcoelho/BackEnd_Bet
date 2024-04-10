package br.uni.unibet.UniBet.model.DTO;

import br.uni.unibet.UniBet.model.ETipoResultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApostaViewDTO {

    private int id;
    private double valorAposta;
    private int idJogador;
    private String nomeJogador;
    private LocalDateTime dataJogo;
    private int idTime1;
    private String time1;
    private int idTime2;
    private String time2;
    private ETipoResultado resultadoJogo;
    private ETipoResultado resultadoApostado;
    private boolean acertou;


}
