public class Block 
{
    private String className;
    private String wing;
    private int floor;
    private String block;
    private int position;
    private String URL;

    public Block(String letter) // Exclusive to the user
    {   
        block = letter;

        if (block == "A") 
        {
            position = 0;
            className = "AP US History";
            wing = "blue";
            floor = 3;
            URL = "https://classroom.google.com/c/NzA4MzQ1MDM4Njk2";
        }
        else if (block == "B")
        {
            position = 1;
            className = "AP Computer Science A";
            wing = "green";
            floor = 3;
            URL = "https://classroom.google.com/c/Njk0NzkyNTAxNzUz";
        }
        else if (block == "C")
        {
            position = 2;
            className = "AP English Language";
            wing = "green";
            floor = 2;
            URL = "https://classroom.google.com/c/NzAzMjM0ODIwODg1";
        }
        else if (block == "D")
        {
            position = 3;
            className = "Spanish 3";
            wing = "blue";
            floor = 2;
            URL = "https://classroom.google.com/c/NzA4Njg2MjAxNzY3";
        }
        else if (block == "E")
        {
            position = 4;
            className = "Wind Ensemble";
            wing = "green";
            floor = 1;
            URL = "https://classroom.google.com/c/NzA4NjE0Mzg2NTAw";
        }
        else if (block == "F")
        {
            position = 5;
            className = "AP Physics";
            wing = "green";
            floor = 1;
            URL = "https://classroom.google.com/c/NzA5NTA4NDAyNTYz";
        }
        else if (block == "G")
        {
            position = 6;
            className = "Precalc with Calc A";
            wing = "green";
            floor = 3;
            URL = "https://classroom.google.com/c/NzA4MzQzNDAwMjcz";
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
