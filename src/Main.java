import Pruebas.Juego.Game;
import Pruebas.Juego.Input;

public class Main {

    public static void main(String[] args) {

        Input input = new Input();
        Game game = new Game(input);

        game.run();
    }
}

//PROBLEMAS: Si entras a una habitación tienes que salir de la casilla puerta y volver
//a pisarla para salir
//Opciónes: Que en vez de "teletransportarse a la casilla puerta te teletransporte una casilla mas alante"
