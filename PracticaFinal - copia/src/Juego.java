import Pruebas.Juego.Game;
import Pruebas.Juego.Input;

public class Juego {

    public static void main(String[] args) {

        Input input = new Input();
        Game game = new Game(input);

        game.run();
    }
}

//Para testear el juego sin que cargue texturas