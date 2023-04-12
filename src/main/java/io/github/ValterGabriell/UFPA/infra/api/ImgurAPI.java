package io.github.ValterGabriell.UFPA.infra.api;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
public class ImgurAPI extends WebClientHttp {


    @Override
    public ResponseImageDTO getImage(String hashImage, String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);

        HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ResponseImageDTO> response = getRestTemplateInstance()
                .exchange(BASE_URL + "image/" + hashImage, HttpMethod.GET, requestEntity, ResponseImageDTO.class);

        return response.getBody();
    }

    @Override
    public synchronized String sendImageToImgur(String title, MultipartFile image, String token) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        });
        body.add("title", title);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<ResponseImageDTO> responseEntity = getRestTemplateInstance()
                .postForEntity(BASE_URL + "image", requestEntity, ResponseImageDTO.class);

        return Objects.requireNonNull(responseEntity.getBody()).getData().getLink();
    }
}
