package io.github.ValterGabriell.UFPA.application.post;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {


    @ParameterizedTest
    @ValueSource(strings = {"eedbe1c8-cf03-4f24-8fd0-89ce67269617", "eedbec8-c103-4f64-8fd03-89ce672696017"})
    void itShouldPassWhenIdHasCameFromDBAreNotEqualsNewIdCreated(String idHasComeFromDatabase) {
        String postId = UUID.randomUUID().toString();

        while (idHasComeFromDatabase.equals(postId)) {
            postId = UUID.randomUUID().toString();
        }


        assertNotEquals(postId, idHasComeFromDatabase);

    }

}