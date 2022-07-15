package com.example.quiz_management;

import java.time.LocalDateTime;

public class QuizController {
    DataBase obj = new DataBase();
    public void insertQuiz(String ID,LocalDateTime quizDateTime, String Duration, String section, String course){
        obj.insertQuiz(ID,quizDateTime,Duration,section,course);
    }
    public void addQuestion(String ID,String noOfQuestions,String question,String op1,String op2,String op3,String op4,String ans,String marks){
        obj.addQuestion(ID,noOfQuestions,question,op1,op2,op3,op4,ans,marks);
    }
    public String[] getQID(String S_ID){
        return obj.getQID(S_ID);
    }
    public String getQuestion(String Q_ID,Integer Question_num){
        return obj.getQuestion(Q_ID,Question_num.toString());
    }
    public String getop(String num,String Q_ID,Integer Question_num){
        return obj.getop(num,Q_ID,Question_num.toString());
    }
    public void insertAnswer(String ans,String Q_ID,String S_ID,Integer Question_num){
        obj.insertAnswer(ans,Q_ID,S_ID,Question_num.toString());
    }
    public Integer getTotalQuestions(String Q_ID){
        return obj.getTotalQuestions(Q_ID);
    }
    public String[] getQIDs(String ID){
        return obj.getQIDs(ID);
    }
    public String[] getSQIDs(String Q_ID){
        return obj.getSQIDs(Q_ID);
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
    public String[] getResult(String Q_ID_Result,String ID){
        return obj.getResult(Q_ID_Result,ID);
    }
    public boolean getAttemptID(String ID,String IDQ){
        return obj.getAttemptID(ID,IDQ);
    }
    public String[] getStudents(String IDQ){
        return obj.getStudents(IDQ);
    }
}
