import game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.inicialization();

        System.out.println("Ukazka pohybu");
        game.processCommand("jdi sever");
        game.processCommand("jdi východ");
        game.processCommand("jdi sever");
        game.processCommand("jdi západ");
        game.processCommand("jdi jih");
        game.processCommand("jdi jih");
        game.processCommand("jdi jih");
        game.processCommand("jdi západ");
        System.out.println("Konec ukazky");
    }
}
