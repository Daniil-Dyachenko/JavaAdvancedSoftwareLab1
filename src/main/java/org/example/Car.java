package org.example;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents a car with essential details such as brand, model, class, manufacture date, and price.
 */
@AllArgsConstructor
@Data
@Builder
public class Car {

    /**
     * The brand of the car .
     */
    private String brand;

    /**
     * The model of the car.
     */
    private String model;

    /**
     * The class of the car .
     */
    private String carClass;

    /**
     * The production date of the car.
     * Represents the date when the car was manufactured.
     */
    private LocalDate productionDate;

    /**
     * The price of the car in the local currency.
     */
    private int price;
}
