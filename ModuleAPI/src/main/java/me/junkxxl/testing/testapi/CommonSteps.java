package me.junkxxl.testing.testapi;

import io.qameta.allure.Step;
import me.junkxxl.testing.model.Car;
import me.junkxxl.testing.model.House;
import me.junkxxl.testing.model.User;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class CommonSteps {

    @Step("Проверяем данные созданного пользователя и данные из метода getUserId ")
    void checkUserFromMethodGetUserId(User userResponse, User userGetId) {
        assertEquals(userResponse, userGetId, "Пользователи не равны");
    }

    @Step("Проверяем данные созданного пользователя и данные из БД")
    void checkUserDB(User userResponse, User userGetDb) {
        assertAll(
                () -> assertEquals(userResponse.getId(), userGetDb.getId()),
                () -> assertEquals(userResponse.getAge(), userGetDb.getAge()),
                () -> assertEquals(userResponse.getFirstName(), userGetDb.getFirstName()),
                () -> assertEquals(userResponse.getSecondName(), userGetDb.getSecondName()),
                () -> assertEquals("t", userGetDb.getSex())
        );
    }

    @Step("Проверяем что количество авто увеличилось, после добавления автомобиля")
    void checkQuantityCar(Car[] arrCar, Car[] arrCarAfterAddCar) {
        assertEquals(arrCar.length + 1, arrCarAfterAddCar.length, "Количество авто не увеличилось");
    }

    @Step("Проверяем данные добавленного автомобиля и данные из БД")
    void checkDataCar(Car carResponse, Car carGetDb) {
        assertAll(
                () -> assertEquals(carResponse.getId(), carGetDb.getId()),
                () -> assertEquals(carResponse.getMark(), carGetDb.getMark()),
                () -> assertEquals(carResponse.getModel(), carGetDb.getModel()),
                () -> assertEquals(carResponse.getPrice(), carGetDb.getPrice()),
                () -> assertEquals(carResponse.getEngineType(), carGetDb.getType_name())
        );
    }

    @Step("Проверяем что дом принадлежит пользователю")
    void checkHouseBelongsToUser(House house, User userGetDb) {
        assertEquals(house.getId(), userGetDb.getHouse_id(), "Дом не принадлежит этому пользователю");
    }
}
