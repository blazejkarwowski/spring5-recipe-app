package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rolnik on 2017-11-01 12:55.
 * Project: spring5-recipe-app
 */
@Data
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    @Lob
    private String source;
    private String url;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @Lob
    private byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new HashSet<>();
        }
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }
}
