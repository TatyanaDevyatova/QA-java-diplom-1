package ingredientTests;

import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTests {
    Ingredient ingredient;

    String ingredientName = "beef cutlet";
    float ingredientPrice = 20.01f;

    @Before
    public void getBun() {
        ingredient = new Ingredient(IngredientType.FILLING, ingredientName, ingredientPrice);
    }

    @Test
    public void getNameShowsExpectedString() {
        // Arrange & Act
        String actualName = ingredient.getName();

        // Assert
        assertEquals("Было получено: " + actualName + ", вместо: " + ingredientName, ingredientName, actualName);
    }

    @Test
    public void getPriceShowsExpectedFloat() {
        // Arrange & Act
        float actualPrice = ingredient.getPrice();

        // Assert
        assertEquals("Было получено: " + actualPrice + ", вместо: " + ingredientPrice, ingredientPrice, actualPrice, 0);
    }
}
