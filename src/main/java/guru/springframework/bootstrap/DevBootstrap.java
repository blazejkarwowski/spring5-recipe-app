package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rolnik on 2017-11-01 17:42.
 * Project: spring5-recipe-app
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String TABLESPOON = "getOrAddUOM(TABLESPOON)";
    private static final String PIECE = "getOrAddUOM(PIECE)";
    private static final String DASH = "Dash";
    private static final String TEASPOON = "Teaspoon";
    private static final String CUP = "Cup";
    private static final String PINT = "Pint";
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setCategories(Collections.singleton(getOrAddCategory("Mexican")));
        perfectGuacamole.setCookTime(10 * 60);
        perfectGuacamole.setPrepTime(10 * 60);
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setSource("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, onion, and tomato, if you want.\n" +
                "\n" +
                "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n");
        Notes notes = new Notes();
        // notes.setRecipe(perfectGuacamole);
        notes.setRecipeNotes("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n");
        notes.setRecipe(perfectGuacamole);
        perfectGuacamole.setNotes(notes);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("ripe avocados", "2", getOrAddUOM(PIECE)));

        ingredients.add(new Ingredient("Kosher salt", "0.5", getOrAddUOM(TEASPOON)));
        ingredients.add(new Ingredient("fresh lime juice or lemon juice", "1", getOrAddUOM(TABLESPOON)));
        ingredients.add(new Ingredient("minced red onion or thinly sliced green onion", "2", getOrAddUOM(TABLESPOON)));
        ingredients.add(new Ingredient("serrano chiles, stems and seeds removed, minced", "2", getOrAddUOM(PIECE)));

        ingredients.add(new Ingredient("cilantro (leaves and tender stems), finely chopped", "2", getOrAddUOM(TABLESPOON)));
        ingredients.add(new Ingredient("freshly grated black pepper", "2", getOrAddUOM(DASH)));
        ingredients.add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", "0.5", getOrAddUOM(PIECE)));
        ingredients.stream().forEach((Ingredient ingredient) -> {
            ingredient.setRecipe(perfectGuacamole);
        });
        perfectGuacamole.setIngredients(ingredients);

        perfectGuacamole.setImage(getImageFromUrl("http://assets.simplyrecipes.com/wp-content/uploads/2009/05/guacamole-520-b.jpg"));

        recipeRepository.save(perfectGuacamole);


        Recipe chickenTacos = new Recipe();
        chickenTacos.setCategories(Collections.singleton(getOrAddCategory("Mexican")));
        chickenTacos.setCookTime(10 * 60);
        chickenTacos.setPrepTime(10 * 60);
        chickenTacos.setDifficulty(Difficulty.EASY);
        chickenTacos.setServings(4);
        chickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacos.setSource("\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "by Sally Vargas on May 22, 2017\n" +
                "Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.\n" +
                "Go to Recipe\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "Photography Credit: Sally Vargas\n" +
                "\n" +
                "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!" + "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");
        notes = new Notes();
        notes.setRecipeNotes("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        chickenTacos.setNotes(notes);

        chickenTacos.addIngredient(new Ingredient("ripe avocados", "2", getOrAddUOM(PIECE)));


        chickenTacos.addIngredient(new Ingredient("ancho chili powder", "2", getOrAddUOM(TABLESPOON)));
        chickenTacos.addIngredient(new Ingredient("dried oregano", "1", getOrAddUOM(TEASPOON)));
        chickenTacos.addIngredient(new Ingredient("dried cumin", "1", getOrAddUOM(TEASPOON)));
        chickenTacos.addIngredient(new Ingredient("sugar", "1", getOrAddUOM(TEASPOON)));
        chickenTacos.addIngredient(new Ingredient("salt", "0.5", getOrAddUOM(TEASPOON)));
        chickenTacos.addIngredient(new Ingredient("clove garlic, finely chopped", "1", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("finely grated orange zest", "1", getOrAddUOM(TABLESPOON)));
        chickenTacos.addIngredient(new Ingredient("fresh-squeezed orange juice", "3", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("olive oil", "2", getOrAddUOM(TABLESPOON)));
        chickenTacos.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", "6", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("small corn tortillas", "8", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("cups packed baby arugula (3 ounces)", "3", getOrAddUOM(CUP)));
        chickenTacos.addIngredient(new Ingredient("medium ripe avocados, sliced", "3", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("radishes, thinly sliced", "4", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("cherry tomatoes, halved", "0.5", getOrAddUOM(PINT)));
        chickenTacos.addIngredient(new Ingredient("red onion, thinly sliced", "0.25", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("roughly chopped cilantro", "1", getOrAddUOM(PIECE)));
        chickenTacos.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", "1", getOrAddUOM(CUP)));
        chickenTacos.addIngredient(new Ingredient("lime, cut into wedges", "1", getOrAddUOM(PIECE)));

        chickenTacos.setImage(getImageFromUrl("http://www.simplyrecipes.com/wp-content/uploads/2017/05/2017-05-29-GrilledChickenTacos-3.jpg"));
        chickenTacos.setIngredients(ingredients);
        recipeRepository.save(chickenTacos);
    }

    private Byte[] getImageFromUrl(String urlString) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URL url;
        try {
            url = new URL(urlString);
            try (InputStream inputStream = url.openStream()) {
                int n;
                byte[] buffer = new byte[1024];
                while (-1 != (n = inputStream.read(buffer))) {
                    output.write(buffer, 0, n);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] img = output.toByteArray();
        Byte[] bytes = new Byte[img.length];
        Arrays.setAll(bytes, n -> img[n]);
        return bytes;
    }

    private Category getOrAddCategory(String category) {
        return categoryRepository.findByDescription(category).orElseGet(() -> {
            Category c = new Category();
            c.setDescription(category);
            categoryRepository.save(c);
            return c;
        });
    }

    private UnitOfMeasure getOrAddUOM(String uom) {
        return unitOfMeasureRepository.findByDescription(uom).orElseGet(() -> {
            UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setDescription(uom);
            unitOfMeasureRepository.save(unitOfMeasure);
            return unitOfMeasure;
        });
    }
}
