package dev.API.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , unique = true)
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String body;

    @CreationTimestamp //automatically set the current date/time when an entity is first persisted (created). 
    private Date createdAt;

    @UpdateTimestamp //be updated with the current timestamp whenever this entity is modified and persisted. 
    private Date updatedAt;
 
    public Post(String title, String body, List<Tags> tags) {
        this.title = title;
        this.body = body;
        this.tags = tags;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "posts_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tags> tags = new ArrayList<>();

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", body=" + body + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + "]";
    }


    
}
