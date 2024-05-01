package br.uni.unibet.UniBet.model.DTO;

import br.uni.unibet.UniBet.model.ETipoResultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApostaInputDTO {
    private int idApostador, idJogo;
    private double valorAposta;
    private ETipoResultado resultado;
}
