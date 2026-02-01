package Commands;

import game.Game;

public class KonecCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        System.out.println("Ukončuji hru...");
        game.setGameOver(true);
    }
}
