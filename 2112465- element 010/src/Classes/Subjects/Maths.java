package Classes.Subjects;

import Classes.Student.MathsStudent;


import java.util.ArrayList;
import java.util.List;

/** Type of subject: used to hold the students who take maths */
public class Maths extends Subject {

    //used to store the students within a subject
    private List<MathsStudent> students = new ArrayList<>();

    // on instantiation set the subject to the relevant one
    public Maths(){
        this.subject = "Maths";
    }

    //methods

    //getters

    /** Returns a list of all the maths students */
    public List<MathsStudent> getStudents() {
        return students;
    }

    //setters

    /** Sets a list of maths students */
    public void setStudents(List<MathsStudent> students){
        this.students = students;
    }


    //custom methods
    /** Adds a single student to maths */
    public void addStudent(MathsStudent student) {
        this.students.add(student);
    }

    /** Returns students who graduated maths in the provided year */
    public List<MathsStudent> filterStudentsFromYear(int year) {

        //holder to place the students from a certain year in
        List<MathsStudent> filteredStudents = new ArrayList<>();

        //loop through each student and if it is within the year then add it to the filtered list
        for(MathsStudent student : this.students){
            if(student.getStudentYear() == year){
                filteredStudents.add(student);
            }
        }

        //return the filtered list if it has items in, otherwise return null
        if(filteredStudents.size() > 0){
            return filteredStudents;
        }

        return null;

    }

    /** Returns students who failed a single module in maths */
    public List<MathsStudent> filterFailedStudents(List <MathsStudent> studentList){
        //used to store the filtered data
        List<MathsStudent> filteredList = new ArrayList<>();

        //loop through each student looking for a failed module
        for (MathsStudent student : studentList){
            boolean failedAModule = false;

            //loop through each mark to see if the student failed a module
            for(int mark : student.getMarks()){
                if(mark < 40){
                    //if they did the flag the earlier boolean and break the loop since they failed
                    failedAModule = true;
                    break;
                }
            }

            //if the student failed a module add them to the filtered list since they fit criteria
            if(failedAModule){
                filteredList.add(student);
            }


        }

        //if there are objects in the filtered list then return the list, otherwise null for easy usage
        if(filteredList.size() > 0){
            return  filteredList;
        }
        return null;
    }


    @Override
    public String toString() {
        return getSubject()+"{" +
                "students=" + students +
                '}';
    }
}
