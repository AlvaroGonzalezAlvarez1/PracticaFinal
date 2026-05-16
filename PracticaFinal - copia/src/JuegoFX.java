import Estructuras.IndexedList;
import Pruebas.Interacciones.Interactuable;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Estado;
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

    private Image imgNormal;
    private Image imgAgua;
    private ImageView jugadorView;

    private Image[] fondos;
    private ImageView fondoView;

    private int habitacionActualRender=-1;

    private IndexedList<ImageView> interactuablesView;

    //Cantidad de bits por celdas
    private int TILE = 16;

    @Override
    public void start(Stage stage) {
        mapa=new Mapa();
        jugador=new Jugador(5,5,0);

        root=new Pane();
        Scene scene=new Scene(root, 800, 600);

        //Habitaciones
        fondos=new Image[7];
        for (int i=0; i<fondos.length;i++) {
            fondos[i]=new Image("file:./src/sprites/habitacion_" + i + ".png");
        }
        fondoView=new ImageView();
        fondoView.setSmooth(false);
        fondoView.setPreserveRatio(false);
        fondoView.setCache(true);
        root.getChildren().add(fondoView);

        //Jugador
        imgNormal=new Image("file:./src/sprites/jugador.png");
        imgAgua=new Image("file:./src/sprites/jugador_agua.png");
        jugadorView=new ImageView(imgNormal);
        jugadorView.setSmooth(false);
        root.getChildren().add(jugadorView);

        //Interactuables
        interactuablesView=new IndexedList<>();
        habitacionActualRender=jugador.getHabitacionActual();
        fondoView.setImage(fondos[jugador.getHabitacionActual()]);
        cargarInteractuables();

        //Input
        scene.setOnKeyPressed(e -> {
            KeyCode code=e.getCode();
            switch (code) {
                case W -> jugador.mover(0,-1,mapa);
                case S -> jugador.mover(0,1,mapa);
                case A -> jugador.mover(-1,0,mapa);
                case D -> jugador.mover(1,0,mapa);
                case E -> {
                    jugador.interactuar(mapa);
                    cargarInteractuables();
                }
            }
            actualizarVista();
        });
        //Primer render
        actualizarVista();
        stage.setScene(scene);
        stage.setTitle("Juego JavaFX");
        stage.show();
    }


    private void cargarInteractuables() {
        //Eliminar visuales anteriores
        for(int i=0;i<interactuablesView.len();i++) {
            root.getChildren().remove(interactuablesView.get(i));
        }
        interactuablesView=new IndexedList<>();
        //Recorrer interactuables del mapa
        for(int i=0; i<mapa.getInteractuables().len();i++) {
            Interactuable inter=mapa.getInteractuables().get(i);
            if(inter.getHabitacion()==jugador.getHabitacionActual()) {
                ImageView view=new ImageView(new Image(inter.getSprite()));
                view.setX(inter.getX()*TILE+inter.getOffsetX());
                view.setY(inter.getY()*TILE+inter.getOffsetY());
                interactuablesView.append(view);
                root.getChildren().add(view);
            }
        }
    }
    // Dibuja TODA la habitación de cero
    private void actualizarVista() {
        // Cambiar fondo solo si cambia la habitación
        if(habitacionActualRender!=jugador.getHabitacionActual()) {
            habitacionActualRender=jugador.getHabitacionActual();
            fondoView.setImage(fondos[habitacionActualRender]);
            cargarInteractuables();
        }
        // Mover jugador
        jugadorView.setX(jugador.getX()*TILE-1);
        jugadorView.setY(jugador.getY() * TILE-9);
        if (jugador.getEstado() ==Estado.AGUA) {
            jugadorView.setImage(imgAgua);
        } else {
            jugadorView.setImage(imgNormal);
        }
    }



    public static void main(String[] args) {
        launch();
    }
}