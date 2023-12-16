package dev.API.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class RootUser implements UserDetails {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Author authors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role_join_tbl",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> authority = new ArrayList<>();
    
    @Override
    public String toString() {
        return "RootUser [id=" + id + ", username=" + username + ", password=" + password + " [Authors: id=" + authors.getId() + ", email=" + authors.getEmail() +  "]" ;
    }

    public Author getAuthor(){
        return this.authors;
    } 

    public RootUser(String username, String password, Author authors, List<Roles> authority) {
        this.username = username;
        this.password = password;
        this.authors = authors;
        this.authority = authority;
    }


    public RootUser(String username, String password, List<Roles> authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.authority;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }



    @Override
    public boolean isAccountNonLocked() {
       return true;
    }



    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }



    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
