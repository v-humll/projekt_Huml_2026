package Commands;

import game.Game;
import game.Player;

public class VezmiCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Co chceš sebrat?");
            return;
        }

        String target = args[1];
        game.Location loc = game.getPlayer().getLocation();
        java.util.List<String> loot = loc.getLootTable();
        String itemToRemove = null;
        game.Item foundItem = null;

        if (loot != null) {
            for (String itemId : loot) {
                game.Item it = game.getWorld().findItem(itemId);
                if (it != null && (it.getName().equalsIgnoreCase(target) || it.getId().equalsIgnoreCase(target))) {
                    foundItem = it;
                    itemToRemove = itemId;
                    break;
                }
            }
        }

        if (foundItem == null) {
            System.out.println("To tu není.");
            return;
        }

        if (foundItem.getName().equalsIgnoreCase("munice") || foundItem.getId().contains("munice")) {
            System.out.println("Sebral jsi munici.");
            game.getPlayer().addAmmo(5);
            System.out.println("+5 nábojů do rezervy.");
        } else {
            if (game.getPlayer().getInventory().size() >= Player.MAX_INVENTORY_SIZE) {
                System.out.println("Tvůj inventář je plný! (Max " + Player.MAX_INVENTORY_SIZE + " předmětů)");
                return;
            }
            System.out.println("Sebral jsi: " + foundItem.getName());
            game.getPlayer().addItem(foundItem);
        }

        loot.remove(itemToRemove);
    }
}
