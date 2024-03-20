package br.uni.unibet.UniBet.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private String nome, login, senha, email;
    private double saldo;
    private ArrayList<Aposta> minhasApostas;
}
