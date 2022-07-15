package com.example.quiz_management;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QuizManagementApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizManagementApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Quiz Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchScene(String fxml, ActionEvent event,String Username) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizManagementApplication.class.getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        if(fxml.equals("Admin.fxml")){
            Admin setting = fxmlLoader.getController();
            setting.setUser(Username);

        }else if(fxml.equals("Teacher.fxml")){
            Teacher setting = fxmlLoader.getController();
            setting.setUser(Username);

        }else{
            Student setting = fxmlLoader.getController();
            setting.setUser(Username);
        }
        stage.setResizable(false);
        Image icon = new Image("/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Quiz Management System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}