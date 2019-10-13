package com.phantomthieves.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phantomthieves.api.model.Roles;
import com.phantomthieves.api.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository userRepository;
	
    @Override
     public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		
     //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
     com.phantomthieves.api.model.Usuario appUser = 
                 userRepository.findByUser(usuario).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
		
    //Mapear nuestra lista de Authority con la de spring security 
    List grantList = new ArrayList();
    for (Roles roles: appUser.getRoles()) {
        // ROLE_USER, ROLE_ADMIN,ROLE_CLIENT..
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getRole());
            grantList.add(grantedAuthority);
    }
		
    //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
    UserDetails user = (UserDetails) new User(appUser.getUser(), appUser.getPassword(), grantList);
         return user;
    }
	
}
