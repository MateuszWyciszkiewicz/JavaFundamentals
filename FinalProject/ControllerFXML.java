import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerFXML implements Initializable {
    
       
    @FXML 
    private TextArea QueryTextArea;
    
    static TextArea staticTxtArea;
    
    @FXML
    private TextField QueryField;
         
    @FXML
    private void executeQuery(ActionEvent event) { 
        
        // these will be redirected to textArea on GUI
        String query = QueryField.getText();
        Parser.executeQuery(query);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      staticTxtArea = QueryTextArea;
  }
      
}