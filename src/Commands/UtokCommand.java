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
        if (!player.hasItem("item_weapon")) { 
            System.out.println("Nemáš zbraň! (Potřebuješ Služební zbraň)");
            return;
        }

        if (player.getCurrentMagazine() <= 0) {
            System.out.println("Došly náboje. Přebij! (Prebit)");
            return;
        }

        /*
         * Shoot the boss and use one bullet.
         * If the boss has 0 health, the fight ends.
         */
        player.decreaseMagazine();
        int damage = (int)(Math.random() * 10) + 5; 
        game.damageBoss(damage);
        System.out.println("Vystřelil jsi! (Zbývá nábojů: " + player.getCurrentMagazine() + "/3)");
        System.out.println("Boss utrpěl " + damage + " poškození. (Boss HP: " + game.getBossHp() + ")");

        if (game.getBossHp() <= 0) {
            System.out.println("Černohorský padl k zemi. Je konec... ale jen pro něj.");
            System.out.println("Teď musíš zavolat pomoc. Vysílač je ve sklepě.");
            game.setBossDefeated(true);
            game.setBossFightActive(false);
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
