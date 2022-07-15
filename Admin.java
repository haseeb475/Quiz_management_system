package com.example.quiz_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Admin implements Initializable {
    AdminController obj = new AdminController();
    Student student = new Student();
    Courses course = new Courses();
    Teacher teacher = new Teacher();
    @FXML
    private AnchorPane AddStudentScreen;
    @FXML
    private AnchorPane AddTeacherAnchorPane;
    @FXML
    private AnchorPane AssignCourseAnchorPane;
    @FXML
    private Label upper_label;
    @FXML
    private Label User;
    @FXML
    private AnchorPane AssignSectionScreen;
    @FXML
    private ChoiceBox<String> AvailableSections;
    @FXML
    private ChoiceBox<String> Gender;
    @FXML
    private TextField StudentContact;
    @FXML
    private TextField StudentName;
    @FXML
    private PasswordField StudentPassword;
    @FXML
    private TextField StudentRollNo;
    @FXML
    private TextField StudentRollNoSearch;
    @FXML
    private TextField StudentUsername;
    @FXML
    private Label sectionLabel;
    @FXML
    private Button SectionAssignButton;
    @FXML
    private TextField CourseName;
    @FXML
    private ListView<String> CourseListsList;
    @FXML
    private TableView<Courses> CourseTable;
    @FXML
    private ChoiceBox<String> ClassSection;
    @FXML
    private TableColumn<Courses, String> CoursesList;
    @FXML
    private TableColumn<Courses, String> CoursesIDList;
    @FXML
    private AnchorPane ManageClass;
    @FXML
    private AnchorPane ManageCourse;
    @FXML
    private AnchorPane ManageStudent;
    @FXML
    private AnchorPane ManageTeacher;
    @FXML
    private ChoiceBox<String> CourseListChoice;
    @FXML
    private ChoiceBox<String> SectionListChoice;
    @FXML
    private PasswordField TeacherPassword;

    @FXML
    private TextField TeacherContact;

    @FXML
    private ChoiceBox<String> TeacherGender;

    @FXML
    private TextField TeacherID;

    @FXML
    private TextField TeacherIDSearch;

    @FXML
    private TextField TeacherName;

    @FXML
    private TextField TeacherUsername;


    private String SelectedCourses;
    private String TempRollNo;


    public void setUser(String user) {
        User.setText(user);
    }

    @FXML
    public void onManageCourse() {
        ManageCourse.setVisible(true);
        CourseTable.setVisible(true);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(false);
        CourseTable.setItems(course.initialization());
        SelectedCourses = "";
        upper_label.setText("Courses");


    }

    @FXML
    public void onManageClass() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(true);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(false);
        upper_label.setText("Classes");
        CourseListsList.setItems(course.SectionBasedCourses(ClassSection.getValue()));
        CourseListsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        SelectedCourses = "";
        CourseListsList.getSelectionModel().clearSelection();
    }

    @FXML
    public void onManageStudent() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(true);
        ManageTeacher.setVisible(false);
        AssignSectionScreen.setVisible(false);
        AddStudentScreen.setVisible(true);
        upper_label.setText("Students");
    }

    @FXML
    public void onManageTeacher() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(true);
        AddTeacherAnchorPane.setVisible(true);
        AssignCourseAnchorPane.setVisible(false);
        upper_label.setText("Teachers");
    }
    @FXML
    public void onAssignButton(){
        if(!Objects.equals(SelectedCourses, "")) {
            obj.assignCourseToSection(SelectedCourses, ClassSection.getValue());
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Confirmation");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Course Assigned Successfully.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
            SelectedCourses = "";
        }else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Course is not selected.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
        }
    }
    @FXML
    public void onSectionSelection(){
        CourseListsList.setItems(course.SectionBasedCourses(ClassSection.getValue()));

    }

    @FXML
    public void onAddCourse() {
        CourseTable.setVisible(true);
        obj.AddCourse(CourseName.getText());
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Confirmation");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Course Successfully Added.");
        dialog.getDialogPane().getButtonTypes().add(type);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
        dialog.showAndWait();
        CourseName.setText("");
        CourseTable.setItems(course.initialization());
    }
    @FXML
    public void onAddStudent() {
        if(Objects.equals(StudentName.getText(), "") || Objects.equals(StudentRollNo.getText(), "") || Objects.equals(StudentUsername.getText(), "") || Objects.equals(StudentPassword.getText(), "") || Objects.equals(Gender.getValue(), "") || Objects.equals(StudentContact.getText(), "")){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Enter Correct Details.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
        }else {
            student.addStudent(StudentName.getText(), StudentRollNo.getText(), StudentUsername.getText(), StudentPassword.getText(), Gender.getValue(), StudentContact.getText());
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Confirmation");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Student Successfully Added.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
            StudentName.setText("");
            StudentRollNo.setText("");
            StudentUsername.setText("");
            StudentPassword.setText("");
            Gender.setValue("Male");
            StudentContact.setText("");
        }
    }
    @FXML
    public void onAddTeacher() {
        if(Objects.equals(TeacherName.getText(), "") || Objects.equals(TeacherID.getText(), "") || Objects.equals(TeacherUsername.getText(), "") || Objects.equals(TeacherPassword.getText(), "") || Objects.equals(TeacherGender.getValue(), "") || Objects.equals(TeacherContact.getText(), "")){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Enter Correct Details.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
        }else {
            teacher.addTeacher(TeacherName.getText(), TeacherID.getText(), TeacherUsername.getText(), TeacherPassword.getText(), TeacherGender.getValue(), TeacherContact.getText());
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Confirmation");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Teacher Successfully Added.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
            TeacherName.setText("");
            TeacherID.setText("");
            TeacherUsername.setText("");
            TeacherPassword.setText("");
            TeacherGender.setValue("Male");
            TeacherContact.setText("");
        }
    }
    @FXML
    public void onAddStudentScreen() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(true);
        ManageTeacher.setVisible(false);
        AssignSectionScreen.setVisible(false);
        AddStudentScreen.setVisible(true);

    }
    @FXML
    public void onAddTeacherScreen() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(true);
        AssignCourseAnchorPane.setVisible(false);
        AddTeacherAnchorPane.setVisible(true);
    }
    @FXML
    public void onAssignSectionButton() {
        student.AssignSection(TempRollNo,AvailableSections.getValue());
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Confirmation");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Section Assigned Successfully.");
        dialog.getDialogPane().getButtonTypes().add(type);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
        dialog.showAndWait();
        SectionAssignButton.setVisible(false);
        sectionLabel.setVisible(false);
        AvailableSections.setVisible(false);
    }
    @FXML
    public void onAssignSectionScreenStudent() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(true);
        ManageTeacher.setVisible(false);
        AssignSectionScreen.setVisible(true);
        AddStudentScreen.setVisible(false);
        SectionAssignButton.setVisible(false);
        sectionLabel.setVisible(false);
        AvailableSections.setVisible(false);

    }
    @FXML
    public void onAssignCourseScreenTeacher() {
        ManageCourse.setVisible(false);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(true);
        AssignCourseAnchorPane.setVisible(true);
        AddTeacherAnchorPane.setVisible(false);

    }
    @FXML
    public void onSearch() {
        if(student.searchStudentID(StudentRollNoSearch.getText())){
            TempRollNo = StudentRollNoSearch.getText();
            if(student.searchStudentIDCheck(TempRollNo)){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Error");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Entered Roll No is Already in a Section.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
                SectionAssignButton.setVisible(false);
                sectionLabel.setVisible(false);
                AvailableSections.setVisible(false);
            }else{
                SectionAssignButton.setVisible(true);
                sectionLabel.setVisible(true);
                AvailableSections.setVisible(true);
            }
        }else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Entered Roll No is Incorrect.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
            SectionAssignButton.setVisible(false);
            sectionLabel.setVisible(false);
            AvailableSections.setVisible(false);
        }

    }
    @FXML
    public void onAssignCourseButton(){
        if(Objects.equals(TeacherIDSearch.getText(), "") || Objects.equals(CourseListChoice.getValue(), "") || Objects.equals(SectionListChoice.getValue(), "")){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Error");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Enter Correct Details.");
            dialog.getDialogPane().getButtonTypes().add(type);
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
            dialog.showAndWait();
        }else {
            if (Objects.equals(teacher.AssignCourse(TeacherIDSearch.getText(), CourseListChoice.getValue(), SectionListChoice.getValue()), "Assigned")) {
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Confirmation");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Assigned Successfully.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
                TeacherIDSearch.setText("");
                CourseListChoice.setValue("");
                SectionListChoice.setValue("");
            } else {
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Error");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Error in Assigning Section.");
                dialog.getDialogPane().getButtonTypes().add(type);
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("/icon.png")).toString()));
                dialog.showAndWait();
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManageCourse.setVisible(true);
        ManageClass.setVisible(false);
        ManageStudent.setVisible(false);
        ManageTeacher.setVisible(false);
        CourseTable.setVisible(false);
        upper_label.setVisible(true);
        upper_label.setText("Courses");

        CoursesList.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        CoursesIDList.setCellValueFactory(new PropertyValueFactory<>("courseID"));


        CourseTable.setItems(course.initialization());
        String[] sectionsList = obj.showSections();
        ClassSection.getItems().addAll(sectionsList);
        ClassSection.setValue(sectionsList[0]);
        SectionListChoice.getItems().addAll(sectionsList);
        AvailableSections.getItems().addAll(sectionsList);
        AvailableSections.setValue(sectionsList[0]);
        CourseListChoice.getItems().addAll(course.ShowCourse());
        CourseListsList.setItems(course.SectionBasedCourses(ClassSection.getValue()));
        CourseListsList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            SelectedCourses = CourseListsList.getSelectionModel().getSelectedItem();
        });
        Gender.getItems().addAll("Male","Female");
        Gender.setValue("Male");
        TeacherGender.getItems().addAll("Male","Female");
        TeacherGender.setValue("Male");


    }



}
