package com.devsuperior.hrouath.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entidade auxiliar para buscar informacoes no hr-user
 * UserDetails - interface do Spring boot Security
 * @author fsouviei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails,Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private Set<Role> roles = new HashSet<>();
	
	// Metodo converter os roles<Set> para GrantedAuthority - Representa as permissoes de usuario - 2022, 11 Abril
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList());
	}
	@Override
	public String getUsername() {
		return email;
	}
	
	// Logica para ver se a conta não esta expirada - 2022, 11 Abril
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// Logica para ver se a conta não esta bloqueada - 2022, 11 Abril
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// Logica para ver se a crendeciais não esta expirada - 2022, 11 Abril
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	// Logica para ver se o user está habilitado - 2022, 11 Abril
	@Override
	public boolean isEnabled() {
		return true;
	}
}
