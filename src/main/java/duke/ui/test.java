package duke.ui;

import java.util.Properties;

class test {

    /*public static void main(String args[]) {
        try {
            Properties p = new Properties(System.getProperties());
            System.setProperties(p);
            System.getProperties().list(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String dir = System.getProperty("user.home") + System.getProperty("file.separator")
                + "MyProject" + System.getProperty("file.separator") + "Images";
        System.out.println("home: " + home);
        System.out.println("dir: " + dir);
    }
}