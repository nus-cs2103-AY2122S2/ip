package main.java.duke.ui;

import main.java.duke.responses.Response;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public String getNextLine() {   
        return sc.nextLine();
    }
    
    public void callResponse(Response response) {
        response.callback();
    }
      
    
}
