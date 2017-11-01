package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rolnik on 2017-11-01 13:49.
 * Project: spring5-recipe-app
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
