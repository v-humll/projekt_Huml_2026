package test;

import game.Game;
import game.Item;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDropTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testItemDropSuccess() {
        Item zbran = game.getWorld().findItem("item_weapon");

        game.getPlayer().addItem(zbran);
        assertTrue(game.getPlayer().getInventory().contains(zbran), "Hrac by mel mit veci pred jejich zahozenim.");

        game.getPlayer().removeItem(zbran);
        game.getPlayer().getLocation().getLootTable().add(zbran.getId());

        assertFalse(game.getPlayer().getInventory().contains(zbran), "Hrac by zbran uz mit nemel.");
        assertTrue(game.getPlayer().getLocation().getLootTable().contains(zbran.getId()),
                "Zbran by mela lezet na zemi v lokaci.");
    }
}
