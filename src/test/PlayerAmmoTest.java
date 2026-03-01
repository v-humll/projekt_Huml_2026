package test;

import game.Game;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerAmmoTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testPlayerAmmoAddition() {
        int initialAmmo = game.getPlayer().getAmmoReserve();

        game.getPlayer().addAmmo(15);
        assertEquals(initialAmmo + 15, game.getPlayer().getAmmoReserve(), "Naboje se nespravne pricetly.");
    }
}
