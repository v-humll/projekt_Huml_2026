package Commands;

import game.Game;

public class NapovedaCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        System.out.println("Dostupné příkazy:");
        for (String cmd : game.getCommandsMap().keySet()) {
            System.out.print(cmd + " ");
        }
        System.out.println();
    }
}
