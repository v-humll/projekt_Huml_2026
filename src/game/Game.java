package game;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private GameData world;
    private Player player;
    private boolean gameOver;
    private java.util.Map<String, Commands.Command> commands;
    
    // Boss fight state
    private boolean bossFightActive;
    private int bossHp;
    private int playerHp;
    private boolean playerDefending;
    private final int MAX_BOSS_HP = 50; // Example
    private final int MAX_PLAYER_HP = 100; // Example

    public boolean isPlayerDefending() {
        return playerDefending;
    }

    public void setPlayerDefending(boolean defending) {
        this.playerDefending = defending;
    }

    public boolean isBossFightActive() {
        return bossFightActive;
    }

    public void startBossFight() {
        this.bossFightActive = true;
        this.bossHp = MAX_BOSS_HP;
        this.playerHp = MAX_PLAYER_HP;
    }

    public int getBossHp() {
        return bossHp;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void damageBoss(int amount) {
        this.bossHp -= amount;
        if (this.bossHp < 0) this.bossHp = 0;
    }

    public void damagePlayer(int amount) {
        this.playerHp -= amount;
        if (this.playerHp < 0) this.playerHp = 0;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    public java.util.Map<String, Commands.Command> getCommandsMap() {
        return commands;
    }

    public GameData getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Starts the game and loads everything up.
     * Loads world from JSON and makes new player.
     * Puts player in reception.
     * Also prepares the command "jdi".
     */

    public void inicialization(){
        world = GameData.loadGameDataFromResources("/gamedata.json");
        player = new Player();

        if (world.locations != null && !world.locations.isEmpty()) {
             try {
                 player.setLocation(world.findLocation("loc_recepce"));
             } catch (IllegalArgumentException e) {
                 player.setLocation(world.locations.get(0));
             }
        }
        gameOver = false;
        
        commands = new java.util.HashMap<>();
        commands.put("jdi", new Commands.JdiCommand());
        commands.put("napoveda", new Commands.NapovedaCommand());
        commands.put("konec", new Commands.KonecCommand());
        commands.put("inventar", new Commands.InventarCommand());
        commands.put("vezmi", new Commands.VezmiCommand());
        commands.put("pouzij", new Commands.PouzijCommand());
        commands.put("prozkoumej", new Commands.ProzkoumejCommand());
        commands.put("rozhlednise", new Commands.RozhledniSeCommand());
        commands.put("mluv", new Commands.MluvCommand());
        commands.put("utok", new Commands.UtokCommand());
        commands.put("kryjse", new Commands.KryjSeCommand());
        commands.put("prebit", new Commands.PrebitCommand());
    }

    /**
     * Main loop for the game.
     * First it activates inicialization which loads everything and shows welcome message.
     * Then it keeps asking player for commands until game is over.
     */

    public void start(){
        inicialization();
        System.out.println("Hra začala!");
        if (player.getLocation() != null) {
            System.out.println("Nacházíš se: " + player.getLocation().getName());
            System.out.println("Popis: " + player.getLocation().getDescription());
        }

        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            System.out.print("> ");
            String input = scanner.nextLine();
            processCommand(input);
        }
    }

/**
 * Reads the input and finds which command to run.
 * First it cleans the text and checks if you want to stop with "konec".
 * Then it looks in the map for the command.
 * If it finds it, it runs it. If not, it says it does not know the word.
 */

    public void processCommand(String input) {
        String[] parts = input.trim().split(" ", 2);
        String commandName = parts[0].toLowerCase();

        if (commandName.equals("konec")) {
            gameOver = true;
            return;
        }

        Commands.Command command = commands.get(commandName);
        if (command != null) {
            command.execute(this, parts);
        } else {
            System.out.println("Neznámý příkaz.");
        }
    }


}
