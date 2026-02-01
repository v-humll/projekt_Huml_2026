package Commands;

import game.Game;

public class MluvCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        game.Location loc = game.getPlayer().getLocation();
        if (loc == null) return;
        
        java.util.List<game.GameCharacter> chars = game.getWorld().characters;
        if (chars == null) {
            System.out.println("Nikdo tu není.");
            return;
        }

        boolean found = false;
        for (game.GameCharacter npc : chars) {
            if (npc.getLocationId() != null && npc.getLocationId().equals(loc.getId())) {
                System.out.println(npc.getName() + " říká: \"" + npc.getDialog() + "\"");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Nikdo tu není.");
        }
    }
}
