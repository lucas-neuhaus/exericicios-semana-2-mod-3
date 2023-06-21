package com.senai.ex2semana2mod3.controller;

import com.senai.ex2semana2mod3.model.Time;
import com.senai.ex2semana2mod3.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/time" )
public class TimeController {
    @Autowired
    private TimeService timeService;
    @PostMapping
    public ResponseEntity<Time> criarTime(@RequestBody Time time) {
        Time novoTime = timeService.salvarTime(time);
        return new ResponseEntity<>(novoTime, HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Time>> buscarTimePorNome(@RequestParam String nome){
        List<Time> times = timeService.buscarTimesPorNome(nome);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }
}
