package me.junkxxl.testing.testapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import me.junkxxl.testing.model.Car;
import me.junkxxl.testing.model.House;
import me.junkxxl.testing.model.User;
import me.junkxxl.testing.model.house.ParkingPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class RestSteps {
    @Autowired
    AppConfig appConfig;

    @Step("Добавляем пользователя {name} {lastname} ")
    @Attachment
    public User addUser(String name, String lastname, int age, String sex, int money) throws JsonProcessingException {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .body(appConfig.objectMapper.writeValueAsString(
                        new User()
                                .setFirstName(name)
                                .setSecondName(lastname)
                                .setAge(age)
                                .setSex(sex)
                                .setMoney(money)))
                .post("/addUser").then().log().all().statusCode(201)
                .extract().response().as(User.class);
    }

    @Step("Запрашиваем данные пользователя по id = {id}")
    @Attachment
    public User getUserId(int id) {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .get("/user/{id}", id).then().log().all().statusCode(200)
                .extract().response().as(User.class);
    }

    @Step("Получаем список всех автомобилей")
    @Attachment
    public Car[] getCar() {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .get("/cars").then().log().all().statusCode(200)
                .extract().response().as(Car[].class);
    }

    @Step("Добавляем автомобиль марки {mark}, модель {model}")
    @Attachment
    public Car addCar(String engineType, String mark, String model, int price) throws JsonProcessingException {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .body(appConfig.objectMapper.writeValueAsString(
                        new Car()
                                .setEngineType(engineType)
                                .setMark(mark)
                                .setModel(model)
                                .setPrice((double) price)))
                .post("/addCar").then().log().all().statusCode(201)
                .extract().response().as(Car.class);
    }

    @Step("Добавляем дом с ценой = {price}")
    @Attachment
    public House addHouse(int price) throws JsonProcessingException {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .body(appConfig.objectMapper.writeValueAsString(
                        new House()
                                .setFloorCount(3)
                                .setPrice(price)
                                .setParkingPlaces(new ParkingPlaces[]{
                                        new ParkingPlaces()
                                                .setIsWarm(true)
                                                .setIsCovered(true)
                                                .setPlacesCount(1),
                                        new ParkingPlaces()
                                                .setIsWarm(false)
                                                .setIsCovered(true)
                                                .setPlacesCount(2),
                                        new ParkingPlaces()
                                                .setIsWarm(true)
                                                .setIsCovered(false)
                                                .setPlacesCount(3)
                                })))
                .post("/addHouse").then().log().all().statusCode(201)
                .extract().response().as(House.class);
    }

    @Step("Добавляем пользователю с id = {id}, сумму в размере = {amount}")
    @Attachment
    public User addAmountToUserByID(int id, int amount) {
        return given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .post("/user/{id}/money/{amount}", id, amount).then().log().all().statusCode(200)
                .extract().response().as(User.class);
    }

    @Step(" Покупаем дом с id = {houseId} , пользователю с id = {userId}")
    public void settleHouseByUserID(int houseId, int userId) {
        given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .post("/house/{houseId}/settle/{userId}", houseId, userId).then().log().all().statusCode(200);
    }

    @Step("Покупаем автомобиль с id = {carId}, пользователю с id = {userId}")
    public void buyCarByUserID(int carId, int userId) {
        given().baseUri(appConfig.URL).contentType("application/json").filter(new AllureRestAssured())
                .post("/user/{userId}/buyCar/{carId}", userId, carId).then().log().all().statusCode(200);
    }
}
