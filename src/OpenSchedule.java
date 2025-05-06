import java.util.ArrayList;

public class OpenSchedule 
{
    private static Subject apush = new Subject("APUSH", 3, "blue");
    private static Subject csa = new Subject("AP CSA", 3, "green");
    private static Subject lang = new Subject("AP Lang", 2, "green");
    private static Subject spanish3 = new Subject("Spanish 3", 2, "blue");
    private static Subject windEnsemble = new Subject("Wind Ensemble", 1, "green");
    private static Subject physics = new Subject("AP Physics", 1, "green");
    private static Subject precalcA = new Subject("Pre Calc/Calc A", 3, "green");
    private static Subject bio = new Subject("AP Bio", 1, "green");
    private static Subject forensics = new Subject("AP Forensics", 2, "blue");
    private static Subject psych = new Subject("AP Psych", 3, "blue");
    private static Subject apSpanish = new Subject("AP Spanish", 2, "blue");
    private static Subject calcBC = new Subject("AP Calc BC", 3, "green");
    private static Subject ush2 = new Subject("US History 2", 3, "blue");
    private static Subject placeHolder = new Subject("", 0, "");


    public static void main(String[] args)
    {
        Day d = new Day(); 
        Block[] blocks = d.getBlocks();
        String message = "";

        message += ("Today is a day " + d.getDay() + ": ");
        for (Block b : blocks) message += (b + " ");
        message += "\n";        

        Student me = new Student("Liam", apush, csa, lang, spanish3, windEnsemble, physics, precalcA);
        Student[] classmates = {
            new Student("Brynn", apush, bio, lang, forensics, windEnsemble, apSpanish, precalcA),
            new Student("Tyler", psych, lang, spanish3, apush, windEnsemble, bio, precalcA),
            new Student("Talia", spanish3, csa, lang, ush2, windEnsemble, physics, calcBC),
            new Student("Wes", placeHolder, placeHolder, placeHolder, placeHolder, windEnsemble, placeHolder, precalcA)
        };

        for (Student s : classmates) message += ("You have " + Student.compareSchedules(me, s, blocks).size() + " classes with " + s.getName() + ": " + Student.compareSchedules(me, s, blocks) + "\n");
        message += "\n"; 

        ArrayList<Student> breakMates = Student.compareBreak(me, classmates, blocks);
        message += ("You have break with: ");
        for (int i = 0; i < breakMates.size(); i++) 
        {
            if (i == breakMates.size() - 1) message += ("and " + breakMates.get(i).getName() + "\n");
            else message += (breakMates.get(i).getName() + ", ");
        }

        Notify.showPopup("OpenSchedule", message);
        System.exit(0);
    }
}
