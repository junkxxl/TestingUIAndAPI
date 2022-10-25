package me.junkxxl.testing.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Car {
    private Integer id;
    private String mark;
    private String model;
    private Double price;
    private Integer engine_type_id;
    private String engineType;
    private String type_name;
    private Integer person_id;
}
