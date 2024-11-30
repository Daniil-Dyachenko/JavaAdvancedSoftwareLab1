package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents statistical data for car prices.
 *
 */
@AllArgsConstructor
@Data
public class CarStatistics {

    /**
     * The minimum price of the cars.
     */
    private int minPrice;

    /**
     * The maximum price of the cars.
     */
    private int maxPrice;

    /**
     * The average price of the cars.
     */
    private double averagePrice;

    /**
     * The standard deviation of the car prices.
     */
    private double standardDeviation;
}
