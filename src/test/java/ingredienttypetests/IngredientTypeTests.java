package ingredienttypetests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final IngredientType type;
    private final IngredientType expectedType;

    public IngredientTypeTests(IngredientType type, IngredientType expectedType) {
        this.type = type;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} | {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {IngredientType.FILLING, IngredientType.valueOf("FILLING")},
                {IngredientType.SAUCE, IngredientType.valueOf("SAUCE")},
        };
    }

    @Test
    public void getTypeShowsExpectedEnum() {
        // Arrange & Act
        Ingredient ingredient = new Ingredient(type, "ingredient", 0.88f);
        IngredientType actualType = ingredient.getType();

        // Assert
        assertEquals("Был получен неверный тип ингредиента", expectedType, actualType);
    }
}

