public class Subject
{
    private String name;
    private int floor;
    private String wing;

    public Subject(String name, int floor, String wing)
    {
        this.name = name;
        this.floor = floor;
        this.wing = wing;
    }

    public String getName()
    {
        return name;
    }

    public int getFloor()
    {
        return floor;
    }

    public int getBreak()
    {
        if (wing.equals("green")) return 1;
        else if (wing.equals("blue")) return 2;
        else return -1;
    }

    public String toString()
    {
        return name;
    }
}
