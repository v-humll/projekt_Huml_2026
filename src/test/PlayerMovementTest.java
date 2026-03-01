package test;

import game.Game;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerMovementTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testMovementBetweenRooms() {
        Location kancelar = game.getWorld().findLocation("loc_recepce");
        Location chodba = game.getWorld().findLocation("loc_chodba");

        assertNotNull(kancelar, "Recepce nenalezena");
        assertNotNull(chodba, "Chodba nenalezena");

        game.getPlayer().setLocation(kancelar);
        assertEquals(kancelar, game.getPlayer().getLocation(), "Hrac neni na zacatku v recepci.");

        String severId = kancelar.getNeighborId("sever");
        assertEquals("loc_chodba", severId, "Z recepce by se melo na sever projit do chodby.");

        game.getPlayer().setLocation(game.getWorld().findLocation(severId));
        assertEquals(chodba, game.getPlayer().getLocation(), "Hrac se po pohybu nenachazi v chodbe.");
    }
}
