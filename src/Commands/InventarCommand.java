package Commands;

import game.Game;

public class InventarCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        game.Player player = game.getPlayer();
        java.util.List<game.Item> inventory = player.getInventory();
        
        System.out.println("--- Inventář ---");
        if (inventory.isEmpty()) {
            System.out.println("(prázdný)");
        } else {
            for (game.Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
        System.out.println("Zásobník: " + player.getCurrentMagazine() + "/3");
        System.out.println("Rezervní munice: " + player.getAmmoReserve());
        System.out.println("----------------");
    }
}
