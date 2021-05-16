package ro.fastrackit.trainsApp.model;

import lombok.Value;

@Value
public class TrainFilters {
    String model;
    String minCarts;
    String maxCarts;
    String location;
}
