import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public void writeToFile(String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(content);
            writer.newLine();
            //System.out.println("Data written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void clearFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.TRUNCATE_EXISTING)) {
            System.out.println("File cleared.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("src/Storage.txt");

        fileHandler.writeToFile("Hello, World!");
        fileHandler.writeToFile("Java File Handling Example");

        List<String> contents = fileHandler.readFromFile();
        System.out.println("File Contents:");
        for (String line : contents) {
            System.out.println(line);
        }

        // Uncomment to clear file
        // fileHandler.clearFile();
    }
}
