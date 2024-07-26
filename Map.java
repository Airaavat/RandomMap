package Random_map;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.implementations.AbstractGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private static final String[] CITY_NAMES = {"Alpha", "Beta", "Gamma", "Delta", /* add more names */};
    private List<City> cities = new ArrayList<>();
    private List<Road> roads = new ArrayList<>();
    private Random random = new Random();
    
    public void generateCities(int numCities) {
        for (int i = 0; i < numCities; i++) {
            String name = CITY_NAMES[random.nextInt(CITY_NAMES.length)];
            double latitude = -90 + (90 - (-90)) * random.nextDouble();
            double longitude = -180 + (180 - (-180)) * random.nextDouble();
            cities.add(new City(name, latitude, longitude));
        }
    }
    
    public void generateRoads() {
        public void generateRoads() {
            List<Road> nationalHighways = new ArrayList<>();
            List<Road> interStateHighways = new ArrayList<>();
            List<Road> highways = new ArrayList<>();
            List<Road> mainRoads = new ArrayList<>();
        
            Map<City, Set<City>> connections = new HashMap<>();
            for (City city : cities) {
                connections.put(city, new HashSet<>());
            }

            boolean isConnected(City city1, City city2) {
                return connections.get(city1).contains(city2);
            }
        
            void addRoad(City city1, City city2, int lanes) {
                if (city1 != city2 && !isConnected(city1, city2)) {
                    Road road = new Road(city1, city2, lanes);
                    roads.add(road);
                    connections.get(city1).add(city2);
                    connections.get(city2).add(city1);
                }
            }
        
            while (nationalHighways.size() < 4) {
                City city1 = cities.get(random.nextInt(cities.size()));
                City city2 = cities.get(random.nextInt(cities.size()));
                addRoad(city1, city2, 4);
            }
        
            while (interStateHighways.size() < 6) {
                City city1 = cities.get(random.nextInt(cities.size()));
                City city2 = cities.get(random.nextInt(cities.size()));
                if (isConnected(city1, city2)) {
                    addRoad(city1, city2, 3);
                }
            }
        
            while (highways.size() < 8) {
                City city1 = cities.get(random.nextInt(cities.size()));
                City city2 = cities.get(random.nextInt(cities.size()));
                if (isConnected(city1, city2)) {
                    addRoad(city1, city2, 2);
                }
            }
        
            while (mainRoads.size() < 10) {
                City city1 = cities.get(random.nextInt(cities.size()));
                City city2 = cities.get(random.nextInt(cities.size()));
                if (isConnected(city1, city2)) {
                    addRoad(city1, city2, 1);
                }
            }
        
            for (City city : cities) {
                if (connections.get(city).isEmpty()) {
                    City otherCity;
                    do {
                        otherCity = cities.get(random.nextInt(cities.size()));
                    } while (city.equals(otherCity));
                    addRoad(city, otherCity, 1);
                }
            }
        }
    }
    
    public void displayGraph() {
        Graph graph = new SingleGraph("Map");
        
        for (City city : cities) {
            graph.addNode(city.getName()).addAttribute("xy", city.getLatitude(), city.getLongitude());
        }
        
        for (Road road : roads) {
            String edgeId = road.getCity1().getName() + "-" + road.getCity2().getName();
            graph.addEdge(edgeId, road.getCity1().getName(), road.getCity2().getName())
                 .addAttribute("ui.label", road.getLanes() + " lanes")
                 .addAttribute("ui.color", getColorForLanes(road.getLanes()));
        }

        graph.addAttribute("ui.stylesheet", "node { fill-color: white; } edge { size: 2px; }");
        graph.display();
    }

    private String getColorForLanes(int lanes) {
        switch (lanes) {
            case 4: return "black";   
            case 3: return "blue";    
            case 2: return "green";   
            case 1: return "red";     
            default: return "gray";
        }
    }
    
    public static void main(String[] args) {
        Map map = new Map();
        map.generateCities(100);
        map.generateRoads();
        map.displayGraph();
    }
}

