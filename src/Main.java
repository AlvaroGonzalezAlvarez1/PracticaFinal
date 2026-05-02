public class Main {

    public static void main(String[] args) {

        Input input = new Input();

        String[] acciones = {"s","s","s","d","d","s","s","s","d","d","d","d",
                            "w","w","w","w","w","d","d","d","a","a","a","w",
                            "w","w","w","a","a","a","a","a"};

        for (int i = 0; i < acciones.length; i++) {
            input.addAccion(acciones[i]);
        }

        Game game = new Game(input);
        game.run();
    }
}

//PROBLEMAS: Si entras a una habitación tienes que salir de la casilla puerta y volver
//a pisarla para salir
//Opciónes: Que en vez de "teletransportarse a la casilla puerta te teletransporte una casilla mas alante"
