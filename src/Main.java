import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RideContext context = new RideContext();

        List<Ride> rides = List.of(
                new Ride(5, 15, LocalTime.of(8, 30)),  // Short ride during peak hours
                new Ride(25, 45, LocalTime.of(14, 0)), // Long ride during off-peak hours
                new Ride(10, 20, LocalTime.of(18, 30)), // Medium ride during peak hours
                new Ride(15, 30, LocalTime.of(23, 0))  // Medium ride during night (discount hours)
        );

        for (Ride ride : rides) {
            context.selectAppropriateStrategy(ride.distance(), ride.startTime());
            double fare = context.calculateFare(ride.distance(), ride.duration());
            System.out.printf("Ride: Distance=%.1fkm, Duration=%dmin, Start Time=%s, Fare=$%.2f%n",
                    ride.distance(), ride.duration(), ride.startTime(), fare);
        }

        // Test minimum fare
        Ride shortRide = new Ride(1, 3, LocalTime.of(12, 0));
        context.selectAppropriateStrategy(shortRide.distance(), shortRide.startTime());
        double fare = context.calculateFare(shortRide.distance(), shortRide.duration());
        System.out.printf("Short Ride: Distance=%.1fkm, Duration=%dmin, Start Time=%s, Fare=$%.2f%n",
                shortRide.distance(), shortRide.duration(), shortRide.startTime(), fare);

        // Test invalid input
        try {
            Ride invalidRide = new Ride(-5, 10, LocalTime.of(12, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception for invalid ride: " + e.getMessage());
        }
    }
}