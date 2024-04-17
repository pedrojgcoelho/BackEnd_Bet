package br.uni.unibet.UniBet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.uni.unibet.UniBet.model.Jogo;
import br.uni.unibet.UniBet.model.DTO.JogoInputDTO;
import br.uni.unibet.UniBet.model.DTO.JogoUpdateDTO;
import br.uni.unibet.UniBet.model.DTO.JogoViewDTO;
import br.uni.unibet.UniBet.service.JogoService;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    JogoService jogoServ;

    @PostMapping("/")
    public ResponseEntity<?> criaJogo(@RequestBody JogoInputDTO jogo) {
        try {
            jogoServ.criarJogo(jogo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaJogo(@PathVariable Integer id) {
        try {
            JogoViewDTO a = jogoServ.getJogo(id);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> atualizarJogo(@PathVariable Integer id, @RequestBody JogoUpdateDTO jogo) {
        try {
            jogoServ.atualizarJogo(id, jogo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}