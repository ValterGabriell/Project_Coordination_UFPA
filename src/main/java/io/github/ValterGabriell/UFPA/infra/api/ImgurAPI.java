package io.github.ValterGabriell.UFPA.infra.api;

import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

@Component
public class ImgurAPI extends WebClientHttp {


    @Override
    public Mono<ResponseImageDTO> getImage(String hashImage, String token) {
        return getWebClientInstance()
                .get()
                .uri("image/{hashImage}", hashImage)
                .header("Authorization", "Bearer " + token)
                .retrieve().bodyToMono(ResponseImageDTO.class);
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

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseImageDTO> responseEntity = restTemplate.postForEntity("https://api.imgur.com/3/image", requestEntity, ResponseImageDTO.class);

        return Objects.requireNonNull(responseEntity.getBody()).getData().getLink();
    }
}
