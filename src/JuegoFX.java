import Estructuras.IndexedList;
import Pruebas.Interactuable.Interactuable;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Enemigo.Enemigo;
import Pruebas.Personajes.Estado;
import Pruebas.Personajes.Jugador.Jugador;
import Pruebas.Personajes.Posicion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JuegoFX extends Application{
    //ESTADO DEL JUEGO
    private Mapa mapa;
    private Jugador jugador;
    private int habitacionActualRender = -1;
    private IndexedList<Posicion> areaMovimientoActual;

    //JAVA FX
    private Pane root;

    private Pane capaJuego;

    private Pane capaFondo;
    private Pane capaPuentes;
    private Pane capaInteractuables;
    private Pane capaEnemigos;
    private Pane capaRango;
    private Pane capaJugador;
    private Pane capaUI;
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
    private IndexedList<ImageView> enemigosView;
    private IndexedList<ImageView> puentesView;

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
        root = new Pane();

        capaJuego=new Pane();
        capaFondo=new Pane();
        capaPuentes=new Pane();
        capaInteractuables=new Pane();
        capaEnemigos=new Pane();
        capaRango=new Pane();
        capaJugador=new Pane();
        capaUI=new Pane();
        capaJuego.getChildren().addAll(capaFondo,capaPuentes,capaInteractuables,capaEnemigos,capaRango,capaJugador);
        scene=new Scene(root,800,600);
        fondoView=new ImageView();
        capaFondo.getChildren().add(fondoView);
        fondoView.setSmooth(false);
        fondoView.setPreserveRatio(false);
        fondoView.setCache(true);
        root.getChildren().addAll(capaJuego,capaUI);
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
        capaJugador.getChildren().add(jugadorView);
        interactuablesView=new IndexedList<>();
    }

    private void renderInteractuables() {
        //Eliminar visuales anteriores
        for(int i=0;i<interactuablesView.len();i++) {
            capaInteractuables.getChildren().remove(interactuablesView.get(i));        }
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
                capaInteractuables.getChildren().add(view);            }
        }
    }

    private void renderPuentes(){
        //BORRAR ANTERIORES
        if(puentesView!=null){
            for(int i=0;i<puentesView.len();i++){
                capaPuentes.getChildren().remove(puentesView.get(i));
                puentesView.get(i);
            }
        }
        puentesView=new IndexedList<>();
        //RECORRER EVENTOS
        for(int i=0;i<jugador.getEventos().len();i++){
            String evento=jugador.getEventos().get(i);
            if(evento.startsWith("palanca_activa_")){
                String[] partes=evento.split("_");
                int habitacion=Integer.parseInt(partes[2]);
                //Solo renderiza habitación actual
                if(habitacion==jugador.getHabitacionActual()){
                    for(int j=5;j<partes.length;j+=3){
                        int x=Integer.parseInt(partes[j]);
                        int y=Integer.parseInt(partes[j+1]);
                        int sprite=Integer.parseInt(partes[j+2]);
                        String ruta="";
                        //0 horizontal
                        if(sprite==0){
                            ruta="file:./src/sprites/puente_horizontal.png";
                        }
                        //1 vertical
                        else{
                            ruta="file:./src/sprites/puente_vertical.png";
                        }
                        ImageView view=new ImageView(new Image(ruta));
                        view.setX(x*TILE);
                        view.setY(y*TILE);
                        puentesView.append(view);
                        capaPuentes.getChildren().add(view);
                    }
                }
            }
        }
    }

    private void renderEnemigos() {
        if (enemigosView!=null) {
            for (int i=0;i<enemigosView.len();i++) {
                capaEnemigos.getChildren().remove(enemigosView.get(i));
            }
        }
        enemigosView=new IndexedList<>();
        int habitacion=jugador.getHabitacionActual();
        IndexedList<Enemigo>lista=mapa.getHabitacion(habitacion).getEnemigos();
        for (int i=0;i<lista.len();i++) {
            Enemigo e=lista.get(i);
            if (e.estaVivo()) {
                ImageView view=new ImageView(new Image(e.getSprite()));
                view.setX(e.getX()*TILE+e.getOffsetX());
                view.setY(e.getY()*TILE+e.getOffsetY());
                enemigosView.append(view);
                capaEnemigos.getChildren().add(view);
            }
        }
    }

    private void configurarControles() {
        scene.setOnMouseClicked(e -> {
            double mouseX=e.getSceneX()-capaJuego.getLayoutX();
            double mouseY=e.getSceneY()-capaJuego.getLayoutY();
            int tileX=(int)(mouseX/TILE);
            int tileY=(int)(mouseY/TILE);
            Interactuable inter=mapa.getInteractuable(jugador.getHabitacionActual(),tileX,tileY);
            if(inter!=null && jugador.estaAlLado(tileX,tileY)){
                jugador.mirarHacia(tileX,tileY);
                jugador.interactuar(mapa);
                mapa.aplicarEventos(jugador);
            }
            if(celdaEnRango(tileX,tileY)){
                jugador.moverA(tileX,tileY,mapa);
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
        jugadorView.setY(jugador.getY()*TILE-9);
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
        renderEnemigos();
        renderPuentes();
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
        areaMovimientoActual=mapa.getHabitacion(jugador.getHabitacionActual()).getAreaMovimiento(jugador,jugador.getX(),jugador.getY(),jugador.getRango());
        for(int i=0;i<areaMovimientoActual.len();i++){
            Posicion p=areaMovimientoActual.get(i);
            ImageView tile=new ImageView(tileRangoIm);
            tile.setX(p.getX()*TILE);
            tile.setY(p.getY()*TILE);
            areaView.append(tile);
            capaRango.getChildren().add(tile);
        }
    }

    private boolean celdaEnRango(int x, int y){
        boolean resultado=false;
        for(int i=0;i<areaMovimientoActual.len();i++){
            Posicion p=areaMovimientoActual.get(i);
            if(p.getX()==x && p.getY()==y){
                resultado=true;
            }
        }
        return resultado;
    }

    private void centrarMapa(int anchoTiles, int altoTiles) {
        double anchoMapa=anchoTiles*TILE;
        double altoMapa=altoTiles*TILE;
        capaJuego.setLayoutX((scene.getWidth()-anchoMapa)/2);
        capaJuego.setLayoutY((scene.getHeight()-altoMapa)/2);
    }

    public static void main(String[] args) {
        launch();
    }
}