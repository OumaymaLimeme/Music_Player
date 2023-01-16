package com.mycompany.musicplayer;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class RegisterController implements Initializable {
     @FXML
    private Button reset_btn;

    @FXML
    private Button submit_btn;

    @FXML
    private CheckBox chkfemale;

    @FXML
    private CheckBox chkmale;

    @FXML
    private TextField txtage;
     @FXML
    private Button back_btn;
    @FXML
    private PasswordField txtcpassword;

    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;
    /**
     * Initializes the controller class.
     * @param event
     */

    @FXML
     public void back_to_login(MouseEvent event) throws IOException{
              back_btn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Scene scene = new Scene(root,1100, 700);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        Image image = new Image("/img/m2.png");
                        stage.getIcons().add(image);
                        stage.setTitle("Login Page");
                        stage.setResizable(true);
                        stage.show();
    
    }
    @FXML
    public void Reset(MouseEvent event){
        txtusername.setText(null);
        txtemail.setText(null);
       txtage.setText(null);
        txtpassword.setText(null);
        txtcpassword.setText(null);
        chkmale.setSelected(false);
        chkfemale.setSelected(false);
    }
    public String cryptage(String password) throws NoSuchAlgorithmException{
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
    private Connection con=null;
    private PreparedStatement pst=null;
    @FXML
   public void Submit(MouseEvent event){
    if(!"".equals(txtusername.getText())&&!"".equals(txtemail.getText())&&!"".equals(txtage.getText())&&!"".equals(txtpassword.getText())&&!"".equals(txtcpassword.getText())&&(chkmale.isSelected()||chkfemale.isSelected())){
                if(txtpassword.getText().equals(txtcpassword.getText())){
             try{
           
                
                String query ="INSERT INTO `utilisateur`(`username`, `email`, `age`, `gender`, `password`) VALUES (?,?,?,?,?)";
                con =DriverManager.getConnection("jdbc:mysql://localhost/musicplayer","root","");
                 pst=con.prepareStatement(query);
                 pst.setString(1,txtusername.getText());
                 pst.setString(2,txtemail.getText());
                 if(chkmale.isSelected()){ pst.setString(4,chkmale.getText()); }
                 if(chkfemale.isSelected()){pst.setString(4,chkfemale.getText());}
                 pst.setString(3,txtage.getText());
                 String password=txtpassword.getText();
                 String hpassword=cryptage(password);
                 pst.setString(5,hpassword);
                 pst.executeUpdate();
                 JOptionPane.showMessageDialog(null,"REGISTER SUCCESSFULLY");
                 txtusername.setText(null);
                 txtemail.setText(null);
                 txtage.setText(null);
                 txtpassword.setText(null);
                 txtcpassword.setText(null);
                 chkmale.setSelected(false);
                 chkfemale.setSelected(false);
        
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex); };
             }else{  JOptionPane.showMessageDialog(null,"Please check the password!");}
        }else{
             JOptionPane.showMessageDialog(null,"Please Fill All The Fields!");
            }
    }

@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
      @FXML
    private void Register(javafx.event.ActionEvent event) {
    }
}