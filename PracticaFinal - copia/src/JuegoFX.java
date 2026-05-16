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

    private Image jugadorImg;
    private ImageView jugadorView;

    private Image[] fondos;
    private ImageView fondoView;

    private int habitacionActualRender = -1;

    //Cantidad de bits por celdas
    private int TILE = 16;

    @Override
    public void start(Stage stage) {
        mapa=new Mapa();
        jugador=new Jugador(5,5,0);

        root=new Pane();
        Scene scene=new Scene(root, 800, 600);

        //Habitaciones
        fondos=new Image[6];
        for (int i = 0; i < fondos.length; i++) {
            fondos[i] = new Image("file:./src/sprites/habitacion_" + i + ".png");
        }
        fondoView=new ImageView();
        fondoView.setSmooth(false);
        fondoView.setPreserveRatio(false);
        fondoView.setCache(true);
        root.getChildren().add(fondoView);
        //Jugador
        jugadorImg = new Image("file:./src/sprites/jugador.png");

        jugadorView=new ImageView(jugadorImg);
        jugadorView.setSmooth(false);
        root.getChildren().add(jugadorView);
        //Input
        scene.setOnKeyPressed(e -> {
            KeyCode code=e.getCode();
            switch (code) {
                case W -> jugador.mover(0,-1,mapa);
                case S -> jugador.mover(0,1,mapa);
                case A -> jugador.mover(-1,0,mapa);
                case D -> jugador.mover(1,0,mapa);
            }
            actualizarVista();
        });
        //Primer render
        actualizarVista();
        stage.setScene(scene);
        stage.setTitle("Juego JavaFX");
        stage.show();
    }

    // Dibuja TODA la habitación de cero
    private void actualizarVista() {
        // Cambiar fondo solo si cambia la habitación
        if(habitacionActualRender!=jugador.getHabitacionActual()) {
            habitacionActualRender=jugador.getHabitacionActual();
            fondoView.setImage(fondos[habitacionActualRender]
            );
        }
        // Mover jugador
        jugadorView.setX(jugador.getX()*TILE-1);
        jugadorView.setY(jugador.getY() * TILE-9);
    }

    public static void main(String[] args) {
        launch();
    }
}