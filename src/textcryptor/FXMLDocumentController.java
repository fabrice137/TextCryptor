package textcryptor;

import fabFileLib.FabCipher;
import java.net.URL; 
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    
    @FXML private TextArea area;
    @FXML private TextField keyField;
    @FXML private TextField textField;
    
    String textIn;
    String keyString;
    FabCipher ciph = new FabCipher();
    
    @FXML
    private void encrypt(ActionEvent event) {
        
        textIn = textField.getText();
        keyString = keyField.getText();
        area.setText(ciph.cipherString(textIn, keyString));
     }
    
    @FXML
    private void decrypt(ActionEvent event) {
        
        textIn = textField.getText();
        keyString = keyField.getText();
        area.setText(ciph.decipherString(textIn, keyString));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        area.setWrapText(true); 
    }    
    
}
