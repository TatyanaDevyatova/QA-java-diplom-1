package burgertests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient fillingIngredient;
    @Mock
    private Ingredient sauceIngredient;

    private final String bunName = "potato bun";
    private final float bunPrice = 0.05f;
    private final IngredientType fillingIngredientType = IngredientType.FILLING;
    private final String fillingIngredientName = "beef cutlet";
    private final float filingIngredientPrice = 1f;
    private final IngredientType sauceIngredientType = IngredientType.SAUCE;
    private final String sauceIngredientName = "cranberry sauce";
    private final float sauceIngredientPrice = 0.05f;
    private final float expectedBurgerPrice = 1.15f;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @After
    public void clear() {
        Mockito.reset(bun);
        Mockito.reset(fillingIngredient);
        Mockito.reset(sauceIngredient);
    }

    @Test
    public void setBunsAddsExpectedBun() {
        // Arrange
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        // Act
        burger.setBuns(bun);
        String actualName = burger.bun.getName();
        float actualPrice = burger.bun.getPrice();

        // Assert
        assertEquals("Было получено неверное имя булки", bunName, actualName);
        assertEquals("Была получена неверная цена булки", bunPrice, actualPrice, 0);
    }

    @Test
    public void addIngredientAddsExpectedIngredient() {
        // Arrange
        int expectedSize = 1;
        List<Ingredient> expectedIngredients = List.of(fillingIngredient);

        // Act
        burger.addIngredient(fillingIngredient);
        int actualSize = burger.ingredients.size();
        List<Ingredient> actualIngredients = burger.ingredients;

        // Assert
        assertEquals("Было получено неверное количество ингредиентов", expectedSize, actualSize);
        assertTrue(burger.ingredients.contains(fillingIngredient));
        assertEquals("Был получен неверный список ингредиентов", actualIngredients.toString(), expectedIngredients.toString());
    }

    @Test
    public void removeIngredientDeletesExpectedIngredient() {
        // Arrange
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        int expectedSize = 1;
        List<Ingredient> expectedIngredients = List.of(sauceIngredient);

        // Act
        burger.removeIngredient(0);
        int actualSize = burger.ingredients.size();
        List<Ingredient> actualIngredients = burger.ingredients;

        // Assert
        assertEquals("Было получено неверное количество ингредиентов", expectedSize, actualSize);
        assertTrue(burger.ingredients.contains(sauceIngredient));
        assertEquals("Был получен неверный список ингредиентов", actualIngredients.toString(), expectedIngredients.toString());
    }

    @Test
    public void moveIngredientSetsExpectedIngredientIndex() {
        // Arrange
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        int expectedSize = 2;
        int expectedIndex = 1;
        List<Ingredient> expectedIngredients = List.of(sauceIngredient, fillingIngredient);

        // Act
        burger.moveIngredient(0, expectedIndex);
        int actualSize = burger.ingredients.size();
        int actualIndex = burger.ingredients.indexOf(fillingIngredient);
        List<Ingredient> actualIngredients = burger.ingredients;

        // Assert
        assertEquals("Было получено неверное количество ингредиентов", expectedSize, actualSize);
        assertEquals("Был получен неверный индекс ингредиента", expectedIndex, actualIndex);
        assertEquals("Был получен неверный список ингредиентов", actualIngredients.toString(), expectedIngredients.toString());
    }

    @Test
    public void getPriceReturnsExpectedPrice() {
        // Arrange
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(fillingIngredient.getPrice()).thenReturn(filingIngredientPrice);
        Mockito.when(sauceIngredient.getPrice()).thenReturn(sauceIngredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        // Act
        float actualPrice = burger.getPrice();

        // Assert
        assertEquals("Была получена неверная цена бургера", expectedBurgerPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptReturnsExpectedText() {
        // Arrange
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Mockito.when(fillingIngredient.getType()).thenReturn(fillingIngredientType);
        Mockito.when(fillingIngredient.getName()).thenReturn(fillingIngredientName);
        Mockito.when(fillingIngredient.getPrice()).thenReturn(filingIngredientPrice);

        Mockito.when(sauceIngredient.getType()).thenReturn(sauceIngredientType);
        Mockito.when(sauceIngredient.getName()).thenReturn(sauceIngredientName);
        Mockito.when(sauceIngredient.getPrice()).thenReturn(sauceIngredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        StringBuilder expectedReceipt = new StringBuilder();
        expectedReceipt.append(String.format("(==== %s ====)%n", bunName));
        expectedReceipt.append(String.format("= %s %s =%n", fillingIngredientType.toString().toLowerCase(), fillingIngredientName));
        expectedReceipt.append(String.format("= %s %s =%n", sauceIngredientType.toString().toLowerCase(), sauceIngredientName));
        expectedReceipt.append(String.format("(==== %s ====)%n", bunName));
        expectedReceipt.append(String.format("%nPrice: %f%n", expectedBurgerPrice));

        // Act
        String actualReceipt = burger.getReceipt();

        // Assert
        assertEquals("Был получен неверный рецепт бургера", expectedReceipt.toString(), actualReceipt);
    }
}
