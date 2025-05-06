import java.util.ArrayList;

public class Student 
{
    private String name;
    private Subject[] classes = new Subject[7];

    public Student(String name, Subject a, Subject b, Subject c, Subject d, Subject e, Subject f, Subject g)
    {
        this.name = name;
        classes[0] = a;
        classes[1] = b;
        classes[2] = c;
        classes[3] = d;
        classes[4] = e;
        classes[5] = f;
        classes[6] = g;
    }

    public String getName()
    {
        return name;
    }

    public Subject[] getClasses()
    {
        return classes;
    }

    public static ArrayList<Subject> compareSchedules(Student a, Student b, Block[] blocks)
    {
        Subject[] aClasses = a.getClasses();
        Subject[] bClasses = b.getClasses();

        ArrayList<Subject> sameClasses = new ArrayList<>();

        // Get the positions for today from the Block array
        ArrayList<Integer> todaysPositions = new ArrayList<>();
        for (Block block : blocks) {
            todaysPositions.add(block.getPosition());
        }

        // Compare only classes that are in today's positions
        for (int pos : todaysPositions) {
            Subject subjectA = aClasses[pos];
            Subject subjectB = bClasses[pos];
            if (subjectA == subjectB && subjectA != null) {
                sameClasses.add(subjectA);
            }
        }

        return sameClasses;
    }


    public static ArrayList<Student> compareBreak(Student me, Student[] students, Block[] blocks)
    {
        int second = blocks[1].getPosition();
        ArrayList<Student> breakMates = new ArrayList<Student>();

        for (Student s : students)
        {
            if (s.getClasses()[second].getBreak() == me.getClasses()[second].getBreak()) breakMates.add(s);
        }
        return breakMates;
    }

    public String toString()
    {
        return name;
    }
}
