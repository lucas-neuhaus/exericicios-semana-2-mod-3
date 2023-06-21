package com.senai.ex2semana2mod3.repository;

import com.senai.ex2semana2mod3.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByNome(String nome);
}
