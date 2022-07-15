package com.example.quiz_management;



public class AdminController {
    DataBase obj = new DataBase();
    public boolean checkLogin(String username,String password) {
        return obj.AdminLogin(username,password);
    }
    public String setUserName(String username,String password){
        return obj.AdminuserName(username, password);
    }
    public void AddCourse(String course){
        obj.addCourse(course);
    }
    public String[] showCourse(){
        return obj.showCourse();
    }
    public String[] showCourseID(){
        return obj.showCourseID();
    }
    public String[] showSections(){
        return obj.showSections();
    }
    public String[] showSectionsWithCourse(String Section){
        return obj.showSectionsWithCourse(Section);
    }
    public void assignCourseToSection(String course, String section){
        obj.assignCourseToSection(course,section);
    }
    public String[] ShowCourse(){
        return obj.ShowCourse();
    }
}