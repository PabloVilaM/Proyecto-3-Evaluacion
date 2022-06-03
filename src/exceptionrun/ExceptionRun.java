package exceptionrun;

import connection.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
/**
 *
 * @author celta
 */
public class ExceptionRun extends Application {
    
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
