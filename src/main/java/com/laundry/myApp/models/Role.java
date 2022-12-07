package com.laundry.myApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Role implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String role;

	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private List<User> users;

	public Role(String role) {
		this.role = role;
	}
	@Override
	public String getAuthority() {
		return role;
	}
}