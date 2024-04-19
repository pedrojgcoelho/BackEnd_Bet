package br.uni.unibet.UniBet.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String login;
    private double saldo;
    private boolean ehAdmin;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Aposta> minhasApostas;

    public void sacar(double valorAposta) {
        if (valorAposta > 0 && valorAposta <= saldo) {
            saldo -= valorAposta;
        }
    }
}
