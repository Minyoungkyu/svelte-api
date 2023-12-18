package com.yk.demo.domain.post.post.controller;

import com.yk.demo.domain.member.member.entity.Member;
import com.yk.demo.domain.post.post.dto.PostDto;
import com.yk.demo.domain.post.post.entity.Post;
import com.yk.demo.domain.post.post.service.PostService;
import com.yk.demo.global.rq.Rq.Rq;
import com.yk.demo.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.ALL_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/posts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "ApiV1ArticlesController", description = "게시물 CRUD 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
public class ApiV1PostsController {
    private final Rq rq;
    private final PostService postService;

    @Getter
    public static class GetMineResponseBody {
        @NonNull
        private final List<PostDto> items;

        public GetMineResponseBody(List<Post> posts) {
            this.items = posts
                    .stream()
                    .map(PostDto::new)
                    .toList();
        }
    }

    @GetMapping(value = "/mine", consumes = ALL_VALUE)
    @Operation(summary = "내 글 리스트")
    public RsData<GetMineResponseBody> getMine() {
        Member member = rq.getMember();

        List<Post> posts = postService.findByAuthor(member);

        return RsData.of(
                "200",
                "내 글 가져오기 성공",
                new GetMineResponseBody(posts)
        );
    }
}
