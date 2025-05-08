import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    public static String[] showInputPopup(String title, String message, int numInputs) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5)); // Add spacing between components

        // Add the message at the top
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.LEFT); // Align the message to the left
        panel.add(messageLabel, BorderLayout.NORTH);

        // Create a panel for the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, numInputs, 5, 5)); // Arrange inputs in a grid with spacing

        JTextField[] textFields = new JTextField[numInputs];
        // Add empty labels to the left and right of the input fields to shrink them
        panel.add(new JLabel("      "), BorderLayout.WEST); // Empty label on the left
        panel.add(new JLabel("           "), BorderLayout.EAST); // Empty label on the right
        for (int i = 0; i < numInputs; i++) {
            JTextField textField = new JTextField(); 
            textFields[i] = textField;
            inputPanel.add(textField); // Add text fields to the same line
        }

        panel.add(inputPanel, BorderLayout.CENTER); // Add the input panel below the message

        int result = JOptionPane.showConfirmDialog(
            null, 
            panel, 
            title, 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String[] inputs = new String[numInputs];
            for (int i = 0; i < numInputs; i++) {
                inputs[i] = textFields[i].getText();
            }
            return inputs;
        } else {
            return null; // Return null if the user cancels
        }
    }
}
