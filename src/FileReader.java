import java.util.ArrayList;
import java.util.List;

public class FileReader
{
    public static FileHandler reader = new FileHandler("C:\\Users\\LiamK\\Documents\\School Tools 1.0\\src\\ClassStorage.txt");

    /** Returns a boolean that tells you whether the current date is stored.
     * 
     * @param currentMonth is a string in the format "MON #", the first three letters of the month capitalized.
     * @return 
     */
    public static boolean isStored(String currentDate)
    {
        List<String> data = reader.readFromFile();

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

    public static ArrayList<String> getDates()
    {
        ArrayList<String> dates = new ArrayList<String>();
        List<String> data = reader.readFromFile();
        for (String item : data)
        {
            dates.add(item);
        }
        return dates;
    }

    public static void writeDates(ArrayList<String> dates)
    {
        for (String date : dates)
        {
            reader.writeToFile(date);
        }
    }
}
