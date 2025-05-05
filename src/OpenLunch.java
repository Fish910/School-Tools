public class OpenLunch
{
    private static Day d;
    public static void main(String[] args)
    {
        d = new Day(false);
        Block b = d.getLunchBlock();

        d.setLunch();

        String lunch = d.getLunchItem().substring(0, d.getLunchItem().indexOf(","));
        lunch += "\n                      WITH";
        char[] charArray = d.getLunchItem().substring(d.getLunchItem().indexOf(",")).toCharArray();
        for (char a : charArray) {
            if (a == ',') {
                lunch += "\n-";
            }
            else {
                lunch += a;
            }
        }
                    
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
}
