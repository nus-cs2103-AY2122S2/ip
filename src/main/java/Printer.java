import java.util.Arrays;

class Printer {
    public void print(String ... args) {
        System.out.println(String.format("    %s", "=========================================="));
        Arrays.asList(args).forEach((x) -> { System.out.println(String.format("    %s", x.toString())); } );
        System.out.println(String.format("    %s", "=========================================="));
    }
}