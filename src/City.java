/**
 * Preset cities with the latitude and longitude of the city
 */
public enum City {
    /**
     * The city of Milwaukee with the latitude and longitude
     */
    MILWAUKEE(43.04, -87.91),
    /**
     * The city of Chicago with the latitude and longitude
     */
    CHICAGO(41.85, -87.65),
    /**
     * The city of New York with the latitude and longitude
     */
    NEW_YORK(40.71, -74.01),
    /**
     * The city of Los Angeles with the latitude and longitude
     */
    LOS_ANGELES(34.05, -118.24),
    /**
     * The city of London with the latitude and longitude
     */
    LONDON(51.52, -.11),
    /**
     * The city of Berlin with the latitude and longitude
     */
    BERLIN(52.52, 13.4),
    /**
     * The city of Tokyo with the latitude and longitude
     */
    TOKYO(35.69, 139.69),
    /**
     * A city with the lat and lon both set to 0, so they can be changes to
     * what the user types in
     */
    OTHER(0, 0);

    private double lat;
    private double lon;
    City(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
}
