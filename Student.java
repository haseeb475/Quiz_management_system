package com.example.quiz_management;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;

public class Student implements Initializable {
    StudentController obj = new StudentController();
    Courses course = new Courses();
    Quiz quiz_connection = new Quiz();
    private String S_ID;
    private String Q_ID;
    private String Q_ID_query;
    private String Q_ID_Response;
    private String Q_ID_Result;

    @FXML
    private ListView<String> quiz_list;

    @FXML
    private TableColumn<Courses, String> CourseID;

    @FXML
    private TableColumn<Courses, String> CourseName;

    @FXML
    private Label CorrectAnswers;
    @FXML
    private Label ObtainedMarks;
    @FXML
    private Label Total_Questions;
    @FXML
    private ListView<String> quiz_result_listview;

    @FXML
    private TableView<Courses> listofstudentenrolled_courses;
    @FXML
    private TextArea query_detail;
    @FXML
    private ListView<String> quiz_list_query;
    @FXML
    private AnchorPane Response;
    @FXML
    private ListView<String> response_list;
    @FXML
    private TextArea response_text;
    @FXML
    private Label contact_infostudent;
    @FXML
    private Label RollNoLabel;

    @FXML
    private Label section_studentenrolled;

    @FXML
    private Label student_gender_label;

    @FXML
    private Label upper_label;

    @FXML
    private Label Username;

    @FXML
    private AnchorPane Slider;

    @FXML
    private AnchorPane Query;

    @FXML
    private AnchorPane Result;

    @FXML
    private AnchorPane home;

    @FXML
    private AnchorPane quiz;
    @FXML
    private Button condition;

    @FXML
    public void onQuiz() {
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(false);
        Response.setVisible(false);
        quiz.setVisible(true);
        Username.setVisible(true);
        condition.setVisible(false);
        upper_label.setText("QUIZ");
        ObservableList<String> list = FXCollections.observableArrayList(quiz_connection.getQID(S_ID));
        quiz_list.setItems(list);
        quiz_list_query.setItems(list);
        quiz_list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Q_ID = quiz_list.getSelectionModel().getSelectedItem();
            condition.setVisible(!quiz_connection.getAttemptID(S_ID, Q_ID) && !Objects.equals(Q_ID, ""));
        });
        quiz_list_query.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> Q_ID_query = quiz_list_query.getSelectionModel().getSelectedItem());



    }
    @FXML
    public void onStartQuiz() throws IOException {
        Timer timer = new Timer();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizManagementApplication.class.getResource("Quiz.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("/icon.png");
        Quiz KeyCheck = fxmlLoader.getController();
        KeyCheck.set_timer_IDS(timer,S_ID,Q_ID);
        KeyCheck.setStage(stage);
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.WINDOWS){
                KeyCheck.exit(stage,timer);
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Illegal Key Pressed");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Quiz Was Submitted.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage1.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }else if (keyEvent.getCode() == KeyCode.ALT){
                KeyCheck.exit(stage,timer);
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Illegal Key Pressed");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Quiz Was Submitted.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage1.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }else if(keyEvent.getCode() == KeyCode.ESCAPE){
                KeyCheck.exit(stage,timer);
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Illegal Key Pressed");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Quiz Was Submitted.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage1.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }
        });

        stage.getIcons().add(icon);
        stage.setTitle("Quiz Management System");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setScene(scene);
        timer.schedule(KeyCheck, 8000);
        stage.show();
    }


    public String[] details(String Username) {
        return obj.details(Username);
    }

    public String section_student(String Username)
    {
        return obj.section_student(Username);
    }

    @FXML
    public void onSubmitQuery(){
        obj.sendQuery(Q_ID_query,query_detail.getText(),S_ID);
        query_detail.setText("");
        quiz_list_query.getSelectionModel().clearSelection();
    }

    @FXML
    public void onHomeClick(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(true);
        quiz.setVisible(false);
        Response.setVisible(false);
        upper_label.setText("HOME");
    }
    @FXML
    public void onQuery(){
        Query.setVisible(true);
        Result.setVisible(false);
        home.setVisible(false);
        quiz.setVisible(false);
        Response.setVisible(false);
        Slider.setVisible(true);
        upper_label.setText("QUERY");
        query_detail.setText("");
        ObservableList<String> list = FXCollections.observableArrayList(quiz_connection.getQID(S_ID));
        quiz_list_query.setItems(list);

    }
    @FXML
    public void onlogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizManagementApplication.class.getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image("/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Quiz Management System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onResponse(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(false);
        quiz.setVisible(false);
        Response.setVisible(true);
        upper_label.setText("RESPONSE");
        ObservableList<String> list = FXCollections.observableArrayList(quiz_connection.getQIDR(S_ID));
        response_list.setItems(list);
        response_list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Q_ID_Response = response_list.getSelectionModel().getSelectedItem();
            if (Q_ID_Response != null) {
                response_text.setText(quiz_connection.getResponseText(Q_ID_Response, S_ID));
            } else {
                response_text.setText("");
            }
        });

    }@FXML
    public void onResult(){
        Query.setVisible(false);
        Result.setVisible(true);
        home.setVisible(false);
        quiz.setVisible(false);
        Response.setVisible(false);
        upper_label.setText("RESULT");
        ObservableList<String> list = FXCollections.observableArrayList(quiz_connection.getResultQID(S_ID));
        quiz_result_listview.setItems(list);
        quiz_result_listview.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Q_ID_Result = quiz_result_listview.getSelectionModel().getSelectedItem();
            if (Q_ID_Result != null) {
                Total_Questions.setText(quiz_connection.getResult(Q_ID_Result, S_ID)[0]);
                CorrectAnswers.setText(quiz_connection.getResult(Q_ID_Result, S_ID)[1]);
                ObtainedMarks.setText(quiz_connection.getResult(Q_ID_Result, S_ID)[2]);
            } else {
                Total_Questions.setText("");
                CorrectAnswers.setText("");
                ObtainedMarks.setText("");
            }
        });


    }



    public void setUser(String user) {
        S_ID = user;
        Username.setText(obj.displayName(user));
        CourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        CourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        listofstudentenrolled_courses.setItems(course.studinitialization(user));
        student_gender_label.setText(details(user)[1]);
        section_studentenrolled.setText(section_student((user)));
        contact_infostudent.setText(details(user)[2]);
        RollNoLabel.setText(details(user)[0]);
        ObservableList<String> list = FXCollections.observableArrayList(quiz_connection.getQID(S_ID));
        quiz_list.setItems(list);
        quiz_list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> Q_ID = quiz_list.getSelectionModel().getSelectedItem());
    }

    public void addStudent(String Name,String RollNo,String Username,String Password,String Gender,String Contact){
        obj.addStudent(Name,RollNo,Username,Password,Gender,Contact);
    }
    public boolean searchStudentID(String ID){
        return obj.SearchStudentID(ID);
    }
    public boolean searchStudentIDCheck(String ID){
        return obj.SearchStudentIDCheck(ID);
    }
    public void AssignSection(String RollNo,String Section){
        obj.AssignSection(RollNo,Section);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(true);
        quiz.setVisible(false);
        Response.setVisible(false);
        Username.setVisible(true);
        upper_label.setText("HOME");
        Slider.setVisible(true);
    }
}