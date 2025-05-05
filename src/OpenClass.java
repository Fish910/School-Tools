import java.io.IOException;
import java.net.URISyntaxException;

public class OpenClass 
{
    private static Day d;
    public static void main(String[] args)
    {
        d = new Day(false);
        Block b = d.getCurrentBlock();

        try {
            String url = b.getURL();
            Notify.openWebsite(url);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Failed to open the website: " + e.getMessage());
        } catch (NullPointerException e) {
            Notify.showPopup("OpenClass error ", "No class was found to open.");
        }
        System.exit(0);
    }
}
