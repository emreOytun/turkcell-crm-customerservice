import com.turkcell.pair3.customerservice.entities.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    private City city;

    @BeforeEach
    public void setUp() {
        city = new City();
        city.setId(1);
        city.setCityName("Sample City");
    }

    @Test
    public void testCityInitialization() {
        assertNotNull(city);
        assertEquals(1, city.getId());
        assertEquals("Sample City", city.getCityName());
    }

    @Test
    public void testCitySettersAndGetters() {
        city.setCityName("Updated City");

        assertEquals("Updated City", city.getCityName());
    }

    @Test
    public void testCityConstructor() {
        City newCity = new City(2, "Another City");

        assertNotNull(newCity);
        assertEquals(2, newCity.getId());
        assertEquals("Another City", newCity.getCityName());
    }
}
