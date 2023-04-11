package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.CustomResponse;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CustomResponse<PostResponse>> createNewPost(
            @RequestParam("body") String body,
            @RequestParam("title") String title,
            @RequestParam("link") String link,
            @RequestParam("Authorization") String token,
            @RequestPart("image") MultipartFile image) throws ApiExceptions, IOException, ExecutionException, InterruptedException {
        CustomResponse<PostResponse> post = postService.createPost(body, title, link, image, token);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/image/{hashImage}")
    public ResponseEntity<Mono<ResponseImageDTO>> getImageByHash(@PathVariable String hashImage, @RequestHeader String Authorization) throws ApiExceptions {
        Mono<ResponseImageDTO> imageByHashImage = postService.getImageByHashImage(hashImage, Authorization);
        return new ResponseEntity<>(imageByHashImage, HttpStatus.OK);
    }


}
