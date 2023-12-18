package dev.API.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.API.dto.LoginResponseDto;
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

    public ResponseEntity<?> sigup(SignupDto dto){

        if (userRepository.findUserByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username aleredy exist.");
        }

        if (userRepository.findUserByEmail(dto.getEmail()).isPresent()) {
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
    
            String token = jwtService.generateToken(userRepository.findUserByUsername(dto.getUsername()).get());
            
            return ResponseEntity.status(HttpStatus.OK).body(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new String("bad credentials")); 
        }
    }


}
