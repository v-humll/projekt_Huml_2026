package Commands;

import game.Game;

public class ProzkoumejCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        /*
         * If no argument, investigate the room (reveal loot)
         * If argument, investigate the item
         */
        if (args.length < 2) {
            game.Location loc = game.getPlayer().getLocation();
            java.util.List<String> loot = loc.getLootTable();
            
            if (loot != null && !loot.isEmpty()) {
                System.out.println("Při prozkoumávání okolí jsi našel:");
                for (String itemId : loot) {
                    game.Item item = game.getWorld().findItem(itemId);
                    if (item != null) {
                        System.out.println("- " + item.getName());
                    }
                }
                return;
            } else {
                System.out.println("Nic zajímavého jsi nenašel.");
                return;
            }
        }
        
        /*
         * Check if the item is in the player's inventory or in the room's loot table
         * If not found, print error message
         * If found, print item description
         * If the item is in the room's loot table, remove it from the loot table
         */

        String target = args[1];
        game.Item item = game.getWorld().findItem(target);
        
        boolean available = game.getPlayer().hasItem(target);
        if (!available && game.getPlayer().getLocation().getLootTable() != null) {
            for (String lootId : game.getPlayer().getLocation().getLootTable()) {
                 game.Item lootItem = game.getWorld().findItem(lootId);
                 if (lootItem != null && (lootId.equalsIgnoreCase(target) || lootItem.getName().equalsIgnoreCase(target))) {
                     available = true;
                     item = lootItem;
                     break;
                 }
            }
        }
        
        if (available && item != null) {
            System.out.println("Předmět: " + item.getName());
            System.out.println("Popis: " + item.getDescription()); 
        } else {
             if (item != null) {
                 System.out.println("To tu nevidíš.");
             } else {
                 System.out.println("To neznám.");
             }
        }
    }
}
