import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private String filename;

    public FileReader(String filename) {
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

    /** Returns a boolean that tells you whether the current date is stored.
     * 
     * @param currentMonth is a string in the format "MON #", the first three letters of the month capitalized.
     * @return 
     */
    public boolean isStored(String currentDate)
    {
        List<String> data = this.readFromFile();

        if (data.isEmpty()) return false;

        for (String item : data)
        {
            //System.out.println(item);
            String storedString = item.substring(0, item.indexOf(":"));
            if (storedString.equals(currentDate)) // data is in "MON #:#" format
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getDates()
    {
        ArrayList<String> dates = new ArrayList<String>();
        List<String> data = this.readFromFile();
        for (String item : data)
        {
            dates.add(item);
        }
        return dates;
    }

    public void writeDates(ArrayList<String> dates)
    {
        for (String date : dates)
        {
            this.writeToFile(date);
        }
    }
}
