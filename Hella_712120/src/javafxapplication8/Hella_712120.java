package javafxapplication8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Lenne
 */
public class Hella_712120 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        try{
            String host = "jdbc:derby://localhost:1527/Hella";
            String user_name = "Lennert";
            String user_pass = "admin";

            Connection con = DriverManager.getConnection(host, user_name, user_pass);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT*FROM HELLA_ACTUATORS";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            int g_number = rs.getInt("G_NUMBER");
            int min_value = rs.getInt("MIN_VALUE");
            
            
            
            System.out.println(g_number + " " + min_value);
            
            
            
            
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        
        
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
