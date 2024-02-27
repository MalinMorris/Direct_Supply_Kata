import java.util.Scanner;

/**
 * Creates a Weather object and stores the data for the City
 * Methods to access and print the data
 */
public class Weather {
    private static final int NUM_OF_DATA = 32;
    private final City city;
    private final double lat;
    private final double lon;

    /**
     * Creates a Weather object that stores a city and the city's latitude and longitude
     * @param city the city to get the weather from
     */
    public Weather(City city) {
        this.city = city;
        this.lat = city.getLat();
        this.lon = city.getLon();
    }

    /**
     * takes the data from the API as a string and uses substrings to put the
     * pieces into an array
     * @param dataString the String with the data read from the url
     * @return the array of strings that hold the data
     */
    public String[] getData(String dataString) {
        String[] data = new String[NUM_OF_DATA];
        for (int i = 0; i < data.length; i++) {
            if (dataString.contains("current") || dataString.contains("condition")) {
                dataString = dataString.substring(dataString.indexOf(":") + 2);
            }
            int comma = dataString.indexOf(",");
            if (comma != -1) {
                String weather = dataString.substring(0, comma);
                int colon = weather.indexOf(":");
                if (colon != 1 && weather.length() >= 3) {
                    data[i] = weather.substring(colon + 1);
                } else {
                    data[i] = "No data";
                }
                dataString = dataString.substring(comma + 1);
            } else {
                data[i] = "No data";
            }

        }
        return data;
    }

    /**
     * takes the data from the API as a string and uses substrings to put the
     * labels into an array
     * @param dataString the String with the data read from the url
     * @return the array of strings that hold the labels
     */
    public String[] getLabels(String dataString) {
        String[] labels = new String[NUM_OF_DATA];
        for (int i = 0; i < labels.length; i++) {
            int comma = dataString.indexOf(",");
            if (comma != -1) {
                String label = dataString.substring(0, comma);
                int colon = label.indexOf(":");
                labels[i] = label.substring(1, colon - 1);
                dataString = dataString.substring(comma + 1);
            } else {
                labels[i] = dataString;
            }

        }
        return labels;
    }

    /**
     * prompts the user to type in one of the preset cities and returns that city
     * @param input the scanner object that takes in user input
     * @return the city that the user chose from the enum
     */
    public static City getCity(Scanner input) {
        System.out.println("Available cities: ");
        for (City c : City.values()) {
            if (!c.name().equals(City.OTHER.name())) {
                System.out.print(c.name() + ", ");
            }
        }
        System.out.println();
        String city = input.nextLine();
        for (City c : City.values()) {
            if (c.name().equalsIgnoreCase(city)) {
                return c;
            }
        }
        return null;
    }

    /**
     * prompts the user to type in the latitude and longitude of the location
     * @param input the scanner object that takes in user input
     * @return a city made with the latitude and longitude from the OTHER city in the City enum
     */
    public static City getLatLon(Scanner input) {
        double lat = 0;
        double lon = 0;
        while (lat == 0 && lon == 0) {
            try {
                System.out.println("Enter the latitude (> -90, < 90, not 0)");
                lat = Double.parseDouble(input.nextLine());
                System.out.println("Enter the longitude (> -90, < 90, not 0)");
                lon = Double.parseDouble(input.nextLine());
                if (lat == 0 && lon == 0) {
                    System.out.println("Enter latitude and longitude that are greater than zero");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a number");
            }
        }
        City.OTHER.setLat(lat);
        City.OTHER.setLon(lon);
        return City.OTHER;
    }

    /**
     * prints the weather at the location in a table
     * @param data the array holding the weather data
     * @param labels the array holding the label names
     * @param city the location the weather is coming from
     */
    public void print(String[] data, String[] labels, City city) {
        System.out.println("Current weather at " + city.getLat() + " " + city.getLon() + "(" + this.city.name() + "):");
        System.out.printf("|%-20s|%-50s|\n", "Condition", "Weather");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("|%-20s|%-50s|\n", labels[i], data[i]);
        }

    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
