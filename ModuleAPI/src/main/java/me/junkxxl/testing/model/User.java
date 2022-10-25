package me.junkxxl.testing.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer age;
    private String sex;
    private Integer money;
    private Integer house_id;
}
