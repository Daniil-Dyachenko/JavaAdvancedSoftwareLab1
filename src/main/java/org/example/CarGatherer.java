package org.example;

import java.util.Optional;
import java.util.stream.Gatherer;
import lombok.AllArgsConstructor;

/**
 * A custom gatherer that filters out a specified number of cars of a given brand.
 *
 */
@AllArgsConstructor
class CarGatherer implements Gatherer<Car, Optional<Car>, Car> {

    /**
     * The number of cars of the specified brand to skip.
     */
    private int skipCount;

    /**
     * The brand of cars to skip during gathering.
     */
    private String skipBrand;

    /**
     * Provides an integrator for processing cars, skipping those that match the brand and the skip count.
     *
     * @return An integrator that determines whether a car should be skipped or processed.
     */
    @Override
    public Integrator<Optional<Car>, Car, Car> integrator() {
        return Gatherer.Integrator.of((_, element, downstream) -> {
            // If the car's brand matches the skipBrand and there are cars left to skip, skip this car
            if (element.getBrand().equals(skipBrand) && skipCount > 0) {
                skipCount--;
                return true;
            }
            return downstream.push(element);
        });
    }
}
