import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DNAtree {
    static Tree mytree;
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        mytree = new Tree();
        Parser parse = new Parser(new File(filename), mytree);
        parse.execute();
    }
}


