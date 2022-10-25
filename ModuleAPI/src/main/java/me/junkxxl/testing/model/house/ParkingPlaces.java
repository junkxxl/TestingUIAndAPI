package me.junkxxl.testing.model.house;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParkingPlaces {
    private Integer id;
    private Boolean isWarm;
    private Boolean isCovered;
    private Integer placesCount;
}
