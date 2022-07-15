package com.example.quiz_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Courses {
    AdminController obj = new AdminController();
    StudentController stud = new StudentController();
    private String courseName;
    private String courseID;
    private String[] CourseNames;
    private String[] CourseIDs;
    private String[] ClassCorses;

    public Courses(String CourseID, String CourseName){
        courseName=CourseName;
        courseID = CourseID;
        CourseNames = null;
        CourseIDs = null;
        ClassCorses = null;
    }
    public Courses(){
        courseName=null;
        courseID=null;
        CourseNames = null;
        CourseIDs = null;
        ClassCorses = null;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public ObservableList<Courses> initialization(){
        CourseNames = obj.showCourse();
        CourseIDs = obj.showCourseID();
        ObservableList<Courses> x = FXCollections.observableArrayList();
        for (int i = 0; i < CourseNames.length; i++) {
            x.add(new Courses(CourseIDs[i], CourseNames[i]));
        }
        return x;
    }
    public ObservableList<String> SectionBasedCourses(String section){
        ClassCorses = obj.showSectionsWithCourse(section);

        return FXCollections.observableArrayList(ClassCorses);
    }
    public String[] ShowCourse(){
        return obj.ShowCourse();
    }
    public ObservableList<Courses> studinitialization(String Username){
        CourseNames = stud.courses_studying_name(Username);
        CourseIDs = stud.courses_studying(Username);
        ObservableList<Courses> x = FXCollections.observableArrayList();
        for (int i = 0; i < CourseIDs.length; i++) {
            x.add(new Courses(CourseIDs[i],CourseNames[i]));
        }
        return x;
    }
}
