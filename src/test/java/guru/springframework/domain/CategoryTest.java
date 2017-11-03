package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rolnik on 2017-11-03 16:35.
 * Project: spring5-recipe-app
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}