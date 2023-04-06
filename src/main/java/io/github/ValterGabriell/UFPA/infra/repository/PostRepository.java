package io.github.ValterGabriell.UFPA.infra.repository;

import io.github.ValterGabriell.UFPA.application.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
