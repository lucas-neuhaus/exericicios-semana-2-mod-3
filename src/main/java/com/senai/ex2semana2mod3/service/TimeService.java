package com.senai.ex2semana2mod3.service;

import com.senai.ex2semana2mod3.model.Time;
import com.senai.ex2semana2mod3.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {
    @Autowired
    private TimeRepository timeRepository;

    public Time criarTime(Time time) {
        return timeRepository.save(time);
    }

    public Time salvarTime(Time time) {
        return timeRepository.save(time);
    }

    public List<Time> buscarTimesPorNome(String nome){
        return timeRepository.findByNome(nome);
    }
}
