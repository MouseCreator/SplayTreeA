import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Клас для зчитування з вхідного файлу
 */
public class InputReader {
    public static ArrayList<String> readFile() throws IOException {
        String inputFile = "data/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)); //відкрити файл
        ArrayList<String> currentArray = new ArrayList<>();
        String line;
        while (true) {
            line = reader.readLine(); //зчитуємо файл по рядках
            if (line == null)
                break;
            line = line.trim().replaceAll(" +", " ");
            line = line.replaceAll("\t", " ");
            String[] strings = line.split(" "); //розбиваємо рядок на окремі значення
            for (String s : strings) {
                if (isNotReadable(line))
                    continue;
                try {
                    currentArray.add(s);
                } catch (Exception e) {
                    System.err.println("Cannot parse value " + s + " to double!"); //додаємо число в масив
                }
            }
        }
        return currentArray;
    }

    private static boolean isNotReadable(String line) {
        return line.isEmpty() || line.startsWith("#");
    }
}
