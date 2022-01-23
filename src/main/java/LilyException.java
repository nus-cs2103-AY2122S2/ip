public class LilyException extends Exception {
    // https://nus-cs2030s.github.io/2021-s2/19-exception.html
    public LilyException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
      return "error message bro";
    } 
}
