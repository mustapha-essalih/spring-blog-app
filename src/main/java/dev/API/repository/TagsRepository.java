package dev.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.API.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags , Integer>{
}
