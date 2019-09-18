package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phantomthieves.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

}
