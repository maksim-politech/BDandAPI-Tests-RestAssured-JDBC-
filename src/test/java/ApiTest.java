import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import pojo_forAPI.*;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApiTest {

    private String url = "https://jsonplaceholder.typicode.com";

    @Before
    public void setUp() {
        RestAssured.baseURI = url;
    }

    @Test
    public void getAllPosts() {
        ValidatableResponse response =
                given()
                        //.header("Content-type", "application/json")
                        .when().get("/posts")
                        .then().statusCode(200)
                        .and().assertThat().header("Content-type", "application/json; charset=utf-8");
        List<User> response2 = response.extract().jsonPath().getList("", User.class); ///для всего json нужно getList("") //лучше вместо response2 писать user
        for (int i = 1; i < response2.size(); i++) {
            System.out.println(response2.get(i).getId());
            assertTrue(response2.get(i).getId() > response2.get(i - 1).getId());
        }
        //Это на проверку: Сообщения сортируются по возрастанию (по id)
    }

    @Test
    public void getPost99() {
        ValidatableResponse response =
                given()
                        .header("Content-type", "application/json")
                        .when().get("/posts/99")
                        .then().statusCode(200)
                        .and().assertThat().body("userId", equalTo(10), "id", equalTo(99), "title", notNullValue(), "body", notNullValue());
    }

    @Test
    public void getPost150() {
        String response =
                given()
                        .header("Content-type", "application/json")
                        .when().get("/posts/150")
                        .then().statusCode(404)
                        .extract().asString();
        assertEquals("{}", response);
    }

    @Test
    public void CreatePost() {
        User newUser = new User(101, 1, RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(5));
        String response =
                given()
                        .header("Content-type", "application/json")
                        .body(newUser)
                        .when().post("/posts")
                        .then().statusCode(201)
                        .extract().asString();
        System.out.println(response);
    }


    @Test //через дессериализацию
    public void CreatePost2() {
        User newUser = new User(1, 101, RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(5));
        User response =
                given()
                        .header("Content-type", "application/json")
                        .body(newUser)
                        .when().post("/posts")
                        .then().statusCode(201)
                        .extract().as(User.class);
        System.out.println(response);
        System.out.println(newUser);
        assertEquals(newUser, response);
        //java.lang.AssertionError: //до переопределения equals было так
        //Expected :pojo.User@55e2fe3c
        //Actual   :pojo.User@45e1aa48
    }

    @Test //через ссериализацию
    public void CreatePost3() {
        User newUser = new User(101, 1, RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(5));
        ValidatableResponse response =
                given()
                        .header("Content-type", "application/json")
                        .body(newUser)
                        .when().post("/posts")
                        .then().statusCode(201);
        assertEquals(given().body(newUser), response);
        //java.lang.AssertionError:
        //Expected :io.restassured.internal.RequestSpecificationImpl@6fd12c5
        //Actual   :io.restassured.internal.ValidatableResponseImpl@1de0a46c
        System.out.println(response);
    }

    @Test
    public void getAllUsers() {
        ValidatableResponse response =
                given()
                        .header("Content-type", "application/json")
                        .when().get("/users")
                        .then().statusCode(200)
                        .and().assertThat().header("Content-type", "application/json; charset=utf-8")
                        .body("find{it.id==5}.name", equalTo("Chelsey Dietrich"));
    }


    @Test
    public void getAllUsers2() {
        boolean sign = false;
        List<UserNew> response =
                given()
                        .header("Content-type", "application/json")
                        .when().get("/users")
                        .then().statusCode(200)
                        .extract().jsonPath().getList("", UserNew.class);
        UserNew expectedUser = new UserNew(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca", new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", new Geo("-31.8129", "62.5342")), "(254)954-1289", "demarco.info", new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems"));
        for (UserNew userNew : response) {
            if (expectedUser.equals(userNew)) {
                sign = true;
                break;
            } else {
                sign = false;
            }
        }
        assertTrue(sign);
    }


    @Test
    public void getAllUsers3() {
        List<UserNew> response =
                given()
                        .header("Content-type", "application/json")
                        .when().get("/users")
                        .then().statusCode(200)
                        .extract().jsonPath().getList("", UserNew.class);
        UserNew expectedUser = new UserNew(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca", new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", new Geo("-31.8129", "62.5342")), "(254)954-1289", "demarco.info", new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems"));
        UserNew userNew5=null;
        for (UserNew userNew : response) {
            if (userNew.getId() == 5) {
                userNew5 = userNew;
                break;
            } else {
                userNew5 = null;
            }
        }
        assertEquals(expectedUser,userNew5);
    }
}


