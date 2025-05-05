public class OpenLunch
{
    private static Day d;
    public static void main(String[] args)
    {
        d = new Day(false);
        Block b = d.getLunchBlock();

        d.setLunch();

        System.out.println(d.getLunchItem());
        String lunch = "\n";
        for (char a : d.getLunchItem().toCharArray()) {
            if (a == ',') {
                lunch += "\n";
            }
            else {
                lunch += a;
            }
        }
                    
        // code to make the popup
        try {
            Notify.showPopup(
                "You have lunch #" + b.getLunch() + " from " + d.getLunchTime() + "\n" + "Lunch today is: " + lunch);
        } catch (NullPointerException e) {
            System.out.println("There is no lunch today.");
        }
        System.exit(0);
    }
}
