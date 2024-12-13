package ValidationEntity;

public class validations {
    String pattern = "^[a-zA-Z]\\d{4}[a-zA-Z]\\d{4}$";
     public boolean rollNumberIsValid(String rollNumber){
         return rollNumber.matches(pattern);
     }




}
