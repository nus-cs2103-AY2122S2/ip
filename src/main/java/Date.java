import java.time.LocalDate;

public class Date {
    public static String toString(LocalDate str) {
        String month = str.getMonth().toString().substring(0,1).toUpperCase() + str.getMonth().toString().substring(1).toLowerCase();;
        
        int year = str.getYear();
        int date = str.getDayOfMonth();

        return Integer.toString(date) + " " + month + " " + Integer.toString(year); 
    }
    
}
