import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

public class Notify
{
    public static void openWebsite(String url) throws IOException, URISyntaxException 
    {
        if (Desktop.isDesktopSupported()) 
        {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(url));
        } 
        else 
        {
            System.out.println("Desktop is not supported.");
        }
    }

    public static void showPopup(String title, String message) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title, 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
