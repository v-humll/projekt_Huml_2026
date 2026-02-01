package game;

import java.util.ArrayList;
import java.util.Map;

public class Location {

    private String id;
    private String name;
    private String description;
    private Map<String, String> neighbors; // Direction -> Location ID
    private ArrayList<String> lootTable;

    public ArrayList<String> getLootTable() {
        return lootTable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getNeighbors() {
        return neighbors;
    }
    
    public String getNeighborId(String direction) {
        if (neighbors != null) {
            return neighbors.get(direction);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", neighbors=" + neighbors +
                ", lootTable=" + lootTable +
                '}';
    }
}
