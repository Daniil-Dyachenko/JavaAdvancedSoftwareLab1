package org.example;
import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Generates random Car objects using Stream API.
 */
public class CarGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Generates a stream of random Car objects.
     *
     * @return A stream of Car objects.
     */
    public static Stream<Car> generateRandomCars() {
        //Create brands, models and classes
        String[] brands = {"Mazda", "Hundai", "Audi", "BMW", "Mersedes"};
        String[] models = {"6", "X7", "Sonata", "Benz", "A4"};
        String[] classes = {"Economy", "Comfort", "Sport", "Business"};

        // Machine flow generation
        return Stream.generate(() -> Car.builder()
                .brand(brands[RANDOM.nextInt(brands.length)]) // Random brand
                .model(models[RANDOM.nextInt(models.length)]) // A random model
                .carClass(classes[RANDOM.nextInt(classes.length)]) // Random class
                .productionDate(LocalDate.now().minusMonths(RANDOM.nextInt(120))) // Random date of production (0-10 years)
                .price((int) (500_000 + (2_000_000 * RANDOM.nextDouble()))) // Random price in the range of 500,000 - 2,500,000 UAH
                .build());
    }
}