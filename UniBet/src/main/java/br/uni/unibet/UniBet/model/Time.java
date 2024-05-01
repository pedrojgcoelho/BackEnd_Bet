package br.uni.unibet.UniBet.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    
//    @OneToMany(mappedBy = "timeA", fetch = FetchType.LAZY)
//    private List<Jogo> jogosA;
//
//    @OneToMany(mappedBy = "timeB", fetch = FetchType.LAZY)
//    private List<Jogo> jogosB;

}
