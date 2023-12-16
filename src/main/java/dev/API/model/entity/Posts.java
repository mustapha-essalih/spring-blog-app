package dev.API.model.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Posts {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    @CreationTimestamp
    private Date publishedOn;

    @Column(nullable = false)
    @CreationTimestamp
    private Date updatedOn;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public Posts(String title, String body, Date publishedOn, Date updatedOn, Author author) {
        this.title = title;
        this.body = body;
        this.publishedOn = publishedOn;
        this.updatedOn = updatedOn;
        this.author = author;
    }

    
    public Posts(String title, String body) {
        this.title = title;
        this.body = body; 
    }

    public Posts(String title) {
        this.title = title;
    }
}
