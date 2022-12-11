package bunTests;

import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTests {
    private Bun bun;

    private final String bunName = "potato bun";
    private final float bunPrice = 1.05f;

    @Before
    public void getBun() {
        bun = new Bun(bunName, bunPrice);
    }

    @Test
    public void getNameShowsExpectedString() {
        // Arrange & Act
        String actualName = bun.getName();

        // Assert
        assertEquals("Было получено: " + actualName + ", вместо: " + bunName, bunName, actualName);
    }

    @Test
    public void getPriceShowsExpectedFloat() {
        // Arrange & Act
        float actualPrice = bun.getPrice();

        // Assert
        assertEquals("Было получено: " + actualPrice + ", вместо: " + bunPrice, bunPrice, actualPrice, 0);
    }
}
