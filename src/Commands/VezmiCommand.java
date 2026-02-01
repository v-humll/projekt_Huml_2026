package Commands;

import game.Game;

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

        if (loot == null || !loot.contains(target)) {
            System.out.println("To tu není.");
            return;
        }

        game.Item item = game.getWorld().findItem(target);

        if (item.getName().equalsIgnoreCase("munice") || item.getId().contains("munice")) {
            System.out.println("Sebral jsi munici.");
            game.getPlayer().addAmmo(5); 
            System.out.println("+5 nábojů do rezervy.");
        } else {
            System.out.println("Sebral jsi: " + item.getName());
            game.getPlayer().addItem(item);
        }

        loot.remove(target);
    }
}
