package Classes.Subjects;

import Classes.Student.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Subject superclass for all subjects to inherit from */
public abstract class  Subject {
    // name of the subject
    String subject;

    //getters
    /** Get the name of the subject */
    public String getSubject() {
        return subject;
    }

    //setters
    /** Set the name of the subject */
    public void setSubject(String subject) {
         this.subject = subject;
    }


    //re-ordering methods

    // descending order
    /** Organise the provided students into descending order (by final result) */
    public List<Student> returnStudentsInResultDescendingOrder(List<? extends Student> studentList){
        if(studentList==null){
            return null;
        }
        Student.StudentDescendingFinalResultComparator finalResultComparator = new Student.StudentDescendingFinalResultComparator();
        Collections.sort(studentList, finalResultComparator);
        List<Student> exportList = new ArrayList<>();
        for(Student student:studentList){
            exportList.add(student);
        }
        return exportList;

    }

    // alphabetical order
    /** Organise the provided students into Alphabetical order (by surname) */
    public List<Student> returnStudentsInAlphabeticalOrder(List<? extends Student> studentList){
        if(studentList==null){
            return null;
        }
        Student.StudentSurnameAlphabeticalOrderComparator alphabeticalSurnameComparator = new Student.StudentSurnameAlphabeticalOrderComparator();
        Collections.sort(studentList, alphabeticalSurnameComparator);
        List<Student> exportList = new ArrayList<>();
        for(Student student:studentList){
            exportList.add(student);
        }
        return exportList;
    }



    @Override
    public String toString() {
        return "Subject{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
