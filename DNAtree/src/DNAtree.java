import java.io.File;
import java.util.Scanner;


public class DNAtree {
    static Tree tree;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String filename = args[0];
        // Pass the function a full filepath
        beginParsing(filename);
        // call the parsing function
        
    }
    public static void beginParsing(String filename) {
        tree = new Tree();

        try {           
            Scanner sc = new Scanner(new File(filename));

            while (sc.hasNextLine()) {
                int numberOfOperation = 0;
                int numberOfSequence = 0;
                String operation = "";
                String sequence = "";
                String line = sc.nextLine().trim();
                
                if (line.equals("")) {
                    continue;
                }
                String[] strs = line.split(" ");

                for (String s : strs) {
                    // blanket                 
                    if (s.equals("")) {
                        continue;
                    }
                    s = s.toUpperCase();
                    if (s.equals("INSERT") || s.equals("REMOVE") || s.equals("PRINT") || s.equals("SEARCH")) {
                        operation = s;
                        numberOfOperation++;
                    }
                    else {
                        sequence= s;
                        numberOfSequence++;
                    }
                }
                
                if (numberOfSequence > 2 || numberOfOperation > 2) {
                    continue;
                }
//                System.out.println(operation +" " + sequence);
                
                if (operation.equals("INSERT")) {
                    
                    int result = tree.insert(sequence);
                    if (result < 0) {
                        System.out.println("Sequence " + sequence + " already exists");
                    }
                    else {
                        System.out.println("Sequence " + sequence + " inserted at level " + result);
                    }
                }
                else if (operation.equals("REMOVE")) {
                    
                    boolean result = tree.remove(sequence);
                    if (result) {
                        System.out.println("Sequence " + sequence + " removed");
                    }
                    else {
                        System.out.println("Sequence " + sequence + " does not exist");
                    }
                }
                else if (operation.equals("PRINT")) {
                    boolean stats = false;
                    boolean lengths = false;
                    if (sequence.equals("STATS")) {
                        stats = true;
                    }
                    if (sequence.equals("LENGTHS")) {
                        lengths = true;
                    }
                    System.out.println("tree dump:");
                    System.out.println(tree.print(lengths, stats).trim());
                }
                else if (operation.equals("SEARCH")) {
                    System.out.println(tree.search(sequence).trim());
                }
                else {
                    continue;
                }

            }

            sc.close();
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

}


