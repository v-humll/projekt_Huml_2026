package game;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private GameData world;
    private Player player;
    private boolean gameOver;
    private java.util.Map<String, Commands.Command> commands;

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
