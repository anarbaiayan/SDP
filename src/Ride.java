import java.time.LocalTime;

public record Ride(double distance, int duration, LocalTime startTime) {
    public Ride {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Distance and duration must be non-negative");
        }
    }
}