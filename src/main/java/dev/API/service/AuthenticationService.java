package dev.API.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import dev.API.model.dto.LoginDto;
import dev.API.model.dto.LoginResponseDto;
import dev.API.model.dto.NewAuthorDto;
import dev.API.model.entity.Author;
import dev.API.model.entity.Roles;
import dev.API.model.entity.RootUser;
import dev.API.repository.AuthorRepository;
import dev.API.repository.UserRepository;

@Service
public class AuthenticationService {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewAccount( NewAuthorDto dto){

        if (userRepository.findByUsername(dto.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists!");
        }
        if (authorRepository.findByEmail(dto.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already exists!");
        }

        String passwordEncoded = passwordEncoder.encode(dto.getPassword());
        
        List<Roles> authority = new ArrayList<>();

        authority.add(new Roles("USER"));

        RootUser rootUser = new RootUser(dto.getUsername() , passwordEncoded , new Author(dto.getEmail()),authority);

        userRepository.save(rootUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(rootUser);
    }



    public ResponseEntity<?> login(LoginDto dto){

        try {
            Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            String token = tokenService.generateToken(auth);
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(userRepository.findByUsername(dto.getUsername()).getUsername() ,token ));
            
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("bad credentials"))); 
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
        
        RootUser user = (RootUser) userService.loadUserByUsername(username);
        
        if(!passwordEncoder.matches(currentPassword, user.getPassword()))
        {
            return ResponseEntity.status(403).body("incorrect current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("password updated");
    }
}
