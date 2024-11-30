package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A utility class for analyzing the price distribution of cars and identifying outliers.
 */
public class CarPriceAnalysis {

    private final List<Integer> sortedPrices;

    /**
     * Initializes the CarPriceAnalysis instance with a list of car prices sorted in ascending order.
     * <p>
     * The constructor processes the provided list of cars to extract and sort their prices.
     * </p>
     *
     * @param cars The list of {@link Car} objects to analyze.
     */
    public CarPriceAnalysis(List<Car> cars) {
        this.sortedPrices = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Analyzes the car prices to calculate the number of normal data points and outliers.
     */
    public Map<String, Long> analyzePrices() {
        // Calculate the 25th and 75th percentiles (A1 and A3)
        int A1 = sortedPrices.get((int) Math.ceil(25 / 100.0 * sortedPrices.size()) - 1);
        int A3 = sortedPrices.get((int) Math.ceil(75 / 100.0 * sortedPrices.size()) - 1);
        // Calculate the interquartile range
        int interRange = A3 - A1;

        // Define the lower and upper bounds for outlier detection
        double lowerBound = A1 - 1.5 * interRange;
        double upperBound = A3 + 1.5 * interRange;

        // Partition prices into normal data points and outliers based on the bounds
        Map<Boolean, Long> result = sortedPrices.stream()
                .collect(Collectors.partitioningBy(
                        price -> price >= lowerBound && price <= upperBound,
                        Collectors.counting()
                ));

        // Prepare the final result map
        Map<String, Long> finalResult = new HashMap<>();
        finalResult.put("data", result.get(true));
        finalResult.put("outliers", result.get(false));

        return finalResult;
    }
}