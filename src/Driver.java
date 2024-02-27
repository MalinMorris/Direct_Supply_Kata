import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Runs the weather class
 */
public class Driver {
    public static void main(String[] args) throws IOException {
        String key = "d931fb2fe6e2462292f164816242402";
        Scanner input = new Scanner(System.in);
        City location = null;
        while (location == null) {
            System.out.println("To get the weather from a specific city, enter 'C'.\n" +
                    "To get the weather from latitude and longitude, enter 'L'");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("C")) {
                location = Weather.getCity(input);
            } else if (choice.equalsIgnoreCase("L")) {
                location = Weather.getLatLon(input);
            } else {
                System.out.println("To get the weather from a specific city, enter 'C'.\n" +
                        "To get the weather from latitude and longitude, enter 'L'");
                System.out.println("Enter a valid City or Longitude and Latitude");
            }
        }
        Weather weather = new Weather(location);
        double lon = weather.getLon();
        double lat = weather.getLat();
        String urlString = "http://api.weatherapi.com/v1/current.json?key=" + key
                + "&q=" + lat + "," + lon + "&aqi=no";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Scanner scanner = new Scanner(url.openStream());
        String dataString = scanner.nextLine();
        dataString = dataString.substring(dataString.indexOf(":") + 2);
        String[] data = weather.getData(dataString);
        String[] labels = weather.getLabels(dataString);
        weather.print(data, labels, location);
        //System.out.println(dataString);
    }
}
