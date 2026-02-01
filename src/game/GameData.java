package game;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class GameData {

    public List<Item> items;
    public List<GameCharacter> characters;
    public List<Location> locations;
    public List<Quest> quests;

    /**
     * Loads and makes JSON file into GameData object.
     * If path is not working, it tries again with no "/" at start.
     * Gives error if file is not there or JSON is broken.
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                
                 if (resourcePath.startsWith("/")) {
                    return loadGameDataFromResources(resourcePath.substring(1));
                 }
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " (zkontroluj, že soubor je v src/main/resources).");
            }

            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Looks for one location using its ID.
     * ID is the name for the desired loaction.
     * Returns the location that matches.
     */
    public Location findLocation(String id) {
        if (locations == null) return null;
        for (Location l : locations) {
            if (l.getId().equals(id)){
                return l;
            }
        }
        throw new IllegalArgumentException("Neexistuje lokace s id: " + id);
    }

    /**
     * Looks for one item using its ID or name.
     * ID is the identifier or name for the desired item.
     * Returns the item that matches.
     */
    public Item findItem(String id) {
        if (items == null) return null;
        for (Item i : items) {
            if (i.getId().equals(id) || i.getName().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }
}
