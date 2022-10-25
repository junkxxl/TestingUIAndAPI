package me.junkxxl.testing.model;

import lombok.Data;
import lombok.experimental.Accessors;
import me.junkxxl.testing.model.house.ParkingPlaces;

@Data
@Accessors(chain = true)
public class House {
    private Integer id;
    private Integer floorCount;
    private Integer price;
    private ParkingPlaces[] parkingPlaces;
    private String[] lodgers;
}
