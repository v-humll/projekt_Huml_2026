package Commands;

import game.Game;
import game.Item;

public class PolozCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Co chceš položit?");
            return;
        }

        String target = args[1];
        Item itemToDrop = null;

        for (Item item : game.getPlayer().getInventory()) {
            if (item.getName().equalsIgnoreCase(target) || item.getId().equalsIgnoreCase(target)) {
                itemToDrop = item;
                break;
            }
        }

        if (itemToDrop == null) {
            System.out.println("Tohle v inventáři nemáš.");
            return;
        }

        game.getPlayer().removeItem(itemToDrop);
        game.getPlayer().getLocation().getLootTable().add(itemToDrop.getId());
        System.out.println("Položil jsi: " + itemToDrop.getName());
    }
}
