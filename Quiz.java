package com.example.quiz_management;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.time.LocalDateTime;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class Quiz extends TimerTask {
    private Timer timer;
    private String S_ID;
    private String Q_ID;
    private Integer TotalQuestions;
    private Integer Question_num;
    private Stage stage;
    QuizController obj = new QuizController();
    public void insertQuiz(String ID,LocalDateTime quizDateTime,String Duration,String section,String course){
        obj.insertQuiz(ID,quizDateTime,Duration,section,course);
    }
    public void addQuestion(String ID,String noOfQuestions,String question,String op1,String op2,String op3,String op4,String ans,String marks){
        obj.addQuestion(ID,noOfQuestions,question,op1,op2,op3,op4,ans,marks);
    }
    public void set_timer_IDS(Timer t,String s,String q){
        timer = t;
        S_ID = s;
        Q_ID = q;
        Question_num = 1;
        Question_ID.setText(obj.getQuestion(Q_ID,Question_num));
        o1.setText(obj.getop("1",Q_ID,Question_num));
        o2.setText(obj.getop("2",Q_ID,Question_num));
        o3.setText(obj.getop("3",Q_ID,Question_num));
        o4.setText(obj.getop("4",Q_ID,Question_num));
        TotalQuestions = obj.getTotalQuestions(Q_ID);
    }
    @FXML
    private Label Question_ID;
    @FXML
    private Button nextbutton;
    @FXML
    private RadioButton o1,o2,o3,o4;
    @FXML
    public void onSubmit(ActionEvent event){
        while(Question_num < TotalQuestions) {
            onNext();
        }
        if (o1.isSelected()) {
            obj.insertAnswer(o1.getText(), Q_ID, S_ID, Question_num);
        } else if (o2.isSelected()) {
            obj.insertAnswer(o2.getText(), Q_ID, S_ID, Question_num);
        } else if (o3.isSelected()) {
            obj.insertAnswer(o3.getText(), Q_ID, S_ID, Question_num);
        } else if (o4.isSelected()) {
            obj.insertAnswer(o4.getText(), Q_ID, S_ID, Question_num);
        } else {
            obj.insertAnswer(".", Q_ID, S_ID, Question_num);
        }
        o1.setSelected(false);
        o2.setSelected(false);
        o3.setSelected(false);
        o4.setSelected(false);
        timer.cancel();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void exit(Stage stage,Timer timer){
        while(Question_num < TotalQuestions) {
            onNext();
        }
        if (o1.isSelected()) {
            obj.insertAnswer(o1.getText(), Q_ID, S_ID, Question_num);
        } else if (o2.isSelected()) {
            obj.insertAnswer(o2.getText(), Q_ID, S_ID, Question_num);
        } else if (o3.isSelected()) {
            obj.insertAnswer(o3.getText(), Q_ID, S_ID, Question_num);
        } else if (o4.isSelected()) {
            obj.insertAnswer(o4.getText(), Q_ID, S_ID, Question_num);
        } else {
            obj.insertAnswer(".", Q_ID, S_ID, Question_num);
        }
        o1.setSelected(false);
        o2.setSelected(false);
        o3.setSelected(false);
        o4.setSelected(false);
        timer.cancel();
        stage.close();
    }
    public String[] getQID(String ID){
        return obj.getQID(ID);
    }
    public String[] getQIDs(String ID){
        return obj.getQIDs(ID);
    }
    public String[] getSQIDs(String ID){
        return obj.getSQIDs(ID);
    }
    public String getQueryText(String ID,String S_name){
        return obj.getQueryText(ID,S_name);
    }
    public void submitResponse(String ID,String S_name,String response_text){
        obj.submitResponse(ID,S_name,response_text);
    }
    public String getResponseText(String ID,String SID){
        return obj.getResponseText(ID,SID);
    }
    public String[] getQIDR(String ID){
       return obj.getQIDR(ID);
    }
    public String[] getResultQID(String ID){
        return obj.getResultQID(ID);
    }
    public boolean getAttemptID(String ID,String IDQ){
        return obj.getAttemptID(ID,IDQ);
    }
    public String[] getStudents(String IDQ){
        return obj.getStudents(IDQ);
    }
    @FXML
    public void onNext(){
        if(o1.isSelected()){
            obj.insertAnswer(o1.getText(),Q_ID,S_ID,Question_num);
        }else if(o2.isSelected()){
            obj.insertAnswer(o2.getText(),Q_ID,S_ID,Question_num);
        }else if(o3.isSelected()){
            obj.insertAnswer(o3.getText(),Q_ID,S_ID,Question_num);
        }else if(o4.isSelected()){
            obj.insertAnswer(o4.getText(),Q_ID,S_ID,Question_num);
        }else {
            obj.insertAnswer(".",Q_ID,S_ID,Question_num);
        }
        o1.setSelected(false);
        o2.setSelected(false);
        o3.setSelected(false);
        o4.setSelected(false);
        Question_num++;
        Question_ID.setText(obj.getQuestion(Q_ID,Question_num));
        o1.setText(obj.getop("1",Q_ID,Question_num));
        o2.setText(obj.getop("2",Q_ID,Question_num));
        o3.setText(obj.getop("3",Q_ID,Question_num));
        o4.setText(obj.getop("4",Q_ID,Question_num));
        if(Objects.equals(TotalQuestions, Question_num)){
            nextbutton.setVisible(false);
        }
    }
    public String[] getResult(String Q_ID,String ID){
        return obj.getResult(Q_ID,ID);
    }

    public void setStage(Stage s){
        stage = s;
    }
    @Override
    public void run() {
        Platform.runLater(() -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Times UP");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Your Times Up...Submitted The Quiz.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage1.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
            exit(stage,timer);
        });
    }
}
