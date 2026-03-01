package test;

import game.Game;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CombatDamageTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testCombatLogicDamageTaken() {
        game.healPlayer(); // Inicializace HP
        int startingHp = game.getPlayerHp();
        int damageToTake = 25;

        game.damagePlayer(damageToTake);
        assertEquals(startingHp - damageToTake, game.getPlayerHp(), "Hrac neobdrzel ocekavane zraneni.");
    }
}
