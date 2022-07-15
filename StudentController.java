package com.example.quiz_management;

public class StudentController {
    DataBase obj = new DataBase();
    public boolean checkLogin(String username,String password) {
        return obj.StudentLogin(username,password);
    }
    public String setUserName(String username,String password){
        return obj.StudentuserName(username, password);
    }
    public void addStudent(String Name,String RollNo,String Username,String Password,String Gender,String Contact){
        obj.addStudent(Name,RollNo,Username,Password,Gender,Contact);
    }
    public boolean SearchStudentID(String ID){
        return obj.searchStudentID(ID);
    }
    public void AssignSection(String RollNo,String Section){
        obj.AssignSection(RollNo,Section);
    }
    public boolean SearchStudentIDCheck(String ID){
        return obj.searchStudentIDCheck(ID);
    }
    public String[] details(String Username){
        return obj.details(Username);
    }
    public String section_student(String Username){ return obj.section_student(Username);}
    public String[] courses_studying(String Username){ return obj.courses_studying(Username);}
    public String[] courses_studying_name(String Username){ return obj.courses_studying_name(Username);}
    public String displayName(String ID){
        return obj.StudentdisplayName(ID);
    }
    public void sendQuery(String Q_ID_query,String query_detail,String S_ID){
        obj.sendQuery(Q_ID_query,query_detail,S_ID);
    }


}