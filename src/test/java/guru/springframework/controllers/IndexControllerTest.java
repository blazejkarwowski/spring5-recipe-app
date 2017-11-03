package guru.springframework.controllers;

import guru.springframework.bootstrap.services.RecipeService;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by rolnik on 2017-11-03 16:59.
 * Project: spring5-recipe-app
 */
public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipesData);

        assertEquals("index", indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(model, times(1)).addAttribute("recipes", recipesData);
    }
}