package io.github.ValterGabriell.UFPA.application.post;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.application.post.domain.Post;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.CustomResponse;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostRequestCreate;
import io.github.ValterGabriell.UFPA.application.post.domain.dto.PostResponse;
import io.github.ValterGabriell.UFPA.infra.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public CustomResponse<PostResponse> createPost(PostRequestCreate postRequestCreate) throws ApiExceptions {


        if (postRequestCreate.getTitle().isEmpty() || postRequestCreate.getTitle().isBlank() || postRequestCreate.getTitle() == null){
            String titleEmpty = "O título não pode estar vazio!";
            throw new ApiExceptions(titleEmpty);
        } else if (postRequestCreate.getBody().isEmpty() || postRequestCreate.getBody().isBlank() || postRequestCreate.getBody() == null){
            String titleEmpty = "O corpo não pode estar vazio!";
            throw new ApiExceptions(titleEmpty);
        }


        //save at database
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


}