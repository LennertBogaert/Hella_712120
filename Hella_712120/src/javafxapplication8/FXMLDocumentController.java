package javafxapplication8;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Lenne
 */
public class FXMLDocumentController implements Initializable {
    
    Serial serial = new Serial("COM16");
    private int[] send = new int[8];
    DBConnect database = new DBConnect();
    
    @FXML
    private Button sweep;
    @FXML
    private Button set_min;
    @FXML
    private Button set_max;
    @FXML
    private TextArea input_set_min;
    @FXML
    private TextArea input_set_max;
    @FXML
    private Button Button_140Hz;
    @FXML
    private Button Button_300Hz;
    @FXML
    private Slider Slider_pwm;
    @FXML
    LineChart<?, ?> position_chart;
    XYChart.Series series = new XYChart.Series(); 
    @FXML
    private Button find_and_set_end_positions;
    @FXML
    private Button update;
    @FXML
    private Label label_max;
    @FXML
    private Label label_min;

    @FXML
    void button_pressed_connect(ActionEvent event) {
        serial.connect("COM16");
    }
    
    @FXML
    void button_pressed_disconnect(ActionEvent event) {
        serial.disconnect();
    }
    
    @FXML
    void button_pressed_140Hz(ActionEvent event) {
        System.out.println("140Hz");
        send[0] = 1;
        send[1] = 1;
        serial.send_int_array(send);
    }

    @FXML
    void button_pressed_300Hz(ActionEvent event) {
        System.out.println("300Hz");
        send[0] = 1;
        send[1] = 2;
        serial.send_int_array(send);
    }

    @FXML
    void button_pressed_ok(ActionEvent event) {
        send[0] = 2;
        send[1] = (char) Slider_pwm.getValue();
        serial.send_int_array(send);
    }
    
    @FXML
    void button_pressed_set_max(ActionEvent event) {
        send[0] = 3;
        send[1] = Integer.parseInt(input_set_max.getText())/256;
        send[2] = Integer.parseInt(input_set_max.getText())%256;
        serial.send_int_array(send);
    }

    @FXML
    void button_pressed_set_min(ActionEvent event) {
        send[0] = 4;
        send[1] = Integer.parseInt(input_set_min.getText())/256;
        send[2] = Integer.parseInt(input_set_min.getText())%256;
        serial.send_int_array(send);
    }
    
    @FXML
    void button_pressed_find_and_set_end_positions(ActionEvent event) {
        send[0] = 1;
        send[1] = 3;
        serial.send_int_array(send);
    }
    
    @FXML
    void button_pressed_update(ActionEvent event) {
        //XYChart.Series series = new XYChart.Series();
        //series.setName("Hella position");   
        //series.getData().add(new XYChart.Data(serial.get_x(), serial.get_y())); 
        //position_chart.getData().add(series);
        series.getData().add(new XYChart.Data(Integer.toString(serial.get_x()), serial.get_y()));
    }
    
    @FXML
    void button_pressed_get_max(ActionEvent event) {
        
    }

    @FXML
    void button_pressed_get_min(ActionEvent event) {

    }
    
    @FXML
    void button_pressed_sweep(ActionEvent event) throws InterruptedException {
        for(int i=5;i<95;i++){
            send[0] = 2;
            send[1] = (char) i;
            serial.send_int_array(send);
            Thread.sleep(100);
            series.getData().add(new XYChart.Data(Integer.toString(serial.get_x()), serial.get_y()));
        }
        for(int i=95;i>5;i--){
            send[0] = 2;
            send[1] = (char) i;
            serial.send_int_array(send);
            Thread.sleep(100);
            series.getData().add(new XYChart.Data(Integer.toString(serial.get_x()), serial.get_y()));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //x_as = new NumberAxis(0, 100, 1); 
        //y_as = new NumberAxis(0, 1024, 1); 
        //position_chart = new LineChart(xAxis,yAxis);
        position_chart.getData().add(series);
      
    }
    
}
