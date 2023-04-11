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
import reactor.core.publisher.Mono;

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

    public synchronized CustomResponse<PostResponse> createPost(String body, String title, String link, MultipartFile image, String token) throws ApiExceptions, IOException, InterruptedException, ExecutionException {


        if (title.isEmpty() || title.isBlank()) {
            String titleEmpty = "O título não pode estar vazio!";
            throw new ApiExceptions(titleEmpty);
        } else if (body.isEmpty() || body.isBlank()) {
            String titleEmpty = "O corpo não pode estar vazio!";
            throw new ApiExceptions(titleEmpty);
        }

        CompletableFuture<String> imgurAPICallFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                String imageToImgur = imgurAPI.sendImageToImgur(title, image, token);
                imgurAPICallFuture.complete(imageToImgur);
            } catch (IOException e) {
                imgurAPICallFuture.complete(e.getMessage());
            }
        }).start();
        String imgRef = imgurAPICallFuture.get();


        //save at database
        PostRequestCreate postRequestCreate = new PostRequestCreate(body, title, imgRef, link);
        Post post = postRequestCreate.toModel();
        post.setPostedAt(LocalDate.now());
        post.setPostId(verifyIfIdAlreadyExistAndCreateWhenIsNot());

        //creating response
        PostResponse postResponse = new PostResponse();
        postResponse.setPostedAt(post.getPostedAt());
        postResponse.setBody(post.getBody());
        postResponse.setTitle(post.getTitle());
        postResponse.setImgRef(post.getImgRef());

        CustomResponse<PostResponse> customResponse = new CustomResponse<>();
        customResponse.setMessage("Post criado com sucesso!");
        customResponse.setObject(postResponse);
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

    public Mono<ResponseImageDTO> getImageByHashImage(String hashImage, String token) {
        return imgurAPI.getImage(hashImage, token);
    }


}