
  package com.laundry.myApp.service;
  
  import com.laundry.myApp.controllers.dto.UserDto;
  import com.laundry.myApp.controllers.form.RegistrationFormDto;
  import com.laundry.myApp.controllers.form.UserUpdateFormDto;
  import com.laundry.myApp.models.Role;
  import com.laundry.myApp.models.User;
  import com.laundry.myApp.repository.RoleRepository;
  import com.laundry.myApp.repository.UserRepository;
  import com.laundry.myApp.service.exceptions.UserAlreadyRegisteredException;
  import com.laundry.myApp.service.exceptions.UserNotFoundException;
  import com.laundry.myApp.service.exceptions.UserNotRegisteredException;
  import lombok.AllArgsConstructor;
  import org.springframework.stereotype.Service;


  import javax.annotation.security.DenyAll;
  import java.util.List;
  import java.util.stream.Collectors;

  @Service
  @AllArgsConstructor
  public class UserService {
      private UserRepository userRepository;

      private RoleRepository roleRepository;

      public List<User> getAll() {
          return userRepository.findAll();
      }

      public User getByUsername(String username) throws UserNotFoundException {
          return userRepository
                  .findByUsername(username)
                  .orElseThrow(() -> new UserNotFoundException(username));
      }

//      public List<User> getAllByRole(Role role) {
//          return userRepository.findByRoles(List.of(role));
//      }
//
//      public List<User> getAllByRoles(Role... roles) {
//          return userRepository.findByRoles(List.of(roles));
//      }

      public void register(RegistrationFormDto registrationForm) {
          User toRegister = toUser(registrationForm);
          updateRoleRepository(toRegister.getRoles());

          if(hasUserAlreadyRegistered(toRegister)) {
            throw new UserAlreadyRegisteredException(toRegister.getEmail());
          }

          userRepository.save(toRegister);
      }

      public User update(UserUpdateFormDto updateForm) throws UserNotRegisteredException {
          User newUserValues = toUser(updateForm);
          User toUpdate = userRepository
                  .findByEmail(updateForm.getDestinationEmail())
                  .orElseThrow(() -> new UserNotRegisteredException(updateForm.getDestinationEmail()));

          return update(toUpdate, newUserValues);
      }

      private User update(User target, User origin) {
          target.setName(origin.getName());
          target.setUsername(origin.getUsername());
          return userRepository.save(target);
      }

      private User applyAdminUpgrade(){
          // TODO
          return null;
      }

      private boolean hasUserAlreadyRegistered(User toRegister) {
          return userRepository.findByUsername(toRegister.getEmail()).isPresent();
      }

      private void updateRoleRepository(List<Role> roles) {
          roles.forEach(role -> {
              if(roleRepository.findByRole(role.getRole()).isEmpty())
                  roleRepository.save(role);
          });
      }

      private User toUser(RegistrationFormDto registrationForm) {
          Role role = new Role();
          role.setRole("USER");

          User user = new User();
          user.setName(registrationForm.getName());
          user.setEmail(registrationForm.getEmail());
          user.setPassword(registrationForm.getPassword());
          user.setUsername(registrationForm.getUsername());
          user.addRole(role);
          user.setEnabled(true);
          user.setAccountExpired(false);
          user.setCredentialsExpired(false);

          return user;
      }

      private User toUser(UserUpdateFormDto updateForm) throws UserNotRegisteredException {
          User user = userRepository
                  .findByEmail(updateForm.getDestinationEmail())
                  .orElseThrow(() -> new UserNotRegisteredException(updateForm.getDestinationEmail()));

          updateForm.setName(updateForm.getName() == null ? user.getName() : updateForm.getName());
          updateForm.setUsername(updateForm.getUsername() == null ? user.getUsername() : updateForm.getUsername());

          return user;
      }
   
 }
 