package com.laundry.myApp.component;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.laundry.myApp.models.Role;
import com.laundry.myApp.repository.RoleRepository;

import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Role> roles = List.of(new Role("ADMIN"), new Role("USER"));
		roles.forEach(role -> {
			if(roleRepository.findByRole(role.getRole()).isEmpty())
				roleRepository.save(role);
		});
	}

}
