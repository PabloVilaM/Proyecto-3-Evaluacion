package exceptionrun;

import connection.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.stage.Stage;
import view.*;


public class ExceptionRun extends Application {
    /**
     * El método de inicio. Junto al main se encarga de iniciar el "Stage" que viene siendo la ventana principal
     * @param primaryStage El primary Stage es la ventana principal y se lo pasaremos desde el manager de vista
     * @throws SQLException Recogemos los posibles errores de la base de datos
     * @throws ClassNotFoundException Recogemos los posibles errores de la base de datos
     */
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
//        Statement sentence=null;
//        Connection c = null;
//        
//         Conexion cn = new Conexion(sentence, c, "Puntuaciones");
//       cn.conectarBase();
//         cn.crearBase("Puntuaciones");
//        
//        cn.consultar("Puntuaciones");
        try{
            
            ViewManager manager= new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();

        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    /**
     * @param args Los argumentos de comandos de linea
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
