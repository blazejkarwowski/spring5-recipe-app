package guru.springframework.controllers;


import guru.springframework.repositories.RecipeRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;


/**
 * Created by rolnik on 2017-11-02 08:13.
 * Project: spring5-recipe-app
 */
@Controller
public class ImageController {

    private RecipeRepository recipeRepository;

    public ImageController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@RequestParam("id") Long recipeId) {
        Byte[] img = recipeRepository.findById(recipeId).get().getImage();
        byte[] image = new byte[img.length];
        for (int i = 0; i < image.length; i++) {
            image[i] = img[i];
        }

        return ResponseEntity.ok().contentLength(image.length).contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(new ByteArrayInputStream(image)));
    }
}
