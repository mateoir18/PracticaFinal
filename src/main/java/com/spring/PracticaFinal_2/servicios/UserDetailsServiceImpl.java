package com.spring.PracticaFinal_2.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.PracticaFinal_2.usuarios.Usuario;
import com.spring.PracticaFinal_2.usuarios.UsuarioDao;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioDao.findById(username);
		if(usuario.isPresent()) {
			
			return (UserDetails)usuario.get();
		}
		
		throw new UsernameNotFoundException(username);
	}
}
