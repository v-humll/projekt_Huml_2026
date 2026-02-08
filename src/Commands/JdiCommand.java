package Commands;

import game.Game;
import game.Location;
import game.Player;

public class JdiCommand implements Command {

/**
 * Moves player to a new place.
 * Check if player said where to go.
 * If path is okay, player goes there and sees new info.
 * If no path, it says you cannot go.
 */

    @Override
    public void execute(Game game, String[] args) {
        if (game.isBossFightActive()) {
            System.out.println("Nemůžeš utéct! Černohorský ti stojí v cestě. Musíš bojovat!");
            return;
        }

        if (args.length < 2) {
            System.out.println("Kam chceš jít?");
            return;
        }

        String direction = args[1];
        Player player = game.getPlayer();
        Location current = player.getLocation();
        String nextLocationId = current.getNeighborId(direction);

        if (nextLocationId != null) {
            if (nextLocationId.equals("loc_archiv") && !game.isArchiveUnlocked()) {
                System.out.println("Dveře do archivu jsou zamčené. Potřebuješ klíč.");
                return;
            }
            
/*
 * Check for darkness (Archiv/Sklep need Flashlight)
 * Reception is dark but you start there
 * But if going into Archiv/Sklep, need flashlight.
*/
            boolean isDark = nextLocationId.equals("loc_archiv") || nextLocationId.equals("loc_sklep") || nextLocationId.equals("loc_recepce");
            if ((nextLocationId.equals("loc_archiv") || nextLocationId.equals("loc_sklep")) 
                && !player.hasItem("item_flashlight") && !game.isPowerRestored()) {
                System.out.println("Je tam příliš velká tma. Bez baterky bys zabloudil.");
                return;
            }

            Location next = game.getWorld().findLocation(nextLocationId);
            player.setLocation(next);
            System.out.println("Jdeš směrem " + direction);
            System.out.println("Nacházíš se: " + next.getName());
            
/*
 * Check boss fight trigger
*/
            if (nextLocationId.equals("loc_cely") && !game.isBossDefeated()) {
                System.out.println("POZOR! V celách na tebe čeká Černohorský!");
                System.out.println("BOJ ZAČÍNÁ! (Příkazy: utok, kryjse, prebit)");
                game.startBossFight();
            }

/*
 * Description logic with darkness
*/
            if (isDark && !player.hasItem("item_flashlight") && !game.isPowerRestored()) {
                 System.out.println("Nic nevidíš. Je tu naprostá tma.");
            } else {
                 System.out.println("Popis: " + next.getDescription());
            }
        } else {
            System.out.println("Tímto směrem nelze jít.");
        }
    }
}
