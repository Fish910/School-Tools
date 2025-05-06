import java.util.ArrayList;
import java.util.List;

public class Block 
{
    private String className;
    private String wing;
    private int floor;
    private String block;
    private int position;
    private String URL;
    private FileHandler fh = new FileHandler("src\\storage\\Liam.txt"); // To be replaced with an approach that supports many users

    public Block(String letter) // Exclusive to the user
    {   
        block = letter;
        List<String> data = fh.readFromFile();  
        for (String item : data)
        {
            String[] parts = item.split(",");
            if (parts[0].equals(letter)) // data is in "letter,position,name,wing,floor,url" format
            {
                position = Integer.parseInt(parts[1]);
                className = parts[2];
                wing = parts[3];
                floor = Integer.parseInt(parts[4]);
                URL = parts[5];
            }
        }
    }
    public String getClassName()
    {
        return className;
    }
    public int getBreak()
    {
        if (wing.equals("blue")) return 2;
        else if (wing.equals("green")) return 1;
        else return -1;
    }
    public int getLunch()
    {
        return floor;
    }
    public String getLetter()
    {
        return block;
    }
    public String getURL()
    {
        return URL;
    }
    public String toString()
    {
        return block;
    }
    public int getPosition()
    {
        return position;
    }
}
