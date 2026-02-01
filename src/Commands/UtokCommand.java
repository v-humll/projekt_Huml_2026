package Commands;

import game.Game;

public class UtokCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (!game.isBossFightActive()) {
            System.out.println("Boje nejsou aktivní. (Bossfight not initiated)");
            return;
        }

        game.Player player = game.getPlayer();
        if (!player.hasItem("zbran")) { 
            System.out.println("Nemáš zbraň!");
            return;
        }

        if (player.getCurrentMagazine() <= 0) {
            System.out.println("Došly náboje. Přebij! (Prebit)");
            return;
        }

        player.decrementMagazine();
        int damage = (int)(Math.random() * 10) + 5; 
        game.damageBoss(damage);
        System.out.println("Vystřelil jsi! Boss utrpěl " + damage + " poškození. (Boss HP: " + game.getBossHp() + ")");

        if (game.getBossHp() <= 0) {
            System.out.println("BRAVO! Porazil jsi bosse a zachránil směnu!");
            game.setGameOver(true);
            return;
        }

        // Bpss counter attack
        if (game.isPlayerDefending()) {
            System.out.println("Boss útočí, ale ty ses kryl!");
            game.setPlayerDefending(false); 
        } else {
            int bossDmg = (int)(Math.random() * 8) + 2; 
            game.damagePlayer(bossDmg);
            System.out.println("Boss opětuje palbu! Utrpěl jsi " + bossDmg + " poškození. (Tvoje HP: " + game.getPlayerHp() + ")");
            
            if (game.getPlayerHp() <= 0) {
                System.out.println("Zemřel jsi. Game Over.");
                game.setGameOver(true);
            }
        }
    }
}
