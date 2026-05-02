public class Main {

    public static void main(String[] args) {

        Input input = new Input();
        input.addAccion("w");
        input.addAccion("w");
        input.addAccion("d");
        input.addAccion("d");
        input.addAccion("s");

        Game game=new Game(input);
        game.run();
    }
}