package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class fxmlDocumentController implements Initializable
{
	

	
    @FXML
    private Button close;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
  private double x=0;
  private double y=0;
  
    public void loginAdmin()
    {
    	connect = database.ConnectDb();
    	
 String sql = "SELECT * FROM admin WHERE username = ? and password = ?";// admin is our table name
 
 try {
    Alert alert;	
	 prepare = connect.prepareStatement(sql);
	 prepare.setString(1, username.getText());
	 prepare.setString(2, password.getText());
	 
	 result = prepare.executeQuery();
	 if(username.getText().isEmpty() || password.getText().isEmpty())
	 {
		 alert= new Alert(AlertType.ERROR);
		 alert.setTitle("Error Messege");
alert.setHeaderText(null);
alert.setContentText("Please fill the blanks fields");
alert.showAndWait();
	 }
	 else
	 {
		  if(result.next())
		  {// if correct username and password
			
getData.username=username.getText();			  
			  
alert= new Alert(AlertType.INFORMATION);
alert.setTitle("Information Messege");
		alert.setHeaderText(null);
alert.setContentText("Succesfully Login");
		alert.showAndWait();	
		
// to hide our login form
loginbtn.getScene().getWindow().hide();		
			  
			  
			  // linked our dashboard
			  Parent root =FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			  Stage stage = new Stage();
			  Scene scene = new Scene(root);
			  

root.setOnMousePressed((MouseEvent event) -> {
	x = event.getSceneX();
	y=event.getSceneY();
});	

root.setOnMouseDragged((MouseEvent event) -> {
	stage.setX(event.getSceneX()-x);
	stage.setY(event.getSceneY()-y);
});
			  
stage.initStyle(StageStyle.TRANSPARENT);
			  stage.setScene(scene);
			  stage.show(); 
			  
		  }else
		  {// if wrong username and password
			  alert= new Alert(AlertType.ERROR);
				 alert.setTitle("Error Messege");
		alert.setHeaderText(null);
		alert.setContentText("Wrong username/password");
		alert.showAndWait();

		  }
	 }
	 
    }catch(Exception e) {e.printStackTrace();}
 
 
    }
    
    public void close()
    {
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
