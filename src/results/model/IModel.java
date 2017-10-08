package results.model;

/**
 *
 * @author VBlue
 */

// this is the interface to handle all the methods for the observer to create and maintain the model with all the results.
public interface IModel {
    
    public void setAllStudents();
    
    public void setSpecifiedStident(String s);
    
    public void setStudentsInRange(int t1, int t2);
    
    public void updateExamAndTotal(String sid, int exam, int total);
    
    public void updateGrades();
    
    public void previous();
    
    public void next();
    
    public void close();
    
}
