package dev.API.service;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.API.dto.SigninDto;
import dev.API.dto.SignupDto;
import dev.API.entity.Roles;
import dev.API.entity.User;
import dev.API.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final  JwtService jwtService;
    private final UserService userService;

    public ResponseEntity<?> sigup(SignupDto dto){

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username aleredy exist.");
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email aleredy exist.");
        }
        
        String passwordEncoded = encoder.encode(dto.getPassword());

        User user = new User(dto.getUsername(), dto.getEmail(), passwordEncoded , Roles.ROLE_USER);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public ResponseEntity<?> signin(SigninDto dto){
        try {
            
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
    
            SecurityContextHolder.getContext().setAuthentication(auth);
    
            String token = jwtService.generateToken(userRepository.findByUsername(dto.getUsername()).get());
            
            return ResponseEntity.status(HttpStatus.OK).body(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("bad credentials")); 
        }
    }

    public ResponseEntity<?> updatePassword(String currentPassword , String newPassword , String username){

        /*
         * The loadUserByUsername method returns a UserDetails object, but you're 
         * explicitly casting it to a User type. If User implements UserDetails, 
         * this cast allows you to access the methods or properties specific to the 
         * User class that are not available in the UserDetails interface.
            . In this scenario, it might be considered more as a type conversion rather
             than typical downcasting.
         */
        
        User user = (User) userService.userDetailsService().loadUserByUsername(username);
        
        if(!encoder.matches(currentPassword, user.getPassword()))
        {
            return ResponseEntity.status(403).body("incorrect current password");
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("password updated");
    }


}
