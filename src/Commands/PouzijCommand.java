package Commands;

import game.Game;

public class PouzijCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Co chceš použít?");
            return;
        }
        
        String target = args[1];
        if (game.getPlayer().hasItem(target)) {
            System.out.println("Použil jsi: " + target);
        } else {
            System.out.println("Tohle nemáš v inventáři.");
        }
    }
}
