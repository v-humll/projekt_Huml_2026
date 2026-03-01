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
/*
 * Check if there are any NPCs in the current location
 * If there are, print their name
 * If there are no NPCs in the current location, print "Nikdo tu není."
 */
        boolean found = false;
        for (game.GameCharacter npc : chars) {
            if (npc.getLocationId() != null && npc.getLocationId().equals(loc.getId())) {
                System.out.println(npc.getName() + " je tu.");
                
                if (npc.getId().equals("char_kohout")) {
                     if (game.isKohoutInteracted()) {
                         System.out.println("Kohout: 'Už jsem ti všechno řekl, ne?'");
                     } else {
                         // Interactive Dialogue
                         System.out.println("Kohout vypadá vyděšeně. Můžeš s ním mluvit 'slusne' nebo 'drsne'.");
                         System.out.println("Jak chceš mluvit?");
                         java.util.Scanner sc = new java.util.Scanner(System.in);
                         String choice = sc.nextLine();
                         
                         if (choice.contains("slusne")) {
                             System.out.println("Uklidnil jsi ho. Kohout ti řekl, že Černohorský se schovává v celách a dal ti plný zásobník nábojů.");
                             game.getPlayer().addAmmo(10);
                             System.out.println("+10 nábojů.");
                         } else {
                             System.out.println("Zařval jsi na něj. Kohout se lekl, hodil ti pár nábojů a utekl do stínů.");
                             game.getPlayer().addAmmo(5);
                             System.out.println("+5 nábojů.");
                         }
                         game.setKohoutInteracted(true);
                     }
                } else {
                    // Default behavior for other NPCs
                    System.out.println(npc.getName() + " říká: \"" + npc.getDialog() + "\"");
                }
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Nikdo tu není.");
        }
    }
}
