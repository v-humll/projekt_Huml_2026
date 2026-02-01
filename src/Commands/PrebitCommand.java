package Commands;

import game.Game;

public class PrebitCommand implements Command {


    @Override
    public void execute(Game game, String[] args) {
        if (!game.isBossFightActive()) {
            System.out.println("Není důvod nabíjet. (Bossfight not initiated)");
            return;
        }

        game.Player player = game.getPlayer();
        if (player.getAmmoReserve() <= 0) {
            System.out.println("Nemáš náhradní munici!");
            return;
        }
        
        System.out.println("Přebíjíš...");
        player.reload();
        System.out.println("Přebito. Zásobník: " + player.getCurrentMagazine() + "/3. Rezerva: " + player.getAmmoReserve());

        if (game.getBossHp() > 0) {
             int bossDmg = (int)(Math.random() * 8) + 2; 
             if (game.isPlayerDefending()) bossDmg /= 2; 
             
             game.damagePlayer(bossDmg);
             System.out.println("Při přebíjení tě boss zasáhl! Poškození: " + bossDmg + " (HP: " + game.getPlayerHp() + ")");
             if (game.getPlayerHp() <= 0) {
                System.out.println("Zemřel jsi. Game Over.");
                game.setGameOver(true);
            }
        }
        game.setPlayerDefending(false);
    }

}
