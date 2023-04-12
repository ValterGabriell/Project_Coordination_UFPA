package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.*;
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

    @PutMapping(value = "update-title", params = {"postId"})
    public ResponseEntity<CustomResponse<String>> updatePostTitle(@RequestBody UpdateTitle updateTitle, @RequestParam String postId) throws ApiExceptions {
        CustomResponse<String> postWithTitleUpdated = postService.updateTitle(updateTitle, postId);
        return new ResponseEntity<>(postWithTitleUpdated, HttpStatus.OK);
    }

    @PutMapping(value = "update-body", params = {"postId"})
    public ResponseEntity<CustomResponse<String>> updatePostBody(@RequestBody UpdateBody updateBody, @RequestParam String postId) throws ApiExceptions {
        CustomResponse<String> postWithTitleUpdated = postService.updateBody(updateBody, postId);
        return new ResponseEntity<>(postWithTitleUpdated, HttpStatus.OK);
    }

    @PutMapping(value = "update-link", params = {"postId"})
    public ResponseEntity<CustomResponse<String>> updatePostLink(@RequestBody UpdateLink updateLink, @RequestParam String postId) throws ApiExceptions {
        CustomResponse<String> postWithTitleUpdated = postService.updateLink(updateLink, postId);
        return new ResponseEntity<>(postWithTitleUpdated, HttpStatus.OK);
    }

    @DeleteMapping(params = {"postId"})
    public ResponseEntity deletePostById(@RequestParam String postId) throws ApiExceptions {
        postService.deletePostById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
