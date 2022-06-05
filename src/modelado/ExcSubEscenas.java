
package modelado;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;


public class ExcSubEscenas extends SubScene {

    private final static String FONT_PATH = "src/modelado/recursos/kenvector_future.tlf";
    private   String BACKGROUND_IMAGE = "File:ayuda.png";
    private  boolean ocultar;
    private boolean subs=true;

    public ExcSubEscenas(String BACKGROUND_IMAGE) {
        super(new AnchorPane(),600, 340);
        prefWidth(600);
        prefHeight(340);

        this.BACKGROUND_IMAGE=BACKGROUND_IMAGE;
        BackgroundImage myBI= new BackgroundImage(new Image(BACKGROUND_IMAGE,600,340,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        AnchorPane root2= (AnchorPane) this.getRoot();
        root2.setBackground(new Background(myBI));

        setLayoutX(1024);
        setLayoutY(100);



    }

    /** INSTANCIAMOS UN OBJETO TRANSLATETRANSITION  **/
    public void moverSubScene (){
        TranslateTransition tr = new TranslateTransition();
//      TIEMPO QUE DURA LA TRANSICION DE UNA COORDENADA A OTRA
        tr.setDuration(Duration.seconds(0.3));
        /**LE ASIGNAMOS EL  ELEMENTO QUE QUEREMOS MOVER
         EN ESTE CASO, NUESTRO OBJETO DE LA CLASE EXSUBSCENES**/
        tr.setNode(this);
        /**SI ES VERDADERO LO MOSTRAMOS EN PANTALLA CON LA TRANSICION **/
        if(ocultar){
            tr.setToX(-676);
            ocultar=false;
//      LO SACAMOS DE LA PANTALLA CON LA TRANSICION
        }else{
            tr.setToX(0);
            ocultar = true;
        }
        tr.play();

    }

    public String getBACKGROUND_IMAGE() {
        return BACKGROUND_IMAGE;
    }

    public void setBACKGROUND_IMAGE(String BACKGROUND_IMAGE) {
        this.BACKGROUND_IMAGE = BACKGROUND_IMAGE;
    }



}
