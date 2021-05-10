package foods.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.acme.Food;
import org.acme.FoodResource;
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
    public void test() {
        // create food
        Food food = new Food();
        String id = "12";
        food.name = "Apple";
        food.id = id;

        Set<FoodUnitMapping> unitMappings = new HashSet<>();
        unitMappings.add(new FoodUnitMapping(200, FoodUnit.GRAM));

        food.unitMappings = unitMappings;

        given().contentType(ContentType.JSON).body(food)
                .when().post("foods");


        // verify created food
        Food foundFood = findFoood();
        assertEquals(1, foundFood.unitMappings.size());


        // patch food
        food.unitMappings.add(new FoodUnitMapping(100, FoodUnit.GRAM));
        food.name = "Banana";
        given().pathParam("id", id).body(food).contentType(ContentType.JSON).patch("foods/{id}");

        // verify patched food
        foundFood = findFoood();
        assertEquals(2, foundFood.unitMappings.size());

    }

    private Food findFoood() {
        return entityManager.find(Food.class, "12");
    }

}
