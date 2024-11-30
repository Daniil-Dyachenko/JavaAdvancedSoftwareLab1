package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * A custom collector for calculating basic statistics of car prices.
 */
public class CarStatisticsCollector implements Collector<Car, List<Integer>, CarStatistics> {

    /**
     * Provides a new list to accumulate car prices.
     *
     * @return A new empty list to collect car prices.
     */
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    /**
     * Adds the price of a car to the accumulator list.
     * @return A bi-consumer function that adds a car's price to the accumulator.
     */
    @Override
    public BiConsumer<List<Integer>, Car> accumulator() {
        return (accumulator, car) -> accumulator.add(car.getPrice());
    }

    /**
     * Combines two partial results of the accumulator into a single result.
     *
     * @return A binary operator that merges two lists of prices into one.
     */
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * Converts the accumulated list of car prices into a {@link CarStatistics} object.
     *
     * @return A function that calculates car statistics from the accumulated list of prices.
     */
    @Override
    public Function<List<Integer>, CarStatistics> finisher() {
        return prices -> {
            // Calculate the minimum price
            int min = prices.stream().min(Integer::compare).orElse(0);
            // Calculate the maximum price
            int max = prices.stream().max(Integer::compare).orElse(0);
            // Calculate the average price
            double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
            // Calculate the standard deviation
            double stdDev = Math.sqrt(prices.stream()
                    .mapToDouble(price -> Math.pow(price - avg, 2))
                    .average().orElse(0.0));
            // Return the calculated statistics
            return new CarStatistics(min, max, avg, stdDev);
        };
    }

    /**
     * Specifies the characteristics of the collector.
     *
     * @return A set of characteristics of the collector.
     */
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.CONCURRENT);
        return characteristics;
    }
}
