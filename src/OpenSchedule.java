import java.util.ArrayList;

public class OpenSchedule 
{
    private static Subject apush = new Subject("APUSH", 3, "blue");
    private static Subject csa = new Subject("AP CSA", 3, "green");
    private static Subject lang = new Subject("AP Lang", 2, "green");
    private static Subject spanish3 = new Subject("Spanish 3", 2, "blue");
    private static Subject windEnsemble = new Subject("Wind Ensemble", 1, "green");
    private static Subject physics = new Subject("AP Physics", 1, "green");
    private static Subject precalca = new Subject("Pre Calc/Calc A", 3, "green");
    private static Subject bio = new Subject("AP Bio", 1, "green");
    private static Subject forensics = new Subject("AP Forensics", 2, "blue");
    private static Subject psych = new Subject("AP Psych", 3, "blue");
    private static Subject apSpanish = new Subject("AP Spanish", 2, "blue");
    private static Subject calcBC = new Subject("AP Calc BC", 3, "green");
    private static Subject placeHolder = new Subject("", 0, "");


    public static void main(String[] args)
    {
        Day d = new Day(false); // Need to write code to determine half day
        Block[] blocks = d.getBlocks();
        System.out.print("Today is a day " + d.getDay() + ": ");
        for (Block b : blocks) System.out.print(b + " ");
        System.out.println();
        
        Student me = new Student("Liam", apush, csa, lang, spanish3, windEnsemble, physics, precalca);
        Student[] classmates = {
            new Student("Brynn", apush, bio, lang, forensics, windEnsemble, apSpanish, precalca),
            new Student("Tyler", psych, placeHolder, placeHolder, placeHolder, windEnsemble, bio, precalca),
            new Student("Talia", placeHolder, csa, lang, placeHolder, windEnsemble, physics, calcBC)
        };

        for (Student s : classmates)
        {
            System.out.println("You have " + Student.compareSchedules(me, s, blocks).size() + " classes with " + s.getName() + ": " + Student.compareSchedules(me, s, blocks));
        }
        System.out.println();

        ArrayList<Student> breakMates = Student.compareBreak(me, classmates, blocks);
        System.out.print("You have break with: ");
        for (Student s : breakMates) System.out.println(s + " ");
    }
}
