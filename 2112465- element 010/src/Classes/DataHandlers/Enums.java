package Classes.DataHandlers;


public class Enums {
    /** Possible grades to attain */
    enum grades {
        Fail,
        Pass,
        Merit,
        Distinction
    }

    /** Returns the grade that is equal to the entered result */
    public grades getGrade(int averageMark){

       if (averageMark < 40){
           return grades.Fail;
       }
       else if(averageMark > 39 && averageMark < 60){
           return grades.Pass;
       }
       else if(averageMark > 59 && averageMark < 70){
           return grades.Merit;
       }
       else if(averageMark > 69 ){
           return grades.Distinction;
       }
       return null;

    }

}
