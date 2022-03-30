package com.dam.kanpeki.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//public class User implements UserDetails {
public class User {

//	@Id
//	@GeneratedValue
//	private Long id;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5707420370591392505L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	private String fullName;

	@Column(unique = true)
	private String nickname;

	private String URLimage;

	private LocalDateTime birthday;

	private String city;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder.Default
	private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Set<Result> results;

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
//	}
//
//	// MÉTODOS USERDETAILS
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// Para gestionar la expiración de cuentas
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// Para gestionar el bloqueo de cuentas
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// Para gestionar la expiración de cuentas
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// Para gestionar el bloqueo de cuentas
//		return false;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	/**
//	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
//	 * cuerpo a este método
//	 */
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	/**
//	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
//	 * cuerpo a este método
//	 */
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	/**
//	 * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
//	 * cuerpo a este método
//	 */
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	
//	/**
//	 * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
//	 * cuerpo a este método
//	 */	
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

}
