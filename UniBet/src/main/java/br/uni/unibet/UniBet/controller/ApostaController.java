package br.uni.unibet.UniBet.controller;


import br.uni.unibet.UniBet.model.Aposta;
import br.uni.unibet.UniBet.model.DTO.ApostaInputDTO;
import br.uni.unibet.UniBet.model.DTO.ApostaViewDTO;
import br.uni.unibet.UniBet.service.ApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    ApostaService apoServ;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_APOSTADOR')")
    public ResponseEntity<?> criaAposta( @RequestBody ApostaInputDTO aposta){
        try {
            apoServ.criaAposta( aposta );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaAposta( @PathVariable Integer id){
        try {
            ApostaViewDTO a = apoServ.getAposta(id);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getApostasUsuario(@PathVariable Integer id){
        return ResponseEntity.ok( apoServ.getApostaUsuario(id) );
    }

    @GetMapping("/usuario/{id}/count")
    public ResponseEntity<?> getApostasUsuarioCount(@PathVariable Integer id){
        try {
            return ResponseEntity.ok( apoServ.getCountApostaUsuario(id) );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<?> getValorRecebido(@PathVariable Integer id){
        try {
            return ResponseEntity.ok( apoServ.getValorRecebidoAposta(id) );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    
    
    
    
    
    

}
