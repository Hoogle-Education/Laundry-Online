package com.laundry.myApp.controllers;

import com.laundry.myApp.controllers.form.RegistrationFormDto;
import com.laundry.myApp.controllers.form.UserUpdateFormDto;
import com.laundry.myApp.service.exceptions.UserNotRegisteredException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laundry.myApp.controllers.dto.UserDto;
import com.laundry.myApp.models.Role;
import com.laundry.myApp.models.User;
//import com.laundry.myApp.service.UserService;
import com.laundry.myApp.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;

	// handler method to handle login request
	@GetMapping("/login")
	public String loginForm(){
		return "login";
	}

	@GetMapping("/new")
	public String addUser(Model model) {
		model.addAttribute("registrationForm", new RegistrationFormDto());
		return "/register";
	}
   @PostMapping("/save")
	public String registration(@Valid @ModelAttribute("registrationForm")
								  RegistrationFormDto userForm,
							   BindingResult result,
							   Model model) {

	   if (result.hasErrors()) {
		   model.addAttribute("registrationForm", userForm);
		   return "/register";
	   }

	   userService.register(userForm);
	   //  attributes.addFlashAttribute("message", "Information added succesfully!");
	   return "redirect:/users/new";

   }

	// method to bring the user list in the admin html
	@RequestMapping("/admin/list")
	public String userList(Model model) {
		model.addAttribute("users", userService.getAll());
		return "/auth/admin/admin-user-list";
	}

	// method to delete a user by the admin
	@GetMapping("/admin/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		// TODO
		return "redirect:/users/admin/list";
	}

	// method to edit a user by the admin
	// TODO: admin check
	@PostMapping("/edit")
	public String editUser(@Valid UserUpdateFormDto updateFormDto, BindingResult result)
			throws UserNotRegisteredException {

		if (result.hasErrors()) {
			// user.setId(id);
			return "/auth/users/user-edit";
		}
		userService.update(updateFormDto);
		return "redirect:/users/admin/list";
	}

//	@GetMapping("/editRole/{id}")
//	public String selectRole(@PathVariable("id") long id, Model model) {
//		// TODO
//		User user = oldUser.get();
//		model.addAttribute("user", user);
//
//		model.addAttribute("listRoles", roleRepository.findAll());
//
//		return "/auth/admin/edit-user-role";
//	}

//	@PostMapping("/editRole/{id}")
//	public String assignRole(@PathVariable("id") long idUser, @RequestParam(value = "pps", required = false) int[] pps,
//			User user, RedirectAttributes attributes) {
//		if (pps == null) {
//			user.setId(idUser);
//			attributes.addFlashAttribute("message", "At leat one role has to be assign");
//			return "redirect:/user/editRole/" + idUser;
//		} else {
//			// Bring the role list selected by the user
//			List<Role> roles = new ArrayList<Role>();
//			for (int i = 0; i < pps.length; i++) {
//				long idRole = pps[i];
//				Optional<Role> roleOptional = roleRepository.findById(idRole);
//				if (roleOptional.isPresent()) {
//					Role role = roleOptional.get();
//					roles.add(role);
//				}
//			}
//			Optional<User> userOptional = userRepository.findById(idUser);
//			if (userOptional.isPresent()) {
//				User usr = userOptional.get();
//				usr.setRoles(roles); // set roles to the users
//				usr.setEnable(user.isEnable());
//				userRepository.save(usr);
//			}
//		}
//		return "redirect:/user/admin/list";
//	}

}// end main class
