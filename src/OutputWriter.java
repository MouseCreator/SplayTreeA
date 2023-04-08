import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас для виводу у текстовий файл
 */
public class OutputWriter {
    private static final String outputFile = "data/output.txt"; //вивідний файл

    /**
     * Запис у файл для виведення
     * @param message - повідомлення
     */
    public static void write(String message) {
        writeTo(message);
    }

    private static void writeTo(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OutputWriter.outputFile, true));
            writer.write(message); //записати повідомлення
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Оновлення файлів
     */
    public static void refresh() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
