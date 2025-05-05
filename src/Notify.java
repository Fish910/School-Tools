import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public static void showTrayMessage(String message) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported.");
            return;
        }

        // Create a tray icon
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\LiamK\\Documents\\School Tools 1.0\\fork-and-knife.ico"); // Use an icon image here
        TrayIcon trayIcon = new TrayIcon(image, "ClassPicker");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("ClassPicker Notification");

        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("ClassPicker Notification", message, TrayIcon.MessageType.NONE);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added: " + e.getMessage());
        }
    }
    /* 
    public static int retrieveSchedule()
    {
        String file = "schedule.json";
    }
    
    public static String retrieveLunch()
    {
        String file = "schedule.json";
    } */
}
