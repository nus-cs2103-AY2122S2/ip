package duke;

import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String k = s.nextLine();
        String[] x = k.split(" ", 2);
        System.out.println(x.length);
        for (String i : x){
            System.out.println(i);
        }
    }
}
