package guru.springframework.controllers;

import guru.springframework.bootstrap.services.RecipeService;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

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
        //given
        Set<Recipe> recipesData = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setDescription("1");
        recipesData.add(recipe);
        recipe = new Recipe();
        recipe.setDescription("2");
        recipesData.add(recipe);
        recipe = new Recipe();
        recipe.setDescription("3");
        recipesData.add(recipe);
        recipe = new Recipe();
        recipe.setDescription("1");
        recipesData.add(recipe);


        when(recipeService.getRecipes()).thenReturn(recipesData);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when

        //then
        assertEquals("index", indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(model, times(1)).addAttribute("recipes", recipesData);
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(3, setInController.size());
    }
}