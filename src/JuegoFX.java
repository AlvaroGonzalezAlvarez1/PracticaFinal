import Pruebas.Mapa.Habitacion;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JuegoFX extends Application {

    private Pane root;
    private Mapa mapa;
    private Jugador jugador;
    private Image paredImg;
    private Image sueloImg;
    private Image puertaImg;
    private Image jugadorImg;
    private ImageView jugadorView;


    private int TILE = 32; //cantidad de bits por celdas (da lo mismo, cambiar en un futuro)

    @Override
    public void start(Stage stage) {

        mapa=new Mapa();
        jugador=new Jugador(2,2,0);

        //Carga texturas
        paredImg=new Image("file:./src/sprites/pared.png");
        sueloImg = new Image("file:./src/sprites/suelo.png");
        puertaImg = new Image("file:./src/sprites/puerta.png");

        jugadorImg=new Image("file:./src/sprites/jugador.png");
        jugadorView=new ImageView(jugadorImg);
        jugadorView.setSmooth(false);   //Evita para el pixel-art que haga cosa raras

        //Contenedor visual
        root=new Pane();
        Scene scene=new Scene(root, 800, 600);
        scene.setOnKeyPressed(e -> {    //Cambiar en un futuro a pinchar casilla
            KeyCode code=e.getCode();
            switch (code) {
                case W -> jugador.mover(0, -1, mapa);
                case S -> jugador.mover(0, 1, mapa);
                case A -> jugador.mover(-1, 0, mapa);
                case D -> jugador.mover(1, 0, mapa);
            }
            actualizarVista(); //Se crea cada interaccion
        });
        actualizarVista();
        stage.setScene(scene);
        stage.setTitle("Juego JavaFX");
        stage.show();
    }

    //Dibuja TODA la habitacion de cero
    private void actualizarVista() {
        root.getChildren().clear();
        Habitacion h=mapa.getHabitacion(jugador.getHabitacionActual());
        for (int y=0;y<h.getCeldas().length;y++) {
            for (int x=0;x<h.getCeldas()[y].length;x++) {
                ImageView tile=new ImageView();
                tile.setFitWidth(TILE);
                tile.setFitHeight(TILE);
                tile.setSmooth(false);
                tile.setX(x*TILE);
                tile.setY(y*TILE);
                switch (h.getCeldas()[y][x].getTipo()) {
                    case PARED->tile.setImage(paredImg);
                    case SUELO->tile.setImage(sueloImg);
                    case PUERTA->tile.setImage(puertaImg);
                    case VACIO->{
                        continue;
                    }
                }
                root.getChildren().add(tile);
            }
        }
        //Dibujar jugador
        jugadorView.setX(jugador.getX() * TILE);
        jugadorView.setY(jugador.getY() * TILE-16); //El archivo mide 16 pixeles más para prueba
        root.getChildren().add(jugadorView);
    }

    public static void main(String[] args) {
        launch();
    }
}