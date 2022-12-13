package ingredienttypetests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final String typeValue;
    private final IngredientType expectedType;

    public IngredientTypeTests(String typeValue, IngredientType expectedType) {
        this.typeValue = typeValue;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} | {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {"FILLING", IngredientType.FILLING},
                {"SAUCE", IngredientType.SAUCE},
        };
    }

    @Test
    public void getTypeShowsExpectedEnum() {
        // Arrange & Act
        Ingredient ingredient = new Ingredient(IngredientType.valueOf(typeValue), "ingredient", 0.88f);
        IngredientType actualType = ingredient.getType();

        // Assert
        assertEquals("Был получен неверный тип ингредиента", expectedType, actualType);
    }
}