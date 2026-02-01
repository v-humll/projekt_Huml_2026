package Commands;

import game.Game;

public class ProzkoumejCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Co chceš prozkoumat?");
            return;
        }
        
        String target = args[1];
        game.Item item = game.getWorld().findItem(target);
        
        boolean available = game.getPlayer().hasItem(target);
        if (!available && game.getPlayer().getLocation().getLootTable() != null) {
            available = game.getPlayer().getLocation().getLootTable().contains(target);
        }
        
        if (available && item != null) {
            System.out.println("Předmět: " + item.getName());
            System.out.println("Popis: " + item.getDescription()); 
        } else {
             if (item != null) {
                 System.out.println("To tu nevidíš.");
             } else {
                 System.out.println("To neznám bruh.");
             }
        }
    }
}
