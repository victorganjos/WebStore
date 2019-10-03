package com.phantomthieves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	@Query(value = "SELECT * FROM USUARIO WHERE ATIVO = TRUE;", nativeQuery = true)
	List<Usuario> findAllAtivo();
	
	
	@Query(value = "UPDATE USUARIO SET ATIVO = FALSE WHERE COD_USUARIO = ?;", nativeQuery = true)
	void deleteDesativo(Integer id);
	
	@Modifying
	@Query(value = "INSERT ROLE_USERS(COD_USUARIO, COD_ROLE) VALUES(?, ?);", nativeQuery = true)
	void incluiRegra(Integer idUsuario, Integer idRegra);
	
	public Optional<Usuario> findByUser(String user);
}
