package Commands;

import game.Game;

public class RozhledniSeCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        game.Location loc = game.getPlayer().getLocation();
        if (loc != null) {
            System.out.println("Nacházíš se: " + loc.getName());
            System.out.println("Popis: " + loc.getDescription());
        } else {
            System.out.println("Jsi v prázdnotě.");
        }
    }
}
