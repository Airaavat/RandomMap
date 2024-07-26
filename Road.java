package Random_map;

import java.util.Random;

public class City {
    private String name;
    private double latitude;
    private double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    
    
    @Override
    public String toString() {
        return String.format("%s (%.2f, %.2f)", name, latitude, longitude);
    }
}

public class Road {
    private City city1;
    private City city2;
    private int lanes;

    public Road(City city1, City city2, int lanes) {
        this.city1 = city1;
        this.city2 = city2;
        this.lanes = lanes;
    }

    public City getCity1() { return city1; }
    public City getCity2() { return city2; }
    public int getLanes() { return lanes; }
}
