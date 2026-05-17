import Estructuras.IndexedList;
import Pruebas.Interactuable.Interactuable;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Estado;
import Pruebas.Personajes.Jugador;

import Pruebas.Personajes.Posicion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JuegoFX extends Application {

    private Pane root;
    private Pane capaRango;
    private Mapa mapa;
    private Jugador jugador;

    private Image imgNormal;
    private Image imgAgua;
    private ImageView jugadorView;

    private Image[] fondos;
    private ImageView fondoView;
    private Image tileRangoIm;
    private IndexedList<ImageView> areaView;

    private int habitacionActualRender=-1;

    private IndexedList<ImageView> interactuablesView;

    //Cantidad de bits por celdas
    private int TILE = 16;

    @Override
    public void start(Stage stage) {
        mapa=new Mapa();
        jugador=new Jugador(5,5,0);
        tileRangoIm=new Image("file:./src/sprites/tile_rango.png");
        areaView=new IndexedList<>();
        root=new Pane();
        capaRango=new Pane();
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
        root.getChildren().add(capaRango);

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
        pintarAreaMovimiento();
        // Mover jugador
        jugadorView.setX(jugador.getX()*TILE-1);
        jugadorView.setY(jugador.getY() * TILE-9);
        if (jugador.getEstado() ==Estado.AGUA) {
            jugadorView.setImage(imgAgua);
        } else {
            jugadorView.setImage(imgNormal);
        }
    }

    private void pintarAreaMovimiento() {
        // borrar anterior
        if(areaView!=null){
            for(int i=0;i<areaView.len();i++){
                root.getChildren().remove(areaView.get(i));
            }
        }
        areaView=new IndexedList<>();
        capaRango.getChildren().clear();
        IndexedList<Posicion> area=mapa.getHabitacion(jugador.getHabitacionActual()).getAreaMovimiento(jugador,jugador.getX(),jugador.getY(),jugador.getRango());
        for(int i=0;i<area.len();i++){
            Posicion p=area.get(i);
            ImageView tile=new ImageView(tileRangoIm);
            tile.setX(p.getX()*TILE);
            tile.setY(p.getY()*TILE);
            areaView.append(tile);
            capaRango.getChildren().add(tile);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}