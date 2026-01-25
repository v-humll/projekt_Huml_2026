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
        if (args.length < 2) {
            System.out.println("Kam chceš jít?");
            return;
        }

        String direction = args[1];
        Player player = game.getPlayer();
        Location current = player.getLocation();
        String nextLocationId = current.getNeighborId(direction);

        if (nextLocationId != null) {
            Location next = game.getWorld().findLocation(nextLocationId);
            player.setLocation(next);
            System.out.println("Jdeš směrem " + direction);
            System.out.println("Nacházíš se: " + next.getName());
            System.out.println("Popis: " + next.getDescription());
        } else {
            System.out.println("Tímto směrem nelze jít.");
        }
    }
}
