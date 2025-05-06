import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Day 
{
    private ArrayList<String> schedule; // List that will contain the schedule times for the day, depending on whether or not it's a half day.
    private String lunchTime;
    private String lunchItem;
    private String rest;
    private int day;
    private Block[] blocks;
    private FileHandler fh = new FileHandler("src\\storage\\ClassStorage.txt"); 

    public Day()
    {
        schedule = new ArrayList<>();
        day = getDay();

        // Set the times for the schedule
            schedule.add("07:25-08:34");
            schedule.add("08:37-09:58");
            schedule.add("10:01-11:05");
            schedule.add("11:08-12:53");
            schedule.add("12:56-14:00");

        // Set the blocks based on the given day it is
        blocks = new Block[5];
        Block[] b = {
            new Block("A"),
            new Block("B"),
            new Block("C"),
            new Block("D"),
            new Block("E"),
            new Block("F"),
            new Block("G"),
        };
        
        try {
            int[] dayStarts = {0, 5, 3, 1, 6, 4, 2}; 
            int index = dayStarts[day - 1]; 

            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = b[(index + i) % b.length];
            }

            // Get break and lunch
            if (blocks[1].getBreak() == 1) rest = "8:34-8:51";
            else if (blocks[1].getBreak() == 2) rest = "9:41-9:58";

            if (blocks[3].getLunch() == 1) lunchTime = "11:08-11:33";
            else if (blocks[3].getLunch() == 2) lunchTime = "11:48-12:13";
            else if (blocks[3].getLunch() == 3) lunchTime = "12:28-12:53";
        } catch (Exception e) {
            System.out.println("No School today!");
        }
    }

    public Block getCurrentBlock()
    {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Define the desired format

        for (String range : schedule)
        {
            String[] times = range.split("-");
            LocalTime startTime = LocalTime.parse(times[0], formatter);
            LocalTime endTime = LocalTime.parse(times[1], formatter);  

            if (!currentTime.isBefore(startTime) && !currentTime.isAfter(endTime)) // Has to be done to include the start and end time
            {
                return blocks[schedule.indexOf(range)];
            }
        }
        return null;
    }

    public Block getLunchBlock()
    {
        return blocks[3];
    }

    public int getDay()
    {
        int m = LocalDate.now().getMonthValue();
        if ((m <= 12) && (m >= 9)) m -= 8; // Set Sep-Dec to 1-4
        else if ((m >= 1) && (m <= 6)) m += 4; // Set Jan-Jun 5-10
        String url = "0";
        switch(m) {
            case 1: // September
                url = "";
                break;
            case 2: // October
                url = "";
                break;
            case 3: // November
                url = "";
                break;
            case 4: // December
                url = "";
                break;
            case 5: // January
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-01-31&start_date=2025-01-01";
                break;
            case 6: // February
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-02-28&start_date=2025-02-01";
                break;
            case 7: // March
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-03-31&start_date=2025-03-01";
                break;
            case 8: // April
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-04-30&start_date=2025-04-01";
                break;
            case 9: // May
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-05-31&start_date=2025-05-01";
                break;
            case 10: // June
                url = "https://www.mursd.org/o/nipmuc-regional-high-school/events?end_date=2025-06-30&start_date=2025-06-01";
                break;
        }

        LocalDate date = LocalDate.now();
        String fixedDate = date.getMonth().toString();
        fixedDate = fixedDate.substring(0,3);
        fixedDate += " ";
        fixedDate += date.getDayOfMonth();

        ArrayList<String> dayList; // Declare the variable before the if-else
        
        if (fh.isStored(fixedDate)) { // Assuming FileHandler is a custom class
            dayList = fh.getDates();  // Read from file
        } else {
            dayList = WebScraper.getSchedule(url); // Fetch from the web
            fh.writeDates(dayList);
        }

        int day = -1;
        for (String d : dayList)
        {
            String cutDate = d.substring(0, d.indexOf(":"));
            if (cutDate.equals(fixedDate)) day = Integer.parseInt(d.substring(d.indexOf(":") + 1));
        }
        return day;
        
    }

    public void setLunch()
    {
        //System.out.println(menuList);
        String currentMonth = LocalDate.now().getMonth().toString();

        LocalDate date = LocalDate.now();
        String fixedDate = date.getMonth().toString();
        fixedDate += "L";
        fixedDate += " ";
        fixedDate += date.getDayOfMonth();

        ArrayList<String> menuList;

        if (fh.isStored(fixedDate)) { // Assuming FileHandler is a custom class
            menuList = fh.getDates();  // Read from file
        } else {
            menuList = MenuScraper.getMenu(currentMonth); // Fetch from the web
            fh.writeDates(menuList);
        }

        //Get left side of : in the menu list and get fixedDate and compare to get a match
        lunchItem = "[No lunch found.]";
        for (String menu : menuList)
        {
            String m = menu.substring(0, menu.indexOf(":"));
            if (m.equals(fixedDate))
            {
                lunchItem = menu.substring(menu.indexOf(":") + 1);
            }
        }
    }
    public String getLunchTime()
    {
        return lunchTime;
    }
    public String getLunchItem()
    {
        return lunchItem;
    }
    public String getBreak()
    {
        return rest;
    }
    public ArrayList<String> getSchedule()
    {
        return schedule;
    }

    public String getDate()
    {
        return LocalDate.now().toString();
    }
    public Block[] getBlocks()
    {
        return blocks;
    }
}   
