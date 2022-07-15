package com.example.quiz_management;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {
    AdminController obj = new AdminController();
    StudentController obj2 = new StudentController();
    TeacherController obj3 = new TeacherController();
    QuizManagementApplication obj1 = new QuizManagementApplication();
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private ChoiceBox<String> type;
    private final String[] typeList = {"Admin","Teacher","Student"};
    @FXML
    public void onLogin(ActionEvent event) throws Exception {
        boolean LoginVerification;
        if(Objects.equals(type.getValue(), "Admin")){
           LoginVerification = obj.checkLogin(username.getText(),password.getText());
           if(LoginVerification){
               obj1.SwitchScene("Admin.fxml",event,obj.setUserName(username.getText(),password.getText()));
           }else{
               Dialog<String> dialog = new Dialog<>();
               dialog.setTitle("Error");
               ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
               dialog.setContentText("Invalid Username or Password.");
               dialog.getDialogPane().getButtonTypes().add(type);
               Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
               stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
               dialog.showAndWait();
           }

        }else if(Objects.equals(type.getValue(), "Teacher")){
            LoginVerification = obj3.checkLogin(username.getText(),password.getText());
            if(LoginVerification){
                obj1.SwitchScene("Teacher.fxml",event,obj3.setUserName(username.getText(),password.getText()));
            }else{
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Error");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Invalid Username or Password.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }
        }else if(Objects.equals(type.getValue(), "Student")){
            LoginVerification = obj2.checkLogin(username.getText(),password.getText());
            if(LoginVerification){
                obj1.SwitchScene("Student.fxml",event,obj2.setUserName(username.getText(),password.getText()));
            }else{
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Error");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Invalid Username or Password.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(typeList);
        type.setValue("Admin");
    }

}