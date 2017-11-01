package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rolnik on 2017-11-01 13:48.
 * Project: spring5-recipe-app
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
