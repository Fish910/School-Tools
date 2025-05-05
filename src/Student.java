import java.util.ArrayList;

public class Student 
{
    String name;
    Subject[] classes = new Subject[7];

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
        Subject[] aClasses = a.classes;
        Subject[] bClasses = b.classes;

        ArrayList<Subject> sameClasses = new ArrayList<Subject>();
        int start = blocks[0].getPosition();

        // Ignore classes that arent today.
        if (start < 2) {
            aClasses[start+5] = null;
            aClasses[start+6] = null;
            bClasses[start+5] = null;
            bClasses[start+6] = null;
        } else {
            aClasses[start-2] = null;
            aClasses[start-1] = null;
            bClasses[start-2] = null;
            bClasses[start-1] = null;
        }

        for (Subject x : aClasses) for (Subject y : bClasses) if (x == y && !(x == null)) sameClasses.add(x);

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
