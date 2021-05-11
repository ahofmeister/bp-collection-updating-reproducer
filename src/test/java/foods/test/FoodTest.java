package foods.test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.Food;
import org.acme.FoodUnit;
import org.acme.FoodUnitMapping;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class FoodTest {

    @Inject
    EntityManager entityManager;

    @Test
    @TestTransaction
    public void testMappingWithoutBP() {
        Set<FoodUnitMapping> unitMappings = new HashSet<>();
        unitMappings.add(new FoodUnitMapping(200, FoodUnit.GRAM));
        unitMappings.add(new FoodUnitMapping(12, FoodUnit.GRAM));
        Food food = createFood(unitMappings);

        entityManager.merge(food);
        food = findFood();
        assertEquals(2, food.unitMappings.size());
    }

    @Test
    @TestTransaction
    public void testPatchWithViews() {
        Set<FoodUnitMapping> unitMappings = new HashSet<>();
        unitMappings.add(new FoodUnitMapping(200, FoodUnit.GRAM));
        Food food = createFood(unitMappings);
        given().contentType(ContentType.JSON).body(food)
                .when().post("foods");

        verifyUnitMappings(1);

        // patch food
        food.unitMappings.add(new FoodUnitMapping(100, FoodUnit.PORTION));
        food.name = "Banana";
        given().pathParam("id", food.id).body(food).contentType(ContentType.JSON).patch("foods/{id}");

        verifyUnitMappings(2);
    }

    private void verifyUnitMappings(int size) {
        Food foundFood = findFood();
        assertEquals(size, foundFood.unitMappings.size());
    }


    private Food createFood(Set<FoodUnitMapping> unitMappings) {
        Food food = new Food();
        String id = "12";
        food.name = "Apple";
        food.id = id;
        food.unitMappings = unitMappings;
        return food;
    }

    private Food findFood() {
        return entityManager.find(Food.class, "12");
    }

}
