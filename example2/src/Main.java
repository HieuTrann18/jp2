import Controller.StudentTest;
import Entity.Data;
import Entity.Student;
import Entity.ThreadOne;
import Entity.ThreadTwo;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        StudentTest studentTest = new StudentTest(students);
//        studentTest.addStudent();

        Data data = new Data();
        ThreadTwo threadTwo = new ThreadTwo();
        ThreadOne threadOne = new ThreadOne(data, threadTwo);
        threadOne.start();


//        for(Student student : students){
//            System.out.println(student.toString());
//        }
//
//        studentTest.showAgeStudent();



    }
}