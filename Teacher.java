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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class Teacher implements Initializable {
    TeacherController obj = new TeacherController();
    Quiz quiz = new Quiz();
    private String ID;
    private String selectedSection;
    private String QuizIDTemp;
    private Integer i;
    private String selectedCourse;
    private String Q_ID;
    private String S_name;


    ///////////////////////
    @FXML
    private Label section_taught;

    @FXML
    private Label courses_taught;

    @FXML
    private Label teacher_contact_homepage;

    @FXML
    private Label teacher_gender_homepage;

    @FXML
    private Label teacher_id_homepage;
    ///////////////////////

    @FXML
    private ListView<String> sectionListView;

    @FXML
    private Label CorrectAnswers;

    @FXML
    private Label ObtainedMarks;

    @FXML
    private Label Total_Questions;

    @FXML
    private ListView<String> quiz_result_listview;

    @FXML
    private ListView<String> quiz_result_listviewStudent;


    @FXML
    private TextArea query_text;

    @FXML
    private TextArea response_text;

    @FXML
    private ListView<String> quiz_list;

    @FXML
    private ListView<String> quiz_student_list;

    @FXML
    private TextField op1;

    @FXML
    private TextField op2;

    @FXML
    private TextField op3;

    @FXML
    private TextField op4;

    @FXML
    private TextArea questionArea;

    @FXML
    private ChoiceBox<String> ansChoice;

    @FXML
    private TextField marksofquestion;

    @FXML
    private TextField QuizNoID;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private ChoiceBox<String> Course;

    @FXML
    private TextField Quiz_Duration;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private DatePicker datePicker;

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
    private AnchorPane quiz1;

    @FXML
    private AnchorPane quiz2;


    public void addTeacher(String Name,String ID,String Username,String Password,String Gender,String Contact){
        obj.addTeacher(Name,ID,Username,Password,Gender,Contact);
    }
    public String AssignCourse(String ID, String Course, String Section){
        return obj.AssignCourse(ID,Course,Section);
    }
    public void setUser(String PID) {
        ID = PID;
        Username.setText(obj.displayName(PID));
        String[] courses= obj.getCourses(ID);
        Course.getItems().addAll(courses);
        teacher_gender_homepage.setText(details_teacher(PID)[1]);
        teacher_contact_homepage.setText(details_teacher(PID)[2]);
        teacher_id_homepage.setText(details_teacher(PID)[0]);
        courses_taught.setText(courses_taught_name(PID)[0]);
        section_taught.setText(courses_taught_sectionname(PID)[0]);
    }
    @FXML
    public void onCreateQuiz(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(true);
        quiz1.setVisible(true);
        quiz2.setVisible(false);
        i++;
        if(Objects.equals(ansChoice.getValue(), "Option 1")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op1.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 2")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op2.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 3")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op3.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 4")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op4.getText(),marksofquestion.getText());
        }
        questionArea.setText("");
        op1.setText("");
        op2.setText("");
        op3.setText("");
        op4.setText("");
        ansChoice.setValue("");
        marksofquestion.setText("");
        datePicker.setValue(null);
        SpinnerValueFactory<Integer> hour = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0);
        SpinnerValueFactory<Integer> minute = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
        hourSpinner.setValueFactory(hour);
        minuteSpinner.setValueFactory(minute);
        Quiz_Duration.setText("");
        QuizNoID.setText("");
        selectedCourse = "";
        selectedSection = "";
        Course.setValue("");



    }
    @FXML
    public void onAddQuestion(){
        i++;
        if(Objects.equals(ansChoice.getValue(), "Option 1")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op1.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 2")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op2.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 3")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op3.getText(),marksofquestion.getText());
        }
        else if(Objects.equals(ansChoice.getValue(), "Option 4")){
            quiz.addQuestion(QuizIDTemp,i.toString(),questionArea.getText(),op1.getText(),op2.getText(),op3.getText(),op4.getText(),op4.getText(),marksofquestion.getText());
        }


        questionArea.setText("");
        op1.setText("");
        op2.setText("");
        op3.setText("");
        op4.setText("");
        ansChoice.setValue("");
        marksofquestion.setText("");
    }

    @FXML
    public void onNext(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(false);
        quiz1.setVisible(false);
        quiz2.setVisible(true);
        selectedCourse = Course.getValue();
        LocalDate date = datePicker.getValue();
        String Date_Time = date.toString()+" ";
        String hours;
        if(hourSpinner.getValue()<10){
            hours = "0"+hourSpinner.getValue().toString();
        }else{
            hours = hourSpinner.getValue().toString();
        }
        if(minuteSpinner.getValue()<10){
            hours = hours+":"+"0";
        }else{
            hours = hours+":";
        }
        hours = hours + minuteSpinner.getValue().toString();
        Date_Time = Date_Time + hours + ":00" ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(Date_Time, formatter);
        QuizIDTemp = QuizNoID.getText()+selectedCourse+selectedSection;
        quiz.insertQuiz(QuizIDTemp,dateTime,Quiz_Duration.getText(),selectedSection,selectedCourse);
        i=0;

    }
    @FXML
    public void onQuery(){
        Query.setVisible(true);
        Result.setVisible(false);
        home.setVisible(false);
        quiz1.setVisible(false);
        quiz2.setVisible(false);
        upper_label.setText("QUERY");
        ObservableList<String> listofquizes = FXCollections.observableArrayList(quiz.getQIDs(ID));
        quiz_list.setItems(listofquizes);
        quiz_list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Q_ID = quiz_list.getSelectionModel().getSelectedItem();
            if(Q_ID!=null){
                ObservableList<String> listofstudents = FXCollections.observableArrayList(quiz.getSQIDs(Q_ID));
                quiz_student_list.setItems(listofstudents);
            }else{
                quiz_student_list.setItems(null);
            }
        });
        quiz_student_list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            S_name = quiz_student_list.getSelectionModel().getSelectedItem();
            if(S_name!=null){
                query_text.setText(quiz.getQueryText(Q_ID,S_name));
            }else{
                query_text.setText("");
            }
        });
    }
    @FXML
    void onSubmitResponse() {
        quiz.submitResponse(Q_ID,S_name,response_text.getText());
        query_text.setText("");
        response_text.setText("");
        quiz_student_list.getSelectionModel().clearSelection();
        quiz_list.getSelectionModel().clearSelection();
    }

    @FXML
    public void onQuiz(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(false);
        quiz1.setVisible(true);
        quiz2.setVisible(false);
        upper_label.setText("QUIZ");
            if(!Objects.equals(Course.getValue(), "")) {
                String[] sectionsList = obj.getSections(Course.getValue(), ID);
                ObservableList<String> list = FXCollections.observableArrayList(sectionsList);
                sectionListView.setItems(list);
            }else{
                sectionListView.setItems(null);
            }
        sectionListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> selectedSection = sectionListView.getSelectionModel().getSelectedItem());

    }
    @FXML
    public void onResult(){
        Query.setVisible(false);
        Result.setVisible(true);
        home.setVisible(false);
        quiz1.setVisible(false);
        quiz2.setVisible(false);
        upper_label.setText("RESULT");
        ObservableList<String> list = FXCollections.observableArrayList(quiz.getQIDs(ID));
        quiz_result_listview.setItems(list);
        quiz_result_listview.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Q_ID = quiz_result_listview.getSelectionModel().getSelectedItem();
            if(Q_ID!=null){
                ObservableList<String> listofstudents = FXCollections.observableArrayList(quiz.getStudents(Q_ID));
                quiz_result_listviewStudent.setItems(listofstudents);
            }else{
                quiz_result_listviewStudent.setItems(null);
            }
        });
        quiz_result_listviewStudent.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            S_name = quiz_result_listview.getSelectionModel().getSelectedItem();
            if(!Objects.equals(S_name, "")){
                Total_Questions.setText(quiz.getResult(Q_ID, S_name)[0]);
                CorrectAnswers.setText(quiz.getResult(Q_ID, S_name)[1]);
                ObtainedMarks.setText(quiz.getResult(Q_ID, S_name)[2]);
            }else{
                Total_Questions.setText("");
                CorrectAnswers.setText("");
                ObtainedMarks.setText("");
            }
        });

        Total_Questions.setText("");
        CorrectAnswers.setText("");
        ObtainedMarks.setText("");

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
    public String[] details_teacher(String PID){ return obj.details_teacher(PID);}
    public String[] courses_taught_name(String PID){ return obj.courses_taught_name(PID);}
    public String[] courses_taught_sectionname(String PID){ return obj.courses_taught_sectionname(PID);}
    @FXML
    public void onHome(){
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(true);
        quiz1.setVisible(false);
        quiz2.setVisible(false);
        upper_label.setText("HOME");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query.setVisible(false);
        Result.setVisible(false);
        home.setVisible(true);
        quiz1.setVisible(false);
        quiz2.setVisible(false);
        Username.setVisible(true);
        Slider.setVisible(true);
        upper_label.setVisible(true);
        upper_label.setText("HOME");
        SpinnerValueFactory<Integer> hour = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0);
        SpinnerValueFactory<Integer> minute = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
        hourSpinner.setValueFactory(hour);
        minuteSpinner.setValueFactory(minute);
        Course.setValue("");
        ansChoice.getItems().addAll("Option 1","Option 2","Option 3","Option 4");
        ansChoice.setValue("");


    }
}
