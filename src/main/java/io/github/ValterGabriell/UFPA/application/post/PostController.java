package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.CustomResponse;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CustomResponse<PostResponse>> createNewPost(
            @RequestParam("body") @NonNull String body,
            @RequestParam("title") @NonNull String title,
            @RequestParam("link") @NonNull String link,
            @RequestParam("Authorization") @NonNull String token,
            @RequestPart("image") @NonNull MultipartFile image) throws ApiExceptions, IOException, ExecutionException, InterruptedException {
        CustomResponse<PostResponse> post = postService.createPost(body, title, link, image, token);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping(params = {"postId"})
    public ResponseEntity<CustomResponse<PostResponse>> getPostById(@RequestParam String postId) {
        CustomResponse<PostResponse> imageByHashImage = postService.findPostById(postId);
        return new ResponseEntity<>(imageByHashImage, HttpStatus.OK);
    }


}
