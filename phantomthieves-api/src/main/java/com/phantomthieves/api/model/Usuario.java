package com.phantomthieves.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_USUARIO")
	private Integer id;
	
	@Column(name = "USER", length = 50, nullable = false)
	@NotNull(message = "O user é obrigatório")
	@Length(max= 50, min = 3, message="O email de usuário deve conter entrer 3 e 50 caracteres")
	private String user;
	
	
	@Column(name = "PASSWORD", length = 50, nullable = false)
	@NotNull(message = "A senha é obrigatório")
	private String password;

	@Column(name = "PERFIL", length = 50, nullable = false)
	@NotNull(message = "O perfil é obrigatório")
	@Length(max= 50, min = 3, message="O perfil de usuário deve conter entrer 3 e 50 caracteres")
	private String perfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ROLE_USERS",
	joinColumns=@JoinColumn(name="COD_USUARIO"),
	inverseJoinColumns=@JoinColumn(name="COD_ROLE"))
	private Set<Roles> roles;
	
	public Usuario() {
		super();
	}


	public Usuario(Integer id,
			@NotNull(message = "O user é obrigatório") @Length(max = 50, min = 3, message = "O nome de usuário deve conter entrer 3 e 50 caracteres") String user,
			@NotNull(message = "A senha é obrigatório") String password, @NotNull(message = "O perfil é obrigatório") String perfil) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.perfil = perfil;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Roles> getRoles() {
		return roles;
	}


	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
