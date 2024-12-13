package Controller;

import Entity.Student;
import ValidationEntity.validations;

import javax.xml.validation.Validator;
import java.io.*;
import java.util.List;

public class StudentTest {
    private List<Student> students;

    public StudentTest(List<Student> students){
        this.students = students;
    }

    public void addStudent(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        validations validator = new validations();
        try{
            for(int i = 0; i < 3; i++){
                String rollNumber;
                while(true){
                    System.out.println("Enter roll number: ");
                    rollNumber = bufferedReader.readLine();
                    if(validator.rollNumberIsValid(rollNumber)){
                        break;
                    }else if(checkRollNumber(rollNumber)){
                        System.out.println("Exists roll number. Please try again!");
                        break;
                    }else {
                        System.out.println("Invalid roll number. Please try again!");
                    }
                }
                System.out.println("Enter name: ");
                String name = bufferedReader.readLine();
                System.out.println("Enter address: ");
                String address = bufferedReader.readLine();
                System.out.println("Enter age: ");
                int age = Integer.parseInt(bufferedReader.readLine());
                Student student = new Student(rollNumber, name, address, age);
                students.add(student);
            }
            writeToFile(students, "student.bat");
            System.out.println("Successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(List<Student> students, String fileName){
      try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
          for(Student st : students){
            bufferedWriter.write(st.toString());
            bufferedWriter.newLine();
          }
      }catch(IOException e){
          System.out.println(e.getMessage());
      }
    }

    public void readFile(String fileName){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println("list file: ");
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public List<Student> showAgeStudent(){
        System.out.println("List student age less than 18: ");
         List<Student> result =  students.stream()
                .filter(s -> s.getAge() < 18)
                .toList();
         result.forEach(r -> System.out.println(r));
         return result;
    }
    public boolean checkRollNumber(String rollNumber){
        return students.stream().anyMatch(std -> std.getRollNumber().equalsIgnoreCase(rollNumber));
    }
}
