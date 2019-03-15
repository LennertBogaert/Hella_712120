package javafxapplication8;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Lenne
 */
public class Serial {

    SerialPort serial = null;
    private int x;
    private int y;
    private int max;
    private int min;
        
    Serial(String port){
        //connect(port);
    }
    
    public int get_x(){
        return x;
    }
    
    public int get_y(){
        return y;
    }
    
    public int get_max(){
        return max;
    }
    
    public int get_min(){
        return min;
    }
    
    public void send(int send){
        try {
            if(serial != null){
                serial.writeInt(send);
            }else{
                    System.out.println("Port not connected!");
                }
        } catch (SerialPortException ex) {
            Logger.getLogger(Hella_712120.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void send_int_array(int[] array){
        try {
            if(serial != null){
                serial.writeIntArray(array);
            }else{
                    System.out.println("Port not connected!");
                }
        } catch (SerialPortException ex) {
            Logger.getLogger(Hella_712120.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void connect(String port){
        
        System.out.println("connect");
        
        //boolean success = false;
        serial = new SerialPort(port);
        
        try {
            serial.openPort();
            serial.setParams(
                    SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serial.setEventsMask(MASK_RXCHAR);
            serial.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        
                        //byte[] b = serialPort.readBytes();
                        /*int value = b[0] & 0xff;    //convert to int
                        String st = String.valueOf(value);*/
                        
                        String a = serial.readString(15);
                            if(a.startsWith("$")){
                                String[] data = a.split(",",3);
                                data[0] = data[0].substring(1);
                                x = Integer.parseInt(data[0]);
                                y = Integer.parseInt(data[1]);
                                System.out.println(data[0] + " " + data[1]);
                            }
                        //Update label in ui thread
                        Platform.runLater(() -> {
                            //labelValue.setText(st);
                            //shiftSeriesData((float)value * 5/255); //in 5V scale
                            //update(x,y);
                            
                        });
                        
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Hella_712120.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                    
                }
            });

            //success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(Hella_712120.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }

        //return success;
    }
    
    public void disconnect(){
        
        System.out.println("disconnect");
        if(serial != null){
            try {
                serial.removeEventListener();
                
                if(serial.isOpened()){
                    serial.closePort();
                }
                
                serial = null;
            } catch (SerialPortException ex) {
                Logger.getLogger(Hella_712120.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
