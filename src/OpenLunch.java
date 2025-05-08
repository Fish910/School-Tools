import java.time.LocalDate;

public class OpenLunch
{
    private static Day d;
    public static void main(String[] args)
    {
        d = new Day(LocalDate.now());
        Block b = d.getLunchBlock();

        d.setLunch();

        String lunch = formatLunch(d.getLunchItem());
                    
        // code to make the popup
        try {
            Notify.showPopup(
                "OpenLunch",
                "You have lunch #" + b.getLunch() + " from " + d.getLunchTime() + "\n" + "Lunch today is: " + lunch
            );
        } catch (NullPointerException e) {
            Notify.showPopup(
                "OpenLunch error",
                "There is no lunch today."
            );
        }
        System.exit(0);
    }

    public static String formatLunch(String lunch) {
        String formattedLunch = lunch.substring(0, lunch.indexOf(","));
        formattedLunch += "\n                      WITH";
        char[] charArray = lunch.substring(lunch.indexOf(",")).toCharArray();
        for (char a : charArray) {
            if (a == ',') {
                formattedLunch += "\n-";
            } else {
                formattedLunch += a;
            }
        }
        return formattedLunch;
    }
}
