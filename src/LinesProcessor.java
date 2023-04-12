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
                    switch (s) {
                        case "print" -> OutputWriter.write("SPLAY TREE:\n" + tree.asTree());
                        case "clear" -> {
                            OutputWriter.write("Removed all elements from the tree");
                            tree.clear();
                        }
                        case "elements" -> OutputWriter.write(tree.print());
                        default -> currentCommand = s;
                    }
                } else {
                    try {
                        Double d = Double.parseDouble(s);
                        switch (currentCommand) {
                            case "add", "insert" -> {
                                tree.add(d);
                                OutputWriter.write("Added " + d);
                            }
                            case "find", "get" -> OutputWriter.write(tree.find(d) ? "Found " + s + " in the tree" :
                                    "Did not find " + s + " in the tree");
                            case "remove", "delete" -> {
                                tree.remove(d);
                                OutputWriter.write("Removed " + d);
                            }
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
        return str.equals("add") || str.equals("remove") || str.equals("find") ||
                str.equals("print") || str.equals("clear") || str.equals("elements") || str.equals("get") ||
                str.equals("insert") || str.equals("delete");
    }
}
