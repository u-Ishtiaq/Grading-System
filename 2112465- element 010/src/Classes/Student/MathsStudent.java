package Classes.Student;


import java.util.Arrays;

/** Type of student */
public class MathsStudent extends Student {
    private int[] marks = new int[12];

    public MathsStudent(String lastName, String firstName, String studentYear,int[] marks) {
        super(lastName, firstName, studentYear);
        this.marks = marks;

        //add all marks together
        int markCount = 0;
        for(int mark : marks){
            markCount+=mark;
        }

        //then divide by the size of the array for the mean
        float mean = (float) markCount /marks.length;
        // set the final result value
        this.setFinalResult((int) Math.ceil(mean));
    }


    /** Get the student marks for each individual module */
    public int[] getMarks() {
        return marks;
    }

    /** Set the student marks for the individual modules */
    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "MathsStudent: " + getFirstName() + " " + getLastName() + ": Overall: " + getFinalResult() +
                " {" +
                "marks=" + Arrays.toString(marks) +
                '}';
    }
}
