public class OpenLunch
{
    private static Day d;
    public static void main(String[] args)
    {
        d = new Day(false);
        Block b = d.getLunchBlock();

        d.setLunch();

        // code to make the popup
        try {
            Notify.showTrayMessage("You have lunch #" + b.getLunch() + " from " + d.getLunchTime() + "\nLunch today is " + d.getLunchItem());
        } catch (NullPointerException e) {
            System.out.println("There is no lunch today.");
        }
        System.exit(0);
    }
}
