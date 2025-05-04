package com.meutime.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meutime.manager.entity.Clube;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
}