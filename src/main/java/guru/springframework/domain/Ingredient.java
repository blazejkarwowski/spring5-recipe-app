package guru.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rolnik on 2017-11-01 13:09.
 * Project: spring5-recipe-app
 */
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;


    public Ingredient(String description, String amount, UnitOfMeasure uom/*, Recipe recipe*/) {
        this.description = description;
        this.amount = new BigDecimal(amount);
        this.uom = uom;
        /*this.recipe = recipe;*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public void setAmount(Integer amount) {
        this.amount = new BigDecimal(amount);
    }


    public void setAmount(Double amount) {
        this.amount = new BigDecimal(amount);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public void setAmount(String amount) {
        this.amount = new BigDecimal(amount);
    }
}
