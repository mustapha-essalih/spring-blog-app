package dev.API.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tags {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , unique = true)
    private String tagName;

    public Tags(String tagName) {
        this.tagName = tagName;
    }


    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "Tags [id=" + id + ", tagName=" + tagName + "]";
    }

    
}
