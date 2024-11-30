package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main class that demonstrates filtering, grouping, statistical analysis,
 * and price analysis of a list of {@link Car} objects.
 */
public class Main {

    /**
     * Main entry point of the program. This method demonstrates filtering, grouping,
     * statistical analysis, and price analysis of car data.
     *
     */
    public static void main(String[] args) {
        int N = 10;
        String brandToSkip = "Mazda";

        // Generate and filter cars
        List<Car> cars = getCarsFiltered(N, brandToSkip);
        for (Car car : cars) {
            System.out.println(car);
        }

        // Group cars by their class
        Map<String, List<Car>> groupedCars = getCarsGrouped(cars);
        for (Map.Entry<String, List<Car>> entry : groupedCars.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        // Collect car statistics
        CarStatistics statistics = cars.stream()
                .collect(new CarStatisticsCollector());
        System.out.println(statistics);

        // Create a price analyzer object
        CarPriceAnalysis analysis = new CarPriceAnalysis(cars);

        // Analyze price distribution and identify outliers
        Map<String, Long> priceAnalysis = analysis.analyzePrices();

        // Output the results of price analysis
        System.out.println(priceAnalysis);
    }

    /**
     * Filters a list of cars based on a specified number of cars to skip and a brand to skip.
     * The method generates car data and applies filtering criteria.
     *
     * @param skipCount The number of cars of a specific brand to skip.
     * @param brandToSkip The brand of cars to skip during filtering.
     * @return A list of filtered {@link Car} objects.
     */
    private static List<Car> getCarsFiltered(int skipCount, String brandToSkip) {
        return CarGenerator.generateRandomCars()
                .gather(new CarGatherer(skipCount, brandToSkip))
                .limit(500)
                .collect(Collectors.toList());
    }

    /**
     * Groups a list of cars by their class and filters the cars by manufacture date.
     * Only cars manufactured within the last 5 years are included.
     *
     * @param cars A list of {@link Car} objects to group.
     * @return A map where the keys are car classes, and values are lists of cars in those classes.
     */
    private static Map<String, List<Car>> getCarsGrouped(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getProductionDate().isAfter(LocalDate.now().minusMonths(60)))
                .collect(Collectors.groupingBy(Car::getCarClass));
    }
}