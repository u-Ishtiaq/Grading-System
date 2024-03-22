package com.company;

import Classes.DataHandlers.DataProcessor;
import Classes.FrontEnd.GuiController;

import Classes.Student.Student;


import java.util.*;

/**
 * Main programme logic
 */
public class Main {
    /**
     * Main programme logic
     */
    public static void main(String[] args) {
        //boolean to dictate the running of the main programme
        boolean runningProgramme = true;

        //class used to process and read the csv
        DataProcessor dataProcessor = new DataProcessor();
        //prepare the GUI for the user
        GuiController commandLineGui = new GuiController();

        //csv file path for the dataProcessor to find the right file
        String filepath = "src/Resources/Data_File_For_Assignment.csv";

        //get the student data from designated file path
        List<List<List<String>>> filteredStudentData = dataProcessor.getFilteredStudentData(filepath);
        //place the student data into subjects
        boolean processedData = dataProcessor.processData(filteredStudentData);


        //if there is student data and subjects to process further then trigger the user menu
        // else print an error with the data
        if (processedData) {

            while (runningProgramme) {
                //trigger the user to select a subject filter
                String subjectName = commandLineGui.selectASubject();
                //trigger the user to select a year filter
                int subjectYear = commandLineGui.selectAYear();
                //ask the user to decide if they want to only show users who failed a module
                boolean flagFailedModuleStudents = commandLineGui.flagStudentsWhoFailedAModule();
                //ask the user to decide if they want to display the results in descending or alphabetical order
                boolean resultsInDescendingOrder = commandLineGui.resultsInDescendingOrder();
                //ask the user to decide if they want to display the results in number or grade format
                boolean marksInGradeFormat = commandLineGui.marksInGradeFormat();

                // based on the chosen subject, process the data with the polymorphic filter subject data
                // then output the data to the user
                if (subjectName.equalsIgnoreCase("history")) {

                    List<Student> outputList = dataProcessor.filterSubjectData(dataProcessor.getHistory(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);
                    commandLineGui.printStudents(outputList, marksInGradeFormat, subjectYear, subjectName, resultsInDescendingOrder,flagFailedModuleStudents);

                } else if (subjectName.equalsIgnoreCase("maths")) {
                    List<Student> outputList = dataProcessor.filterSubjectData(dataProcessor.getMaths(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);
                    commandLineGui.printStudents(outputList, marksInGradeFormat, subjectYear, subjectName, resultsInDescendingOrder,flagFailedModuleStudents);

                } else if (subjectName.equalsIgnoreCase("geography")) {

                    List<Student> outputList = dataProcessor.filterSubjectData(dataProcessor.getGeography(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);
                    commandLineGui.printStudents(outputList, marksInGradeFormat, subjectYear, subjectName, resultsInDescendingOrder,flagFailedModuleStudents);
                } else if (subjectName.equalsIgnoreCase("all subjects")) {
                    //history data
                    List<Student> historyList = dataProcessor.filterSubjectData(dataProcessor.getHistory(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);

                    //maths data
                    List<Student> mathsList = dataProcessor.filterSubjectData(dataProcessor.getMaths(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);

                    //geography data
                    List<Student> geographyList = dataProcessor.filterSubjectData(dataProcessor.getGeography(), subjectYear,
                            flagFailedModuleStudents, resultsInDescendingOrder);

                    //second parameter will place grades in the result slot if requested
                    commandLineGui.printStudents(historyList, marksInGradeFormat, subjectYear, "History", resultsInDescendingOrder,flagFailedModuleStudents);
                    commandLineGui.printStudents(mathsList, marksInGradeFormat, subjectYear, "Maths", resultsInDescendingOrder,flagFailedModuleStudents);
                    commandLineGui.printStudents(geographyList, marksInGradeFormat, subjectYear, "Geography", resultsInDescendingOrder,flagFailedModuleStudents);
                }
                //the running of the programme is always the opposite of the option the user chooses to close
                runningProgramme = !commandLineGui.closeProgrammeSelection();
            }
        } else{
            commandLineGui.errorMessage("Error with the data file");
        }

    }
}



