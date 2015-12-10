package de.meggsimum.w3w;

/**
 * Class represents a position object holding its coordinates (lat/lon).
 */
public class Coordinates {

    /** latitude of this coordinate */
    private double latitude;

    /** longitude of this coordinate */
    private double longitude;

    /**
     * Creates an empty {@linkplain Coordinates} object
     *
     * @param latitude
     * @param longitude
     */
    public Coordinates() {
    }

    /**
     * Creates a {@linkplain Coordinates} object with the given latitude and
     * longitude
     *
     * @param latitude
     * @param longitude
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        return Double.compare(that.longitude, longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
