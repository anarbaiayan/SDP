@FunctionalInterface
public interface FareStrategy {
    double calculateFare(double distance, int duration, boolean isPeakHour);
}