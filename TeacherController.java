package com.example.quiz_management;

public class TeacherController {
    DataBase obj = new DataBase();
    public boolean checkLogin(String username,String password) {
        return obj.TeacherLogin(username,password);
    }
    public String setUserName(String username,String password){
        return obj.TeacheruserName(username, password);
    }
    public String displayName(String ID){
        return obj.TeacherdisplayName(ID);
    }
    public void addTeacher(String Name,String ID,String Username,String Password,String Gender,String Contact){
        obj.addTeacher(Name,ID,Username,Password,Gender,Contact);
    }
    public String AssignCourse(String ID, String Course, String Section){
        return obj.AssignCourse(ID,Course,Section);
    }
    public String[] getCourses(String name){
        return obj.getCourses(name);
    }
    public String[] getSections(String Course,String ID){
        return obj.getSections(Course,ID);
    }
    public String[] details_teacher(String PID){
        return obj.details(PID);
    }
    public String[] courses_taught_name(String PID){
        return obj.courses_taught_name(PID);}
    public String[] courses_taught_sectionname(String PID)
    {
        return obj.courses_taught_sectionname(PID);
    }
}
