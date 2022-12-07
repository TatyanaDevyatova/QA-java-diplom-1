package burgerTests;

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
    Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient fillingIngredient;
    @Mock
    Ingredient sauceIngredient;

    String bunName = "potato bun";
    float bunPrice = 0.05f;

    IngredientType fillingIngredientType = IngredientType.FILLING;
    String fillingIngredientName = "beef cutlet";
    float filingIngredientPrice = 1f;

    IngredientType sauceIngredientType = IngredientType.SAUCE;
    String sauceIngredientName = "cranberry sauce";
    float sauceIngredientPrice = 0.05f;

    float expectedBurgerPrice = 1.15f;

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
        assertEquals("Было получено: " + actualName + ", вместо: " + bunName, bunName, actualName);
        assertEquals("Было получено: " + actualPrice + ", вместо: " + bunPrice, bunPrice, actualPrice, 0);
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
        assertEquals("Было получено: " + actualSize + ", вместо: " + expectedSize, expectedSize, actualSize);
        assertTrue(burger.ingredients.contains(fillingIngredient));
        assertEquals("Было получено: " + actualIngredients + ", вместо: " + expectedIngredients, actualIngredients.toString(), expectedIngredients.toString());
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
        assertEquals("Было получено: " + actualSize + ", вместо: " + expectedSize, expectedSize, actualSize);
        assertTrue(burger.ingredients.contains(sauceIngredient));
        assertEquals("Было получено: " + actualIngredients + ", вместо: " + expectedIngredients, actualIngredients.toString(), expectedIngredients.toString());
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
        assertEquals("Было получено: " + actualSize + ", вместо: " + expectedSize, expectedSize, actualSize);
        assertEquals("Было получено: " + actualIndex + ", вместо: " + expectedIndex, expectedIndex, actualIndex);
        assertEquals("Было получено: " + actualIngredients + ", вместо: " + expectedIngredients, actualIngredients.toString(), expectedIngredients.toString());
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
        assertEquals("Было получено: " + actualPrice + ", вместо: " + expectedBurgerPrice, expectedBurgerPrice, actualPrice, 0);
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
        assertEquals("Было получено: " + actualReceipt + ", вместо: " + expectedReceipt, expectedReceipt.toString(), actualReceipt);
    }
}
