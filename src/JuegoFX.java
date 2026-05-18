import Estructuras.IndexedList;
import Pruebas.Interactuable.Interactuable;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Estado;
import Pruebas.Personajes.Jugador;
import Pruebas.Personajes.Posicion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JuegoFX extends Application{
    //ESTADO DEL JUEGO
    private Mapa mapa;
    private Jugador jugador;
    private int habitacionActualRender = -1;

    //JAVA FX
    private Pane root;
    private Pane capaRango;
    private Scene scene;

    //SPRITES
    private Image imgNormal;
    private Image imgAgua;
    private Image[] fondos;
    private Image tileRangoIm;

    //VISTAS

    private ImageView jugadorView;
    private ImageView fondoView;
    private IndexedList<ImageView> areaView;
    private IndexedList<ImageView> interactuablesView;

    //CONFIG
    private final int TILE = 16;


    @Override
    public void start(Stage stage) {
        inicializarModelo();            // Inicializa el modelo (datos del juego)
        inicializarInterfaz(stage);     // Inicializa la interfaz gráfica
        cargarSprites();                // Carga las imágenes/sprites
        crearJugador();                 // Crea y añade el jugador a la escena
        renderInteractuables();         // Carga los objetos interactuables en pantalla
        configurarControles();          // Configura el teclado/controles
        actualizarVista();              // Dibuja el primer estado del juego en pantalla
        stage.show();                   // Muestra la ventana
    }

    private void inicializarModelo() {
        mapa=new Mapa();
        jugador=new Jugador(5,5,0);
    }

    private void inicializarInterfaz(Stage stage) {
        root=new Pane();
        capaRango=new Pane();
        scene=new Scene(root,800,600);
        fondoView=new ImageView();
        fondoView.setSmooth(false);
        fondoView.setPreserveRatio(false);
        fondoView.setCache(true);
        root.getChildren().addAll(fondoView, capaRango);
        //Recentrar al cambiar el tamaño de la ventana
        scene.widthProperty().addListener((obs,oldVal,newVal) -> {
            actualizarVista();
        });
        scene.heightProperty().addListener((obs,oldVal,newVal)->{
            actualizarVista();
        });
        stage.setScene(scene);
        stage.setTitle("Juego JavaFX");
    }

    private void cargarSprites() {
        tileRangoIm=new Image("file:./src/sprites/tile_rango.png");
        imgNormal=new Image("file:./src/sprites/jugador.png");
        imgAgua=new Image("file:./src/sprites/jugador_agua.png");
        fondos=new Image[9];
        for (int i=0;i<fondos.length; i++) {
            fondos[i]=new Image("file:./src/sprites/habitacion_"+ i+".png");
        }
    }

    private void crearJugador() {
        jugadorView=new ImageView(imgNormal);
        jugadorView.setSmooth(false);
        root.getChildren().add(jugadorView);
        interactuablesView=new IndexedList<>();
    }

    private void renderInteractuables() {
        //Eliminar visuales anteriores
        for(int i=0;i<interactuablesView.len();i++) {
            root.getChildren().remove(interactuablesView.get(i));
        }
        interactuablesView=new IndexedList<>();
        int habitacion=jugador.getHabitacionActual();
        //Recorrer interactuables del mapa
        for(int i=0; i<mapa.getInteractuables().len();i++) {
            Interactuable inter=mapa.getInteractuables().get(i);
            if(inter.getHabitacion()==habitacion && inter.esVisible()) {
                ImageView view=new ImageView(new Image(inter.getSprite()));
                view.setX(inter.getX()*TILE+inter.getOffsetX());
                view.setY(inter.getY()*TILE+inter.getOffsetY());
                interactuablesView.append(view);
                root.getChildren().add(view);
            }
        }
    }

    private void configurarControles() {
        scene.setOnKeyPressed(e -> {
            KeyCode code=e.getCode();
            switch(code){
                case W ->jugador.mover(0,-1,mapa);
                case S ->jugador.mover(0,1,mapa);
                case A ->jugador.mover(-1,0,mapa);
                case D ->jugador.mover(1,0,mapa);
                case Q -> jugador.imprimirEventos();
                case E -> {
                    jugador.interactuar(mapa);
                    mapa.aplicarEventos(jugador);
                    renderInteractuables();
                }
            }
            actualizarVista();
        });
    }

    private void actualizarEstadoJuego() {
        if (habitacionActualRender!=jugador.getHabitacionActual()) {
            habitacionActualRender=jugador.getHabitacionActual();
            fondoView.setImage(fondos[habitacionActualRender]);
        }
    }

    private void renderJugador() {
        jugadorView.setX(jugador.getX()*TILE-1);
        jugadorView.setY(jugador.getY() * TILE-9);
        if (jugador.getEstado() ==Estado.AGUA) {
            jugadorView.setImage(imgAgua);
        } else {
            jugadorView.setImage(imgNormal);
        }
    }

    private void actualizarVista() {
        Celda[][] celdas=mapa.getHabitacion(jugador.getHabitacionActual()).getCeldas();
        int ancho=celdas[0].length;
        int alto=celdas.length;
        centrarMapa(ancho,alto);
        actualizarEstadoJuego();
        pintarAreaMovimiento();
        renderJugador();
        renderInteractuables();
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

    private void centrarMapa(int anchoTiles, int altoTiles) {
        double anchoMapa=anchoTiles*TILE;
        double altoMapa=altoTiles*TILE;
        root.setLayoutX((scene.getWidth()-anchoMapa)/2);
        root.setLayoutY((scene.getHeight()-altoMapa)/2);
    }

    public static void main(String[] args) {
        launch();
    }
}