package Classes.Student;

import java.util.Comparator;

/** Abstract superclass for all the different types of students */

public abstract class Student  {
    private String lastName;
    private String firstName;
    private int finalResult;
    private int studentYear;


    //constructor
    public Student(String lastName,String firstName,String studentYear){
     this.lastName = lastName;
     this.firstName = firstName;
     this.studentYear = Integer.parseInt(studentYear);

    }

    //getters
    public int getFinalResult() {
        return finalResult;
    }

    public int getStudentYear() { return studentYear;   }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentYear(String studentYear) {
       this.studentYear = Integer.parseInt(studentYear);;
    }

    public void setFinalResult(int finalResult){
        this.finalResult = finalResult;
    }


    //re-ordering methods

    // descending order
    /** Comparator for organising the student data by their final result (in descending order) */
    public static class StudentDescendingFinalResultComparator implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            return s2.getFinalResult()-s1.getFinalResult();
        }
    }

    // alphabetical order
    /** Comparator for organising the student data by their surname (in Alphabetical order) */
    public static class StudentSurnameAlphabeticalOrderComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getLastName().compareTo(s2.getLastName());
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", finalResult=" + finalResult +
                ", studentYear=" + studentYear +
                '}';
    }
}
