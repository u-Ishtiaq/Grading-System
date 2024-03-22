package Classes.FrontEnd;

import Classes.DataHandlers.Enums;
import Classes.Student.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Controls the input and output for the user */
public class GuiController {

    //outputs
    /** Used to print an error that occurs */
    public void errorMessage(String error){
        System.out.println(error);
    }

    /** Prints a general output for the user */
    public void printMessage(String message){
        System.out.println(message);
    }


    /** Outputs the student data provided, in a predetermined manner */
    public void printStudents(List<Student> studentList, boolean marksInGradeFormat,int subjectYear,String subjectName,
                              boolean resultsInDescendingOrder,boolean failedStudents){

        // print subject selected
        printMessage("Subject: " + subjectName);

        // print year selected
        if (subjectYear == 0){
            printMessage("Year: " + "All years");
        }else{
            printMessage("Year: " + subjectYear);
        }

        if(failedStudents){
            printMessage("Students who failed a module");
        }

        //print order type
        if (resultsInDescendingOrder) {
            printMessage("Order: " + "Descending mark order");
        }
        else{
            printMessage("Order: " + "Alphabetical order (by Surname)");
        }

        //new line
        printMessage("");

        //output student data
        if(studentList != null) {
            for (Student student : studentList) {
                String outputLine = student.getFirstName() + " " + student.getLastName() + ": ";
                //if mark format then add the final result as a grade
                if (!marksInGradeFormat) {
                    outputLine += student.getFinalResult();
                } else {
                    Enums enums = new Enums();
                    outputLine += enums.getGrade(student.getFinalResult());
                }
                //output the data line
                System.out.println(outputLine);
            }
        } else{
            printMessage("No students");
        }
        // new line
        printMessage("");

    }

    //inputs and options
    /** General method used internally by the class to determine valid options, returns the chosen value */
    private String takeInput(HashMap<String, String> options) {
        boolean validAnswer = false;
        String chosenOption = null;

        // display the available options
        for (Map.Entry<String, String> option : options.entrySet()) {
            String optionKey = option.getKey();
            String optionValue = option.getValue();
            System.out.println(optionKey + ") " + optionValue);
        }

        //while the answer is invalid
        while (!validAnswer) {
            //request an answer
            Scanner inputReader = new Scanner(System.in);
            chosenOption = inputReader.nextLine();

            //valid answer check
            try {
                if (options.get(chosenOption.toUpperCase()) != null) {
                    validAnswer = true;
                }else{
                    errorMessage("Invalid Answer");
                }
            } catch (NullPointerException nullError) {
                //invalid answer alert the user
                errorMessage("Invalid Answer");
            }
        }

        //once valid return the chosen option
        return options.get(chosenOption.toUpperCase());
    }

    /** Triggers the subject option selection menu for the user and returns the chosen option */
    public String selectASubject() {
        //select a subject
        System.out.println("Select a subject: ");

        //create option hashmap for the take input function to use
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "Maths");
        options.put("B", "Geography");
        options.put("C", "History");
        options.put("D", "All subjects");

        //send the options to the take input function so it displays the options and then returns the users choice
        String chosenOption = takeInput(options);


        return chosenOption;
    }

    /** Triggers the year option selection menu for the user and returns the chosen option */
    public int selectAYear() {
        //select a subject
        System.out.println("Select a year: ");
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "2000");
        options.put("B", "2001");
        options.put("C", "2002");
        options.put("D", "2003");
        options.put("E", "2004");
        options.put("F", "2005");
        options.put("G", "All years");


        String chosenOption = takeInput(options);
        if (chosenOption.equals(options.get("G"))) {
            return 0;
        }

        return Integer.valueOf(chosenOption);
    }

    /** Triggers the flag students who failed option selection menu for the user and returns the chosen option */
    public boolean flagStudentsWhoFailedAModule() {
        //choose to show only students who failed a module within the specified time
        System.out.println("Select a year: ");
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "Print all previously specified results");
        options.put("B", "Print only students who failed a module, within the previously specified parameters");


        String chosenOption = takeInput(options);
        //chosen to flag the failed students then return true
        return chosenOption.equals(options.get("B"));

        //otherwise false
    }

    /** ask the user if they want results in descending order or alphabetical order
     * true = descending, false = alphabetical */
    public boolean resultsInDescendingOrder() {
        //choose to show only students who failed a module within the specified time
        System.out.println("How should the results be ordered?  ");
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "Print results in alphabetical order");
        options.put("B", "Print results in mark descending order");


        String chosenOption = takeInput(options);
        //chosen to flag the failed students then return true
        return chosenOption.equals(options.get("B"));

        //otherwise false
    }

    /** Triggers the display marks in grade format option selection menu for the user and returns the chosen option */
    public boolean marksInGradeFormat() {
        //choose to show only students who failed a module within the specified time
        System.out.println("Print marks in grade format? ");
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "Yes");
        options.put("B", "No");


        String chosenOption = takeInput(options);
        //chosen to flag the failed students then return true
        return chosenOption.equals(options.get("A"));

        //otherwise false
    }

    public boolean closeProgrammeSelection(){
        printMessage("Would you like to close the programme \n");
        HashMap<String, String> options = new HashMap<>();
        options.put("A", "Yes");
        options.put("B", "No");


        String chosenOption = takeInput(options);
        //if the option chosen was A then return true because they want to close the programme
        return chosenOption.equals(options.get("A"));

    }

}
