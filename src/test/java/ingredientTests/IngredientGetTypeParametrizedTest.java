package ingredientTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientGetTypeParametrizedTest {
    private final IngredientType type;
    private final IngredientType expectedType;

    public IngredientGetTypeParametrizedTest(IngredientType type, IngredientType expectedType) {
        this.type = type;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {IngredientType.FILLING, IngredientType.FILLING},
                {IngredientType.SAUCE, IngredientType.SAUCE},
        };
    }

    @Test
    public void getTypeShowsExpectedEnum() {
        // Arrange & Act
        Ingredient ingredient = new Ingredient(type, "ingredient", 0.88f);
        IngredientType actualType = ingredient.getType();

        // Assert
        assertEquals("Было получено: " + actualType + ", вместо: " + expectedType, expectedType, actualType);
    }
}

