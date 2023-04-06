package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.CustomResponse;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostRequestCreate;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity createNewPost(@RequestBody PostRequestCreate postRequestCreate) throws ApiExceptions {
        CustomResponse<PostResponse> post = postService.createPost(postRequestCreate);
        return ResponseEntity.ok(post);
    }

}
