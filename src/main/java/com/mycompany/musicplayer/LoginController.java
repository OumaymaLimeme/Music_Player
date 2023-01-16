package com.mycompany.musicplayer;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
        /**
 * FXML Controller class
 *
 * @author OrdiOne
 */

public class LoginController implements Initializable {

    @FXML
    private Label exit_btn;
   
   
      @FXML
    private Button login_btn;

    @FXML
    private PasswordField password;

    @FXML
    private Button register_btn;

    @FXML
    private TextField username;
    

    @FXML
    void HandleClose() {
        System.exit(0);

    }
    //database Tools
    private Connection connect=null;
    private PreparedStatement pst=null; 
   // fonction pour crypter le mot de passe pour des raisons de sécurité
    public String cryptage(String password) throws NoSuchAlgorithmException {
                MessageDigest m = MessageDigest.getInstance("MD5");
                 m.reset();
                 m.update(password.getBytes());
                 byte[] digest=m.digest();
                 BigInteger bigint=new BigInteger(1,digest);
                 String hpassword=bigint.toString(16);
                 while(hpassword.length()<32)
                 {hpassword="0"+hpassword;
                 }
                 return hpassword;
   }
    public void Login(MouseEvent event){
     if(!password.getText().trim().isEmpty()&&!username.getText().trim().isEmpty()){

                try {
                    connect =DriverManager.getConnection("jdbc:mysql://localhost/musicplayer","root","");
                    String query ="SELECT * FROM UTILISATEUR WHERE username=? AND password=?";
                    pst=connect.prepareStatement(query);
                    pst.setString(1,username.getText());
                    pst.setString(2,cryptage(password.getText()));
                    ResultSet rs=pst.executeQuery();
                    if (rs.next()){
                        JOptionPane.showMessageDialog(null,"SUCCESSFULLY Logged In , WELCOME "+username.getText());
                        login_btn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                         Image image = new Image("/img/m2.png");
                        stage.getIcons().add(image);
                        stage.setTitle("Main Page");
                        stage.show();
                    }else{
                     JOptionPane.showMessageDialog(null," Password or Username not matched !");
                     password.setText(null);
                     username.setText(null);
                    }
                    connect.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);

                }
        }
                  else{
                          JOptionPane.showMessageDialog(null,"Please fill All the fields !");
                          }
    }
 
    
    public void Register(MouseEvent event) throws IOException{
              register_btn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
                        Scene scene = new Scene(root,1100, 700);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        Image image = new Image("/img/m2.png");
                        stage.getIcons().add(image);
                        stage.setTitle("Register Page");
                        stage.setResizable(true);
                        stage.show();
    
    }
    
    public void TextField(MouseEvent event){
    if(event.getSource()==username){
     username.setStyle("-fx-background-color:#fff" +"-fx-background-radius:10px"+"-fx-barder-width:1px" );
 
    }else if(event.getSource()==password){
        password.setStyle("-fx-background-color:#fff" +"-fx-barder-width:1px" );
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           username.setStyle("-fx-background-color:#fff" +"-fx-background-radius:10px"+"-fx-barder-width:1px" );

    }    

    
    
}