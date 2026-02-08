package Commands;

import game.Game;

public class PouzijCommand implements Command {

    @Override
    public void execute(Game game, String[] args) {
        if (args.length < 2) {
            System.out.println("Co chceš použít?");
            return;
        }
        
        String target = args[1];
        game.Player player = game.getPlayer();
        game.Location loc = player.getLocation();
        
        /*
         * Generator (Sklep)
         */
        if (loc.getId().equals("loc_sklep") && (target.equalsIgnoreCase("generator") || target.equalsIgnoreCase("pojistky"))) {
            if (game.isPowerRestored()) {
                System.out.println("Generátor už běží.");
            } else {
                System.out.println("Nahodil jsi pojistky a nastartoval generátor. Světla blikla a rozsvítila se!");
                game.setPowerRestored(true);
            }
            return;
        }
        
        /*
         * Transmitter (Sklep) - WIN CONDITION
         */
        if (loc.getId().equals("loc_sklep") && (target.equalsIgnoreCase("vysilac") || target.equalsIgnoreCase("vysilacku"))) {
            if (!game.isBossDefeated()) {
                System.out.println("Vysílač je hluchý. Signál je rušen bouřkou... nebo něčím jiným.");
            } else {
                if (player.hasItem("item_badge")) {
                    System.out.println("Zapínáš vysílač a voláš SOS. 'Tady strážník Tomáš, mám důkazy o korupci a tělo Černohorského...'");
                    System.out.println("Dispečink potvrzuje příjem. Pomoc je na cestě.");
                    System.out.println("GRATULUJI! Přežil jsi poslední směnu a očistil jméno policie.");
                    game.setGameOver(true);
                } else {
                    System.out.println("Můžeš zavolat pomoc, ale nemáš ten 'Krvavý odznak'. Uvěří ti někdo bez důkazu?");
                    System.out.println("Možná bys ho měl najít v celách.");
                }
            }
            return;
        }

        /*
         * Safe (Kancelar)
         */
        if (loc.getId().equals("loc_kancelar") && target.equalsIgnoreCase("trezor")) {
             if (game.isSafeOpened()) {
                 System.out.println("Trezor je už otevřený a prázdný.");
             } else {
                 if (player.hasItem("item_file_1998")) {
                     System.out.println("Podle informací ze spisu zadáváš kód... Cvak! Trezor se otevřel.");
                     System.out.println("Uvnitř jsi našel DIKTAFON a SLUŽEBNÍ ZBRAŇ!");
                     game.setSafeOpened(true);
                     loc.getLootTable().add("item_recorder");
                     loc.getLootTable().add("item_weapon");
                 } else {
                     System.out.println("Trezor je zamčený. Neznáš číselný kód.");
                 }
             }
             return;
        }

        /*
         * Check if player has the item (by name or ID)
         */
        game.Item foundItem = null;
        for (game.Item i : player.getInventory()) {
             if (i.getName().equalsIgnoreCase(target) || i.getId().equalsIgnoreCase(target)) {
                 foundItem = i;
                 break;
             }
        }

        if (foundItem != null) {
            String id = foundItem.getId();

            /*
             * Medkit
             */
            if (id.equals("item_medkit")) {
                 System.out.println("Použil jsi lékárničku. Tvé zdraví je plně obnoveno.");
                 game.healPlayer();
                 for(game.Item i : player.getInventory()) {
                     if(i.getId().equals("item_medkit")) {
                         player.removeItem(i);
                         break;
                     }
                 }
            }
            
            /*
             * Universal Key
             */
            else if (id.equals("item_key_universal")) {
                if (loc.getNeighbors().containsValue("loc_archiv") && !game.isArchiveUnlocked()) {
                    System.out.println("Odemkl jsi dveře do archivu.");
                    game.setArchiveUnlocked(true);
                } else if (!game.isArchiveUnlocked()) {
                     System.out.println("Tady není co odemykat.");
                } else {
                    System.out.println("Dveře jsou již odemčené.");
                }
            } 
            else {
                System.out.println("Tento předmět nelze přímo použít.");
            }
        } else {
            System.out.println("Tohle nemáš v inventáři.");
        }
    }
}
