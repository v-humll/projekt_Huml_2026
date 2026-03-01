package test;

import game.Game;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerHealTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testPlayerHeal() {
        game.healPlayer(); // Nastavíme úvodní HP pro test na plných 100
        game.damagePlayer(60); // 100 - 60 = 40 HP
        assertEquals(40, game.getPlayerHp(), "Hp nebylo na zacatku nastaveno na 40");

        game.healPlayer(); // healed back to 100
        assertEquals(100, game.getPlayerHp(), "Metoda heal() spatne pridala zivoty.");
    }
}
