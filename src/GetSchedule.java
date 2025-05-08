import java.time.LocalDate;
import java.util.Arrays;

public class GetSchedule 
{
    public static void main(String[] args) 
    {
        String[] input = Notify.showInputPopup("GetSchedule", "Enter your desired month and day:", 2);

        if (input == null) {
            System.exit(0);
        }
        else if (input[0].isEmpty() || input[1].isEmpty()) {
            Notify.showPopup("Error", "No date entered.");
            System.exit(0);
        }

        LocalDate date = LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        Day d = new Day(date);
        Block b = d.getLunchBlock();
        Block[] blocks = d.getBlocks();
        String month = date.getMonth().toString().substring(0, 1).toUpperCase() + date.getMonth().toString().substring(1).toLowerCase();
        String day = date.getDayOfWeek().toString().substring(0, 1).toUpperCase() + date.getDayOfWeek().toString().substring(1).toLowerCase();

        try {
            Notify.showPopup(
                "GetSchedule", 
                day + ", " + month + " " + date.getDayOfMonth() + " is a day " + d.getDay() + ": " + "\n" +
                String.join(" ", Arrays.stream(blocks).map(Block::toString).toArray(String[]::new)) + "\n" +
                "You have lunch #" + b.getLunch() + " from " + d.getLunchTime() + "\n" +
                "You have break #" + blocks[1].getBreak() + " from " + d.getBreak()
            );
        } catch (NullPointerException e) {
            Notify.showPopup("GetSchedule error", "No schedule was found for " + month + " " + date.getDayOfMonth());
        }

        System.exit(0);
    }
}
