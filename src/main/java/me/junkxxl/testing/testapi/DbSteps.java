package me.junkxxl.testing.testapi;


import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import me.junkxxl.testing.model.Car;
import me.junkxxl.testing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DbSteps {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Step("Сохраняем данные пользователя по id =\"{0}\"")
    @Attachment
    public User getUserByID(int id) throws SQLException {
        return jdbcTemplate.query(
                        "select * from person where id = ?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(User.class))
                .get(0);
    }

    @Step("Сохраняем данные автомобиля по id =\"{0}\"")
    @Attachment
    public Car getCarByID(int id) throws SQLException {
        return jdbcTemplate.query(
                        "select c.id , c.mark ,c.model ,c.price ,et.type_name from car c,engine_type et where c.id = ? and c.engine_type_id = et.id",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Car.class))
                .get(0);
    }
}
