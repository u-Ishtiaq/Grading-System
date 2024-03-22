package Classes.DataHandlers;


import Classes.Student.GeographyStudent;
import Classes.Student.HistoryStudent;
import Classes.Student.MathsStudent;
import Classes.Student.Student;
import Classes.Subjects.Geography;
import Classes.Subjects.History;
import Classes.Subjects.Maths;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/** Processes the data from the csv file: creates student objects and subjects to hold them.
 * Also filters data for output */
public class DataProcessor {


    //Subject classes, keep track of students within the subject
    private History history = new History();
    private Maths maths = new Maths();
    private Geography geography = new Geography();

    //getters
    /** Returns the history subject */
    public History getHistory() {

        return history;
    }

    /** Returns the geography subject */
    public Geography getGeography() {

        return geography;
    }
    /** Returns the maths subject */
    public Maths getMaths() {
        return maths;
    }

    //setters
    /** sets the history subject */
    public void setHistory(History history) {
        this.history = history;
    }
    /** sets the maths subject */
    public void setMaths(Maths maths) {
        this.maths = maths;
    }
    /** sets the geography subject */
    public void setGeography(Geography geography) {
        this.geography = geography;
    }



    /** Processes the data from the csv file: creates students and returns their data */
    public  ArrayList<List<List<String>>> getFilteredStudentData(String filepath){
        //using bufferedReader because most efficient way to read file

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            //1 dimensional array for the data
            ArrayList<List<List<String>>> filteredStudentData = new ArrayList<>();

            //while there is a new line in the csv, split the line at the comma
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null){

                //data manipulation(separation)
                String unfilteredStudentRecord = currentLine;

                //remove unnecessary spaces
                String filteredStudentRecord = unfilteredStudentRecord.replaceAll("\\s", "");

                //split data into a list
                List<String> commaSplitStudentRecord = Arrays.asList(filteredStudentRecord.split(","));

                //split list into two parts, metadata and student marks
                List<String> studentMetaData = commaSplitStudentRecord.subList(0,4);
                List<String> studentMarks = commaSplitStudentRecord.subList(4,commaSplitStudentRecord.size());

                //create array lists

                List<List<String>> filteredRecordData = new ArrayList<>();

                filteredRecordData.add(studentMetaData);
                filteredRecordData.add(studentMarks);

                //place the lists inside the data-slot
               filteredStudentData.add(filteredRecordData);


            }
            //finished with the file, close the stream
            bufferedReader.close();
            //return relevant data
            if(!filteredStudentData.isEmpty()){
                return  filteredStudentData;
            }
            else{
                //blank file
                return null;
            }

        }

        catch (FileNotFoundException exception){
            //file error

            return  null;

        } catch (IOException e) {
            return null;
        }

    }

    /** places the student data returned from the getFilteredStudentData method, into subjects */
    public boolean processData(List<List<List<String>>> filteredStudentData){
        boolean processedData = false;
        //loop through each filtered data record if the filtered student data exists

        if (filteredStudentData != null) {

            processedData = true;
            for (int recordIndex = 0; recordIndex < filteredStudentData.size(); recordIndex++) {

                List<List<String>> dataRecords = filteredStudentData.get(recordIndex);
                List<String> metaData = dataRecords.get(0);
                List<String> marksList = dataRecords.get(1);
                int[] marks = new int[marksList.size()];

                //cast list array to store in the student subclasses
                for(int markListIndex=0;markListIndex<marksList.size();markListIndex++){
                    marks[markListIndex] = Integer.valueOf(marksList.get(markListIndex));
                }

                //assigning each piece of data to easy to understand values
                String year = metaData.get(0), subjectName = metaData.get(1), lastName = metaData.get(2),
                        firstName = metaData.get(3);

                //Create the relevant student object for the correct subject and append it to the subject's list of students

                if(subjectName.equals("History")){
                    HistoryStudent student = new HistoryStudent(lastName,firstName,year,marks);
                    history.addStudent(student);
                }
                else if(subjectName.equals("Maths")){

                    MathsStudent student = new MathsStudent(lastName,firstName,year,marks);
                    maths.addStudent(student);

                }
                else if(subjectName.equals("Geography")) {

                    GeographyStudent student = new GeographyStudent(lastName,firstName,year,marks);
                    geography.addStudent(student);
                }

            }

        }

        return processedData;

    }

    //polymorphic filtering
    /** Returns the specified data that satisfies the parameters for the inputted subject (first parameter) */
    public List<Student> filterSubjectData(History chosenSubject, int subjectYear,
                                        boolean flagFailedModuleStudents, boolean resultsInDescendingOrder){
        List<HistoryStudent> dataList;
        List<Student> outputList;

        // filter the year asked for
        if (subjectYear > 0) {
            dataList = chosenSubject.filterStudentsFromYear(subjectYear);
        } else {
            dataList = chosenSubject.getStudents();
        }

        //flag only failed students if requested
        if (flagFailedModuleStudents) {
            dataList = chosenSubject.filterFailedStudents(dataList);
        }

        //put data in the requested order
        if (resultsInDescendingOrder) {
            outputList = chosenSubject.returnStudentsInResultDescendingOrder(dataList);
        } else {
            outputList = chosenSubject.returnStudentsInAlphabeticalOrder(dataList);
        }

        return outputList;

    }
    /** Returns the specified data that satisfies the parameters for the inputted subject (first parameter) */
    public List<Student> filterSubjectData(Maths chosenSubject, int subjectYear,
                                        boolean flagFailedModuleStudents, boolean resultsInDescendingOrder){
        List<MathsStudent> dataList;
        List<Student> outputList;

        // filter the year asked for
        if (subjectYear > 0) {
            dataList = chosenSubject.filterStudentsFromYear(subjectYear);
        } else {
            dataList = chosenSubject.getStudents();
        }

        //flag only failed students if requested
        if (flagFailedModuleStudents) {
            dataList = chosenSubject.filterFailedStudents(dataList);
        }

        //put data in the requested order
        if (resultsInDescendingOrder) {
            outputList = chosenSubject.returnStudentsInResultDescendingOrder(dataList);
        } else {
            outputList = chosenSubject.returnStudentsInAlphabeticalOrder(dataList);
        }

        return outputList;

    }
    /** Returns the specified data that satisfies the parameters for the inputted subject (first parameter) */
    public List<Student> filterSubjectData(Geography chosenSubject, int subjectYear,
                                        boolean flagFailedModuleStudents, boolean resultsInDescendingOrder){
        List<GeographyStudent> dataList;
        List<Student> outputList;

        // filter the year asked for
        if (subjectYear > 0) {
            dataList = chosenSubject.filterStudentsFromYear(subjectYear);
        } else {
            dataList = chosenSubject.getStudents();
        }

        //flag only failed students if requested
        if (flagFailedModuleStudents) {
            dataList = chosenSubject.filterFailedStudents(dataList);
        }

        //put data in the requested order
        if (resultsInDescendingOrder) {
            outputList = chosenSubject.returnStudentsInResultDescendingOrder(dataList);
        } else {
            outputList = chosenSubject.returnStudentsInAlphabeticalOrder(dataList);
        }

        return outputList;

    }

    @Override
    public String toString() {
        return "DataProcessor{" +
                "history=" + history +
                ", maths=" + maths +
                ", geography=" + geography +
                '}';
    }
}
