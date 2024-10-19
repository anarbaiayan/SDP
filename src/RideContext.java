import java.time.LocalTime;

public class RideContext {
    private FareStrategy fareStrategy;
    private static final double MINIMUM_FARE = 5.0;

    public RideContext() {
        this.fareStrategy = new RegularFareStrategy();
    }

    public void setFareStrategy(FareStrategy fareStrategy) {
        this.fareStrategy = fareStrategy;
    }

    public double calculateFare(double distance, int duration) {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Distance and duration must be non-negative");
        }

        boolean isPeakHour = isPeakHour(LocalTime.now());
        double fare = fareStrategy.calculateFare(distance, duration, isPeakHour);
        return Math.max(fare, MINIMUM_FARE);
    }

    public void selectAppropriateStrategy(double distance, LocalTime time) {
        if (isPeakHour(time)) {
            setFareStrategy(new SurgeFareStrategy());
        } else if (distance > 20) {
            setFareStrategy(new PremiumFareStrategy());
        } else if (isOffPeakNight(time)) {
            setFareStrategy(new DiscountFareStrategy());
        } else {
            setFareStrategy(new RegularFareStrategy());
        }
    }

    private boolean isPeakHour(LocalTime time) {
        return isTimeBetween(time, LocalTime.of(7, 0), LocalTime.of(10, 0)) ||
                isTimeBetween(time, LocalTime.of(16, 0), LocalTime.of(19, 0));
    }

    private boolean isOffPeakNight(LocalTime time) {
        return isTimeBetween(time, LocalTime.of(22, 0), LocalTime.of(6, 0));
    }

    private boolean isTimeBetween(LocalTime time, LocalTime start, LocalTime end) {
        return !time.isBefore(start) && time.isBefore(end);
    }
}

class RegularFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance, int duration, boolean isPeakHour) {
        return distance * 1.0 + duration * 0.25;
    }
}

class PremiumFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance, int duration, boolean isPeakHour) {
        return distance * 1.5 + duration * 0.3;
    }
}

class DiscountFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance, int duration, boolean isPeakHour) {
        return (distance * 0.8 + duration * 0.2) * 0.9;
    }
}

class SurgeFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance, int duration, boolean isPeakHour) {
        double baseFare = distance * 1.0 + duration * 0.25;
        return isPeakHour ? baseFare * 2 : baseFare;
    }
}