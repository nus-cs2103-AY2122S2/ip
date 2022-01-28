import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        File data = new File("../../data.txt");
        Scanner s = new Scanner(data);
        String k = s.nextLine();
        System.out.println(k);
        String[] x = k.split("\\|");
        for(String i : x){
            System.out.println(i);
        }
    }
}
