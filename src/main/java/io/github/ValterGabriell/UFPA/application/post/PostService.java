package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.Post;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.CustomResponse;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostRequestCreate;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import io.github.ValterGabriell.UFPA.infra.api.ImgurAPI;
import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import io.github.ValterGabriell.UFPA.infra.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImgurAPI imgurAPI;


    public CustomResponse<PostResponse> createPost(String body, String title, String link, MultipartFile image, String token) throws ApiExceptions, IOException, InterruptedException, ExecutionException {
        CompletableFuture<String> imgurAPICallFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return imgurAPI.sendImageToImgur(title, image, token);
            } catch (IOException e) {
                return e.getMessage();
            }
        });
        String imgRef = imgurAPICallFuture.get();

        CustomResponse<PostResponse> customResponse = saveAtDatabaseAndReturnResponse(body, title, link, imgRef);
        String headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("postId=" + customResponse.getData().getPostId()).build().toUriString();
        customResponse.setMessage(headerLocation);
        return customResponse;
    }

    private CustomResponse<PostResponse> saveAtDatabaseAndReturnResponse(String body, String title, String link, String imgRef) {
        //save at database
        PostRequestCreate postRequestCreate = new PostRequestCreate(body, title, imgRef, link);
        Post post = postRequestCreate.toModel();
        post.setPostedAt(LocalDate.now());
        post.setPostId(verifyIfIdAlreadyExistAndCreateWhenIsNot());
        postRepository.save(post);

        //creating response
        PostResponse postResponse = new PostResponse();
        postResponse.setPostedAt(post.getPostedAt());
        postResponse.setBody(post.getBody());
        postResponse.setTitle(post.getTitle());
        postResponse.setImgRef(post.getImgRef());
        postResponse.setPostId(post.getPostId());

        CustomResponse<PostResponse> customResponse = new CustomResponse<>();
        customResponse.setData(postResponse);
        customResponse.setSuccessful(true);
        return customResponse;
    }

    private String verifyIfIdAlreadyExistAndCreateWhenIsNot() {
        String postId = UUID.randomUUID().toString();
        Optional<Post> postRetrive = postRepository.findById(postId);
        boolean idPresent = postRetrive.isPresent();
        if (idPresent) {
            String id = postRetrive.get().getPostId();
            while (id.equals(postId)) {
                postId = UUID.randomUUID().toString();
            }
        }
        return postId;
    }

    public ResponseImageDTO getImageByHashImage(String hashImage, String token) {
        return imgurAPI.getImage(hashImage, token);
    }

    public CustomResponse<PostResponse> findPostById(String postId) {
        CustomResponse<PostResponse> customResponse = new CustomResponse<>();
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            customResponse.setData(null);
            customResponse.setMessage("Post não encontrado!");
            customResponse.setSuccessful(false);
            return customResponse;
        } else {
            customResponse.setData(post.get().toPostResponse());
            customResponse.setSuccessful(true);
        }
        return customResponse;
    }


}