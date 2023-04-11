package io.github.ValterGabriell.UFPA.infra.api;

import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

public abstract class WebClientHttp {

    public WebClient getWebClientInstance() {
        String BASE_URL = "https://api.imgur.com/3/";
        return WebClient.create(BASE_URL);
    }

    abstract Mono<ResponseImageDTO> getImage(String hashImage, String token);

    abstract String sendImageToImgur(String title, MultipartFile image, String token) throws IOException;
}
