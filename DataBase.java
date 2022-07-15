package com.example.quiz_management;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class DataBase {
    private Statement statement;
    public DataBase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_management", "root", "0475");
            statement = connection.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean AdminLogin(String username,String password) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from superuser where username = '" + username + "' AND password = '" + password + "';");
            if (resultSet.next()) {
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean StudentLogin(String username,String password) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from student where studentusername = '" + username + "' AND studentpassword = '" + password + "';");
            if (resultSet.next()) {
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String AdminuserName(String username,String password){
        try {
            ResultSet resultSet = statement.executeQuery("select * from superuser where username = '" + username + "' AND password = '" + password + "';");
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String StudentuserName(String username,String password){
        try {
            ResultSet resultSet = statement.executeQuery("select * from student where studentusername = '" + username + "' AND studentpassword = '" + password + "';");
            if (resultSet.next()) {
                return resultSet.getString("idstudent");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void addCourse(String course){
        try {
            statement.executeUpdate("INSERT INTO `quiz_management`.`course`(`coursename`) VALUES('"+course+"');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String[] showCourse(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            int i=0;
            while(resultSet.next()){
                i++;
            }
            String[] courses = new String[i];
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            for(i=0 ; resultSet.next() ; i++){
                courses[i]=resultSet.getString("coursename");
            }
            return courses;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] showCourseID(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            int i=0;
            while(resultSet.next()){
                i++;
            }
            String[] coursesID = new String[i];
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            for(i=0 ; resultSet.next() ; i++){
                coursesID[i]=resultSet.getString("idcourse");
            }
            return coursesID;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] showSections(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`section`;");
            int i=0;
            while(resultSet.next()){
                i++;
            }
            String[] sections = new String[i];
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`section`;");
            for(i=0 ; resultSet.next() ; i++){
                sections[i]=resultSet.getString("sectionname");
            }
            return sections;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] showSectionsWithCourse(String section){
        String sectionID="";
        int i=0;

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`section` WHERE `sectionname` = '" + section + "';");
            if (resultSet.next()) {
                sectionID = resultSet.getString("idsection");
            }
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`coursesstudiedbysection` WHERE `idsection` = '" + sectionID + "';");
            while (resultSet.next()){
                i++;
            }
            if(i!=0) {
                String[] coursesStudied = new String[i];
                resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`coursesstudiedbysection` WHERE `idsection` = '" + sectionID + "';");
                for (i = 0; resultSet.next(); i++) {
                    coursesStudied[i] = resultSet.getString("idcourse");
                }
                StringBuilder Query = new StringBuilder("SELECT * FROM `quiz_management`.`course` WHERE ");
                Query.append("`idcourse` !='").append(coursesStudied[0]).append("' ");
                for (i = 1; i < coursesStudied.length; i++) {
                    Query.append("AND `idcourse` !='").append(coursesStudied[i]).append("' ");
                }
                Query.append(";");
                i = 0;
                resultSet = statement.executeQuery(Query.toString());
                while (resultSet.next()) {
                    i++;
                }
                String[] Courses_LIST = new String[i];
                resultSet = statement.executeQuery(Query.toString());
                for (i = 0; resultSet.next(); i++) {
                    Courses_LIST[i] = resultSet.getString("coursename");
                }
                return Courses_LIST;
            }else{

               resultSet = statement.executeQuery( "SELECT * FROM `quiz_management`.`course`;");
                while (resultSet.next()) {
                    i++;
                }
                String[] Courses_LIST = new String[i];
                resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
                for (i = 0; resultSet.next(); i++) {
                    Courses_LIST[i] = resultSet.getString("coursename");
                }
                return Courses_LIST;


            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void assignCourseToSection(String course, String section){
        try {
            String sectionID="";
            String courseID="";
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`section` WHERE `sectionname` = '" + section + "';");
            if (resultSet.next()) {
                sectionID = resultSet.getString("idsection");
            }
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course` WHERE `coursename` = '" + course + "';");
            if (resultSet.next()) {
                courseID = resultSet.getString("idcourse");
            }
            statement.executeUpdate("INSERT INTO `quiz_management`.`coursesstudiedbysection`(`idcourse`,`idsection`)VALUES("+courseID+","+sectionID+");");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addStudent(String Name,String RollNo,String Username,String Password,String Gender,String Contact){
        try {
            statement.executeUpdate("INSERT INTO `quiz_management`.`student`(`idstudent`,`studentname`,`studentusername`,`studentpassword`,`studentgender`,`studentcontact`)VALUES("+RollNo+",'"+Name+"','"+Username+"','"+Password+"','"+Gender+"','"+Contact+"');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addTeacher(String Name,String ID,String Username,String Password,String Gender,String Contact){
        try {
            statement.executeUpdate("INSERT INTO `quiz_management`.`teacher`(`idteacher`,`teachername`,`teacherusername`,`teacherpassword`,`teachergender`,`teachercontact`)VALUES("+ID+",'"+Name+"','"+Username+"','"+Password+"','"+Gender+"','"+Contact+"');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean searchStudentID(String ID){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`student` WHERE `idstudent` = "+ID+";");
            if(resultSet.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void AssignSection(String RollNo,String Section){
        try {
            ResultSet resultSet = statement.executeQuery("Select * FROM `quiz_management`.`section` WHERE `sectionname` = '"+Section+"'");
            String SectionID="";
            if(resultSet.next()){
                SectionID = resultSet.getString("idsection");
            }
            statement.executeUpdate("INSERT INTO `quiz_management`.`studentsection`(`idstudent`,`idsection`)VALUES("+RollNo+","+SectionID+");");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean searchStudentIDCheck(String ID){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`studentsection` WHERE `idstudent` = "+ID+";");
            if(resultSet.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String[] ShowCourse(){
        try {
            int i=0;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            while (resultSet.next()){
                i++;
            }
            String[] CourseList = new String[i];
            i=0;
            resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course`;");
            while (resultSet.next()){
                CourseList[i]=resultSet.getString("coursename");
                i++;
            }
            return CourseList;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String AssignCourse(String ID, String Course, String Section){
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`teacher` WHERE `idteacher` = "+ID+";");
            if(resultSet.next()){
                resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`course` WHERE `coursename` = '"+Course+"';");
                if(resultSet.next()){
                    Course = resultSet.getString("idcourse");
                }else{
                    return "Not";
                }
                resultSet = statement.executeQuery("SELECT * FROM `quiz_management`.`section` WHERE `sectionname` = '"+Section+"';");
                if(resultSet.next()){
                    Section = resultSet.getString("idsection");
                }else{
                    return "Not";
                }
                statement.executeUpdate("INSERT INTO `quiz_management`.`teachersectioncourse`(`idteacher`,`idcourse`,`idsection`)VALUES("+ID+","+Course+","+Section+");");
                return "Assigned";
            }else{
                return "Not";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "Not";
    }
    public boolean TeacherLogin(String username,String password) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from teacher where teacherusername = '" + username + "' AND teacherpassword = '" + password + "';");
            if (resultSet.next()) {
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String TeacheruserName(String username,String password){
        try {
            ResultSet resultSet = statement.executeQuery("select * from teacher where teacherusername = '" + username + "' AND teacherpassword = '" + password + "';");
            if (resultSet.next()) {
                return resultSet.getString("idteacher");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getCourses(String ID){
        try{
            int i =0;
            ResultSet resultSet = statement.executeQuery("select * from teachersectioncourse where idteacher = '" + ID + "';");
                while (resultSet.next()){
                    i++;
                }
                if(i==0){
                    return null;
                }
                else{
                  String[] coursesid = new String[i];
                  i=0;
                  resultSet = statement.executeQuery("select * from teachersectioncourse where idteacher = '" + ID + "';");
                  while (resultSet.next()) {
                      coursesid[i] = resultSet.getString("idcourse");
                      i++;
                  }
                  String[] Courses = new String[i];
                    int j = 0;
                  while(j<i) {
                      resultSet = statement.executeQuery("select * from course where idcourse = " + coursesid[j] + ";");
                      if (resultSet.next()) {
                          Courses[j] = resultSet.getString("coursename");
                      }
                      j++;
                  }
                  return Courses;
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String TeacherdisplayName(String ID){
        try{
            ResultSet resultSet = statement.executeQuery("Select * from teacher where idteacher = "+ID+";");
            if(resultSet.next()){
                return resultSet.getString("teachername");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getSections(String Course,String ID){
      try{
          ResultSet resultSet = statement.executeQuery("select * from course where coursename = '"+Course+"';");
          if(resultSet.next()) {
              String idcourse = resultSet.getString("idcourse");

              int i = 0;
              resultSet = statement.executeQuery("select * from teachersectioncourse where idteacher = '" + ID + "' AND idcourse = '" + idcourse + "';");
              while (resultSet.next()) {
                  i++;
              }
              if (i == 0) {
                  return null;
              } else {
                  String[] sectionid = new String[i];
                  i = 0;
                  resultSet = statement.executeQuery("select * from teachersectioncourse where idteacher = '" + ID +"' AND idcourse = '" + idcourse + "';");
                  while (resultSet.next()) {
                      sectionid[i] = resultSet.getString("idsection");
                      i++;
                  }
                  String[] sections = new String[i];
                  int j = 0;
                  while (j < i) {
                      resultSet = statement.executeQuery("select * from section where idsection = " + sectionid[j] + ";");
                      if (resultSet.next()) {
                          sections[j] = resultSet.getString("sectionname");
                      }
                      j++;
                  }
                  return sections;
              }
          }
      }
      catch(Exception e){
          e.printStackTrace();
      }
      return null;
    }
    public void insertQuiz(String ID,LocalDateTime quizDateTime, String Duration, String section, String course){
        try{
            String sectionid="";
            String courseid="";
            ResultSet resultSet = statement.executeQuery("select * from section where sectionname = '"+section+"';");
            if(resultSet.next()){
                sectionid = resultSet.getString("idsection");
            }
            resultSet = statement.executeQuery("select * from course where coursename = '"+course+"';");
            if(resultSet.next()){
                courseid = resultSet.getString("idcourse");
            }
            statement.executeUpdate("INSERT INTO `quiz_management`.`quiz`(`idquiz`,`quizduration`,`quizdatetime`,`idcourse`,`idsection`)VALUES('"+ID+"','"+Duration+"','"+quizDateTime+"',"+courseid+","+sectionid+");");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addQuestion(String ID,String noOfQuestions,String question,String op1,String op2,String op3,String op4,String ans,String marks){
        try{
            statement.executeUpdate("INSERT INTO `quiz_management`.`quizdatabase`(`idquiz`,`questionno`,`question`,`op1`,`op2`,`op3`,`op4`,`ans`,`marks`)VALUES('"+ID+"','"+noOfQuestions+"','"+question+"','"+op1+"','"+op2+"','"+op3+"','"+op4+"','"+ans+"','"+marks+"');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String[] details(String ID)
    {
        try {
            String[] student_data = new String[4];
            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`student` where `idstudent` = '" + ID + "';");
            if(resultSet.next())
            {
                student_data[0] = resultSet.getString("idstudent");
                student_data[1] = resultSet.getString("studentgender");
                student_data[2] = resultSet.getString("studentcontact");
                student_data[3] = resultSet.getString("studentname");
            }
            return student_data;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public String section_student(String ID)
    {
        try {
            String section_id ;
            String section_name ;

            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`studentsection` where `idstudent` = '" + ID  + "';");
                if(resultSet.next()) {
                    section_id = resultSet.getString("idsection");
                    resultSet = statement.executeQuery("select * from `quiz_management`.`section` where `idsection` = '" + section_id  + "';");
                    if(resultSet.next())
                    {
                        section_name = resultSet.getString("sectionname");
                        return section_name;
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] courses_studying(String ID)
    {
        try {
            String section_id ;
                ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`studentsection` where `idstudent` = '" + ID  + "';");
                if(resultSet.next()) {
                    section_id = resultSet.getString("idsection");
                    resultSet = statement.executeQuery("select * from `quiz_management`.`coursesstudiedbysection` where `idsection` = '" + section_id  + "';");
                    int i =0;
                    while(resultSet.next())
                    {
                        i++;
                    }
                    String[] courses_list_id = new String[i];
                    resultSet = statement.executeQuery("select * from `quiz_management`.`coursesstudiedbysection` where `idsection` = '" + section_id  + "';");
                    i=0;
                    while(resultSet.next())
                    {
                        courses_list_id[i] = resultSet.getString("idcourse");
                        i++;

                    }
                    return courses_list_id;
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }



    public String[] courses_studying_name(String ID)
    {
        try {

            String section_id ;
            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`studentsection` where `idstudent` = '" + ID  + "';");
                if(resultSet.next()) {

                    section_id = resultSet.getString("idsection");
                    resultSet = statement.executeQuery("select * from `quiz_management`.`coursesstudiedbysection` where `idsection` = '" + section_id  + "';");
                    int i =0;
                    while(resultSet.next())
                    {
                        i++;
                    }
                    String[] courses_list_id = new String[i];
                    String[] courses_list_name = new String[i];
                    resultSet = statement.executeQuery("select * from `quiz_management`.`coursesstudiedbysection` where `idsection` = '" + section_id  + "';");
                    i=0;
                    while(resultSet.next())
                    {
                        courses_list_id[i] = resultSet.getString("idcourse");
                        i++;
                    }
                    i=0;
                    while(i< courses_list_id.length)
                    {
                        resultSet = statement.executeQuery("select * from `quiz_management`.`course` where `idcourse` = '" + courses_list_id[i]  + "';");
                        if(resultSet.next()) {
                            courses_list_name[i] = resultSet.getString("coursename");
                        }
                        i++;
                    }
                    return courses_list_name;
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public String StudentdisplayName(String ID){
        try{
            ResultSet resultSet = statement.executeQuery("Select * from student where idstudent = "+ID+";");
            if(resultSet.next()){
                return resultSet.getString("studentname");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getQID(String S_ID){
        try{
            ResultSet resultSet = statement.executeQuery("Select * from studentsection where idstudent = "+S_ID+";");
            if(resultSet.next()){
                String Section = resultSet.getString("idsection");
                resultSet = statement.executeQuery("Select * from coursesstudiedbysection where idsection = "+Section+";");
                int j=0;
                while (resultSet.next()) {
                    j++;
                }
                resultSet = statement.executeQuery("Select * from coursesstudiedbysection where idsection = "+Section+";");
                String[] Course = new String[j];
                j=0;
                while (resultSet.next()) {
                    Course[j] = resultSet.getString("idcourse");
                    j++;
                }
                int i=0;

                String[] list = new String[j*5];
                j=0;
                while(i<Course.length){
                    resultSet = statement.executeQuery("Select * from quiz where idsection = "+Section+" AND idcourse = "+Course[i]+";");
                    while(resultSet.next()){
                        list[j]=resultSet.getString("idquiz");
                        j++;
                    }
                    i++;
                }
                i=0;
                j=0;
                while(i< list.length){
                    if(list[i]!=null) {
                        j++;
                    }
                    i++;
                }
                String[] list1 = new String[j];
                i=0;
                j=0;
                while(i< list.length){
                    if(list[i]!=null) {
                        list1[j]=list[i];
                        j++;
                    }
                    i++;
                }
                return list1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getQuestion(String Q_ID,String Question_num){

        try{
            ResultSet resultSet = statement.executeQuery("Select * from quizdatabase where idquiz = '"+Q_ID+"' AND questionno = '"+Question_num+"';");
            if(resultSet.next()){
                return resultSet.getString("question");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getop(String num,String Q_ID,String Question_num){
        try{
            ResultSet resultSet = statement.executeQuery("Select * from quizdatabase where idquiz = '"+Q_ID+"' AND questionno = '"+Question_num+"';");
            if(resultSet.next()) {
                return resultSet.getString("op"+num);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public void insertAnswer(String ans,String Q_ID,String S_ID,String Question_num){
        try{
            statement.executeUpdate("INSERT INTO `quiz_management`.`studentanswers`(`idquiz`,`questionno`,`idstudent`,`ans`)VALUES('"+Q_ID+"','"+Question_num+"','"+S_ID+"','"+ans+"');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Integer getTotalQuestions(String Q_ID){
        try{
            ResultSet resultSet = statement.executeQuery("Select * from quizdatabase where idquiz = '"+Q_ID+"';");
            Integer i = 0;
            while(resultSet.next()){
                i++;
            }
            return i;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public void sendQuery(String Q_ID_query,String query_detail,String S_ID){
        try{
            statement.executeUpdate("INSERT INTO `quiz_management`.`query`(`idquiz`,`idstudent`,`query`,`queryresponse`)VALUES('"+Q_ID_query+"','"+S_ID+"','"+query_detail+"','Not Responded');");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String[] getQIDs(String ID){
        try{
            int i=0;
         ResultSet resultSet = statement.executeQuery("select * from teachersectioncourse where `idteacher` = "+ID+";");
         while(resultSet.next()){
             i++;
         }
            String[] IDS = new String[i];
            String[] cIDS = new String[i];
            i=0;
            resultSet = statement.executeQuery("select * from teachersectioncourse where `idteacher` = "+ID+";");
            while(resultSet.next()){
                IDS[i] = resultSet.getString("idsection");
                cIDS[i] = resultSet.getString("idcourse");
                i++;
            }
            String[] final_IDS = new String[i*5];
            int j=0;
            i=0;
            while(i< IDS.length) {
                resultSet = statement.executeQuery("select * from quiz where `idsection` = " + IDS[i] + " AND `idcourse` = '" + cIDS[i] + "';");
                while (resultSet.next()) {
                    final_IDS[j] = resultSet.getString("idquiz");
                    j++;
                }
                i++;
            }
            String[] return_arr = new String[j];
            i=0;
            j=0;
            while(i<final_IDS.length){
                if(final_IDS[i]!=null){
                    return_arr[j] = final_IDS[i];
                    j++;
                }
                i++;
            }
            return return_arr;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getSQIDs(String Q_ID){
        try{
         ResultSet resultSet = statement.executeQuery("Select * from query where idquiz = '"+Q_ID+"' AND queryresponse = 'Not Responded';");
         int i =0 ;
         while(resultSet.next()){
               i++;
         }
         String[] IDS= new  String[i];
            resultSet = statement.executeQuery("Select * from query where idquiz = '"+Q_ID+"' AND queryresponse = 'Not Responded';");
            i =0 ;
            while(resultSet.next()){
                IDS[i] = resultSet.getString("idstudent");
                i++;
            }
            return IDS;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getQueryText(String ID,String S_name){
        try {
            ResultSet resultSet = statement.executeQuery("Select * from query where idquiz = '" + ID + "' AND idstudent = '"+S_name+"';");
            if(resultSet.next()) {
                return resultSet.getString("query") ;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void submitResponse(String ID,String S_name,String response_text){
        try{
         statement.executeUpdate("UPDATE query SET queryresponse = '"+response_text+"' WHERE idquiz = '"+ID+"' AND idstudent = '"+S_name+"';");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getResponseText(String ID,String SID){
        try {
            ResultSet resultSet = statement.executeQuery("select * from query where idquiz = '"+ID+"' AND idstudent = '"+SID+"';");
            if(resultSet.next()){
                return resultSet.getString("queryresponse");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getQIDR(String ID){
        try{
            int i=0;
            ResultSet resultSet = statement.executeQuery("select * from query where idstudent = '"+ID+"';");
            while (resultSet.next()){
                i++;

            }
            String [] x= new String[i];
            i=0;
            resultSet = statement.executeQuery("select * from query where idstudent = '"+ID+"';");
            while (resultSet.next()){
                x[i]=resultSet.getString("idquiz");
                i++;
            }
            return x;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String[] getResultQID(String ID) {
        try {
            ResultSet resultSet = statement.executeQuery("Select * from studentsection where idstudent = " + ID + ";");
            if (resultSet.next()) {
                String Section = resultSet.getString("idsection");
                resultSet = statement.executeQuery("Select * from coursesstudiedbysection where idsection = " + Section + ";");
                int j = 0;
                while (resultSet.next()) {
                    j++;
                }
                resultSet = statement.executeQuery("Select * from coursesstudiedbysection where idsection = " + Section + ";");
                String[] Course = new String[j];
                j = 0;
                while (resultSet.next()) {
                    Course[j] = resultSet.getString("idcourse");
                    j++;
                }
                int i = 0;

                String[] list = new String[j * 5];
                j = 0;
                while (i < Course.length) {
                    resultSet = statement.executeQuery("Select * from quiz where idsection = " + Section + " AND idcourse = " + Course[i] + ";");
                    while (resultSet.next()) {
                        list[j] = resultSet.getString("idquiz");
                        j++;
                    }
                    i++;
                }
                i = 0;
                j = 0;
                while (i < list.length) {
                    if (list[i] != null) {
                        j++;
                    }
                    i++;
                }
                String[] list1 = new String[j];
                i = 0;
                j = 0;
                while (i < list.length) {
                    if (list[i] != null) {
                        list1[j] = list[i];
                        j++;
                    }
                    i++;
                }
                return list1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String[] getResult(String Q_ID_Result,String ID){
        try{
            Integer i=0;
            String[] result = new String[3];
            ResultSet resultSet = statement.executeQuery("select * from studentanswers where idstudent = '"+ID+"' and idquiz = '"+Q_ID_Result+"';");
            while(resultSet.next()){
                i++;
            }
            if(i==0){
                String[] arr = new String[3];
                arr[0]="0";
                arr[1]="0";
                arr[2]="0";
                return arr;
            }
            result[0]=i.toString();
            String[] ans = new String[i];
            i=0;
            resultSet = statement.executeQuery("select * from studentanswers where idstudent = '"+ID+"' and idquiz = '"+Q_ID_Result+"';");
            while(resultSet.next()){
                ans[i]=resultSet.getString("ans");
                i++;
            }
            i=0;
            Integer j=0;
            Integer k=0;
            resultSet = statement.executeQuery("select * from quizdatabase where idquiz = '"+Q_ID_Result+"';");
            while(resultSet.next()){
                if(Objects.equals(ans[i], resultSet.getString("ans"))){
                    j++;
                    k = k + Integer.parseInt(resultSet.getString("marks"));
                }
                i++;
            }
            result[1]=j.toString();
            result[2]=k.toString();
            return result;


        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean getAttemptID(String ID,String IDQ){
        try {
            ResultSet resultSet = statement.executeQuery("select * from studentanswers where idstudent = '"+ID+"' and idquiz = '"+IDQ+"';");
            return resultSet.next();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String[] getStudents(String IDQ){
        try {
            ResultSet resultSet = statement.executeQuery("select * from quiz where idquiz = '"+IDQ+"';");
            if(resultSet.next()){
                String section = resultSet.getString("idsection");

                resultSet = statement.executeQuery("select * from studentsection where idsection = '"+section+"';");
                int i=0;
                while(resultSet.next()){
                    i++;
                }
                String[] arr = new String[i];
                resultSet = statement.executeQuery("select * from studentsection where idsection = '"+section+"';");
                i=0;
                while(resultSet.next()){
                    arr[i]=resultSet.getString("idstudent");
                    i++;
                }
                return arr;


            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    //////////////////////////////////////////////////////
    public String[] details_teacher(String PID)
    {
        try {
            String[] teacher_data = new String[4];
            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`teacher` where `idteacher` = '" + PID + "';");
            if(resultSet.next())
            {
                teacher_data[0] = resultSet.getString("idteacher");
                teacher_data[1] = resultSet.getString("teachergender");
                teacher_data[2] = resultSet.getString("teachercontact");
                teacher_data[3] = resultSet.getString("teachername");
            }
            return teacher_data;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public String[] courses_taught_name(String PID)
    {
        try {
            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`teachersectioncourse` where `idteacher` = '" + PID  + "';");
            int i =0;
            while(resultSet.next())
            {
                i++;
            }
            String[] coursestaught_list_id = new String[i];
            String[] coursestaught_name = new String[i];
            resultSet = statement.executeQuery("select * from `quiz_management`.`teachersectioncourse` where `idteacher` = '" + PID  + "';");
            i=0;
            while(resultSet.next())
            {
                coursestaught_list_id[i] = resultSet.getString("idcourse");
                i++;

            }

            i=0;
            while(i< coursestaught_list_id.length)
            {
                resultSet = statement.executeQuery("select * from `quiz_management`.`course` where `idcourse` = '" + coursestaught_list_id[i]  + "';");
                if(resultSet.next()) {
                    coursestaught_name[i] = resultSet.getString("coursename") + "\r\n";
                }
                i++;
            }
            return coursestaught_name;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] courses_taught_sectionname(String PID)
    {
        try {
            ResultSet resultSet = statement.executeQuery("select * from `quiz_management`.`teachersectioncourse` where `idteacher` = '" + PID  + "';");
            int i =0;
            while(resultSet.next())
            {
                i++;
            }
            String[] coursestaught_section_id = new String[i];
            String[] section_name = new String[i];
            resultSet = statement.executeQuery("select * from `quiz_management`.`teachersectioncourse` where `idteacher` = '" + PID  + "';");
            i=0;
            while(resultSet.next())
            {
                coursestaught_section_id[i] = resultSet.getString("idsection");
                i++;

            }

            i=0;
            while(i< coursestaught_section_id.length)
            {
                resultSet = statement.executeQuery("select * from `quiz_management`.`section` where `idsection` = '" + coursestaught_section_id[i]  + "';");
                if(resultSet.next()) {
                    section_name[i] = resultSet.getString("sectionname");
                }
                i++;
            }
            return section_name;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
