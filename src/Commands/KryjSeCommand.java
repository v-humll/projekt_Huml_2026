package Commands;

import game.Game;

public class KryjSeCommand implements Command {


    @Override
    public void execute(Game game, String[] args) {
        if (!game.isBossFightActive()) {
            System.out.println("Není před kým se krýt. (Bossfight not initiated)");
            return;
        }
        
        System.out.println("Skrčil ses za překážku.");
        game.setPlayerDefending(true);
        System.out.println("Boss střílí, ale trefuje jen kryt!");
    }
}
