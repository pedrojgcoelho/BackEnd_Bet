package br.uni.unibet.UniBet.model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int idJogo;
    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA, oddsVitoriaTimeB, oddsEmpate;
    @ManyToOne
    @JoinColumn(name = "id_time_a")
    private Time timeA;
    @ManyToOne
    @JoinColumn(name = "id_time_b")
    private Time timeB;
    private int pontuacaoTimeA, pontuacaoTimeB;
    private ETipoResultado resultado;
}
