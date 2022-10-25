package me.junkxxl.testing.testapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import me.junkxxl.testing.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для работы с API")
@SpringBootTest
public class TestApi {
    @Autowired
    DbSteps db;
    @Autowired
    CommonSteps commonSteps;
    @Autowired
    RestSteps restSteps;

    @DisplayName("Проверка добавления пользователя")
    @Description("Проверяем данные созданного пользователя с данными в БД")
    @Test
    void addUserTest() throws SQLException, JsonProcessingException {
        var userResponse = restSteps.addUser("Alex", "Petrov", 26, "MALE", 999);
        var userGetId = restSteps.getUserId(userResponse.getId());
        commonSteps.checkUserFromMethodGetUserId(userResponse, userGetId);
        var userGetDb = db.getUserByID(userResponse.getId());
        commonSteps.checkUserDB(userResponse, userGetDb);
    }

    @DisplayName("Проверка добавления авто")
    @Description("Проверяем данные созданного автомобиля с данными в БД")
    @Test
    void addCarTest() throws JsonProcessingException, SQLException {
        Car[] arrCar = restSteps.getCar();
        var carResponse = restSteps.addCar("Diesel", "Skoda", "Octavia", 25000);
        Car[] arrCarAfterAddCar = restSteps.getCar();
        commonSteps.checkQuantityCar(arrCar, arrCarAfterAddCar);
        var carGetDb = db.getCarByID(carResponse.getId());
        commonSteps.checkDataCar(carResponse, carGetDb);

    }

    @DisplayName("Проверка добавления дома, с последующим приобритением его и авто")
    @Description("Проверяем что созданный, а далее купленный дом, и купленное авто принадлежат пользователю")
    @Test
    void addHouseAndSettleHouseAndBuyCarTest() throws JsonProcessingException, SQLException {
        var userResponse = restSteps.addUser("Vadim", "Kruglov", 43, "MALE", 0);
        var house = restSteps.addHouse(5000);
        restSteps.addAmountToUserByID(userResponse.getId(), 5000);
        restSteps.settleHouseByUserID(house.getId(), userResponse.getId());

        assertEquals(0, restSteps.getUserId(userResponse.getId()).getMoney(), "Количество денег не равно 0");

        var carResponse = restSteps.addCar("Gasoline", "Skoda", "Kodiaq", 33000);
        restSteps.addAmountToUserByID(userResponse.getId(), 35000);
        restSteps.buyCarByUserID(carResponse.getId(), userResponse.getId());

        assertEquals(2000, restSteps.getUserId(userResponse.getId()).getMoney(), "Количество денег не равно 2000");

        var userGetDb = db.getUserByID(userResponse.getId());
        commonSteps.checkHouseBelongsToUser(house, userGetDb);
    }
}
