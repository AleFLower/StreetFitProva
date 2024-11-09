/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Streetfit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * FXML Controller class
 *
 * @author aless
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private Button signin_close;

    @FXML
    private Hyperlink signin_createAccount;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_login;

    @FXML
    private Button signin_minimize;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private Hyperlink signup_alreadyHaveAccount;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_close;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_minimize;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;
    
    //tools for database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet rs;
    
    public void signin(){
        String sql = "SELECT * FROM trainer WHERE username = ? and password = ?";
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signin_username.getText());  //inserisco nella query cio che l'utente sta inserendo da tastiera! cioè signin_username da scenebuilder
            prepare.setString(2, signin_password.getText()); //stessa cosa per la password, sto collegando cio che inserisce l'utente con cio che va nel DB
            
            rs = prepare.executeQuery();
            
            Alert alert;
            //check if the username or password is empty, by using Alert class
            if(signin_username.getText().isEmpty() || signin_password.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setContentText("Please, fill all blank fields");
                alert.showAndWait();
            }
            else{
                if(rs.next()){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login");
                    alert.showAndWait();  //showAndWait() mostra la finestra e sospende l'esecuzione finché l'utente non la chiude.
                }
                else{
                    alert = new Alert(AlertType.ERROR);
                     alert.setTitle("Error message");
                    alert.setHeaderText(null);  // Imposta un titolo secondario o intestazione. Può essere null per nasconderlo.
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();  //showAndWait() mostra la finestra e sospende l'esecuzione finché l'utente non la chiude.
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void switchForm(ActionEvent event){   //per switchare da signin a signup: se clicco su create a new account, switcha finestra. Viceversa se already have an account
        if(event.getSource() == signin_createAccount){
            signin_form.setVisible(false);
            signup_form.setVisible(true);
        }
        else if(event.getSource() == signup_alreadyHaveAccount){
            signin_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }
    
    public void signin_close() {   //per chiudere sulla x del programma, devo ovviamente andare anche sul file xml e mettere sulla X on action: signin_close
        System.exit(0);
    }
    
    public void signin_minimize(){  //stessa cosa di prima, per cliccare sul - della finestra, anche in questo caso vado sul scenebuilder e metto on action
        Stage stage = (Stage) signin_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void signup_minimize(){
       Stage stage = (Stage) signup_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void signup_close() {   //per chiudere sulla x del programma, devo ovviamente andare anche sul file xml e mettere sulla X on action: signin_close
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
