import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuPrincipalFX extends Application {

    private final int WIDTH=240;
    private final int HEIGHT=160;

    private ImageView background;

    @Override
    public void start(Stage stage) {
        Pane root=new Pane();
        Image img=new Image("file:./src/sprites/menu_principal.png");
        background=new ImageView(img);
        background.setFitWidth(WIDTH);
        background.setFitHeight(HEIGHT);
        background.setPreserveRatio(false);
        background.setSmooth(false);
        root.getChildren().add(background);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnMouseClicked(e -> {
            double x=e.getX();
            double y=e.getY();
            int slot=obtenerSlot(x,y);
            if(slot!=0){
                JuegoFX juego=new JuegoFX();
                juego.iniciarDesdeSlot(slot);
                try {
                    Stage juegoStage = new Stage();
                    juego.start(juegoStage);
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.show();
    }

    private int obtenerSlot(double x, double y){
        int slot=0;
        if(x>=9 && x<=77 && y>=13 && y<=38){
            slot=1;
        }
        else if(x>=9 && x<=77 && y>=41 && y<=66){
            slot=2;
        }
        else if(x>=9 && x<=77 && y>=69 && y<=94){
            slot=3;
        }
        return slot;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
