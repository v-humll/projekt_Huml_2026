package test;

import game.Game;
import game.Item;
import game.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemPickUpTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.inicialization();
        Location startLoc = game.getWorld().findLocation("loc_recepce");
        game.getPlayer().setLocation(startLoc);
    }

    @Test
    public void testItemPickUpSuccess() {
        Item klic = game.getWorld().findItem("item_key_universal");
        assertNotNull(klic, "Klic by mel v hernich datech existovat.");

        game.getPlayer().getLocation().getLootTable().add(klic.getId());
        assertFalse(game.getPlayer().getInventory().contains(klic), "Hrac by na startu klic mit nemel.");

        game.getPlayer().addItem(klic);
        game.getPlayer().getLocation().getLootTable().remove(klic.getId());

        assertTrue(game.getPlayer().getInventory().contains(klic), "Klic se nepridal hracovi do inventare.");
        assertFalse(game.getPlayer().getLocation().getLootTable().contains(klic.getId()),
                "Klic zustal na zemi prestoze ho hrac zvedl.");
    }
}
