package br.uni.unibet.UniBet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uni.unibet.UniBet.model.Time;
import br.uni.unibet.UniBet.model.dao.TimeDAO;
import lombok.var;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeDAO tDao;
    private TimeDAO  dTime;

    public TimeController(TimeDAO dTime) {
        this.dTime = dTime;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveTime(@RequestBody Time time){

        if (time.getNome().isEmpty() || time.getNome().isBlank()){
            return ResponseEntity.badRequest().body("Sem Time.");
        }
        var existingTime = this.dTime.findByNome(time.getNome());

        if (existingTime != null) {
            return ResponseEntity.badRequest().body("Esse time já existe"); 
        }
        var createdTime = this.dTime.save(time);
        return ResponseEntity.ok(createdTime);

    }
}
