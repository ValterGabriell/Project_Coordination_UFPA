package io.github.ValterGabriell.UFPA.infra.api;

import io.github.ValterGabriell.UFPA.application.exceptions.ApiExceptions;
import io.github.ValterGabriell.UFPA.infra.api.dto.ResponseImageDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public abstract class WebClientHttp {
    public final String BASE_URL = "https://api.imgur.com/3/";

    public RestTemplate getRestTemplateInstance() {
        return new RestTemplate();
    }

    abstract ResponseImageDTO getImage(String hashImage, String token) throws ApiExceptions;

    abstract String sendImageToImgurAndReturnLinkForAccessIt(String title, MultipartFile image, String token) throws IOException;
}
