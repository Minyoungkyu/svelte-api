package com.yk.demo.domain.post.post.repository;

import com.yk.demo.domain.member.member.entity.Member;
import com.yk.demo.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Member author);
}
