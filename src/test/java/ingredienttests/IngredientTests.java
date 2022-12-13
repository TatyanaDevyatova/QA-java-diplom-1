package ingredienttests;

import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTests {
    private Ingredient ingredient;

    private final String ingredientName = "beef cutlet";
    private final float ingredientPrice = 20.01f;

    @Before
    public void getBun() {
        ingredient = new Ingredient(IngredientType.FILLING, ingredientName, ingredientPrice);
    }

    @Test
    public void getNameShowsExpectedString() {
        // Arrange & Act
        String actualName = ingredient.getName();

        // Assert
        assertEquals("Было получено неверное имя ингредиента", ingredientName, actualName);
    }

    @Test
    public void getPriceShowsExpectedFloat() {
        // Arrange & Act
        float actualPrice = ingredient.getPrice();

        // Assert
        assertEquals("Была получена невеная цена ингредиента", ingredientPrice, actualPrice, 0);
    }
}