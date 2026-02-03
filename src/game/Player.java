package game;

public class Player {

    private Location location;
    private java.util.List<Item> inventory;
    private int ammoReserve;
    private int currentMagazine;
    private final int MAX_MAGAZINE = 3;
    public static final int MAX_INVENTORY_SIZE = 5;

    public Player() {
        this.inventory = new java.util.ArrayList<>();
        this.ammoReserve = 0;
        this.currentMagazine = MAX_MAGAZINE;
        this.currentMagazine = 3;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public java.util.List<Item> getInventory() {
        return inventory;
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName) || item.getId().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public int getAmmoReserve() {
        return ammoReserve;
    }

    public void addAmmo(int amount) {
        this.ammoReserve += amount;
    }

    public int getCurrentMagazine() {
        return currentMagazine;
    }

    public void decrementMagazine() {
        if (currentMagazine > 0) {
            currentMagazine--;
        }
    }

    /**
     * Reloads the weapon using ammo from the reserve.
     */
    public void reload() {
        if (ammoReserve > 0 && currentMagazine < MAX_MAGAZINE) {
            int needed = MAX_MAGAZINE - currentMagazine;
            int toLoad = Math.min(needed, ammoReserve);
            currentMagazine += toLoad;
            ammoReserve -= toLoad;
        }
    }
}
