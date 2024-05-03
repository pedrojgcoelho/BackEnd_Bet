package br.uni.unibet.UniBet.controller;

import br.uni.unibet.UniBet.model.Time;
import br.uni.unibet.UniBet.model.dao.TimeDAO;
import br.uni.unibet.UniBet.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeService timeServ;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveTime(@RequestBody(required = true) Time time){
        try {
            Time timeResp = timeServ.verificaSalvamento(time);
            return ResponseEntity.status(HttpStatus.CREATED).body(timeResp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> apagarTime( @PathVariable(required = true)  int id) {
        try {
            timeServ.apagaTime(id);
            return ResponseEntity.ok("Time apagado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> retornaTimes(){
        return  ResponseEntity.ok(  timeServ.buscarTodos()  );
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> retornaTimes( @PathVariable(required = true) int id){
        return  ResponseEntity.ok(  timeServ.buscar(id)  );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> alteraTime(@PathVariable(required = true) int id,
                                        @RequestBody Time time){
        try {
            Time t = timeServ.alteraTime(id, time);
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
