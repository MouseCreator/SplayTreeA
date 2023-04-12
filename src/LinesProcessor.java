import java.io.IOException;
import java.util.ArrayList;

public class LinesProcessor {
    public void process() {
        OutputWriter.refresh();
        SplayTree<Double> tree = new SplayTree<>(new DoublesComparator());
        try {
            ArrayList<String> strings = InputReader.readFile();
            String currentCommand = "add";
            for (String s : strings) {
                if (isCommand(s)) {
                    if (s.equals("print")) {
                        OutputWriter.write(tree.print());
                    } else if (s.equals("clear")) {
                        tree.clear();
                    } else {
                        currentCommand = s;
                    }
                } else {
                    try {
                        Double d = Double.parseDouble(s);
                        switch (currentCommand) {
                            case "add" -> tree.add(d);
                            case "find" -> OutputWriter.write(tree.find(d) ? "Found " + s + " in the tree" :
                                    "Did not find " + s + " in the tree");
                            case "remove" -> tree.remove(d);
                        }
                    } catch(NumberFormatException e){
                        OutputWriter.write(String.format("Unable to parse %s to double", s));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isCommand(String str) {
        return str.equals("add") || str.equals("remove") || str.equals("find") || str.equals("print") || str.equals("clear");
    }
}
