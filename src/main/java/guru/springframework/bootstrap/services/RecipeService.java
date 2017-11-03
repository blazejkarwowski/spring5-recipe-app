package guru.springframework.bootstrap.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by rolnik on 2017-11-03 16:42.
 * Project: spring5-recipe-app
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
