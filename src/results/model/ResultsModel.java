package results.model;

// required java libraries.
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VBlue
 */
public class ResultsModel extends Observable implements IModel {
    
    // required objects and variables to set and maintain model for the system.
    private IModel model;
    private ResultsQueries queries;
    List<Result> results;
    int currentEntryIndex;
    int numberOfEntries;
    Result currentEntry;
    
    // constructor to create model
    public ResultsModel (ResultsQueries rq) {
        super();
        queries = rq;
        currentEntryIndex = -1;
        numberOfEntries = 0;
        results = null;
        currentEntry = null;
    }
    
    // method to set the model state for multiple entries.
    private void setModelState() {
        numberOfEntries = results.size();
        Update u = null;
        if (numberOfEntries > 0) {
            currentEntryIndex = 0;
            currentEntry = results.get(currentEntryIndex);
            u = new Update(new String(), currentEntry);
        } else {
            currentEntryIndex = -1;
            currentEntry = null;
        }
        setChanged();
        notifyObservers(u);
    }
    
    // it handles related events when All Student button is clicked.
    @Override
    public void setAllStudents() {
        try {
            results = queries.getAllResults();
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModelState();
        Update u = null;
        if (currentEntryIndex < 0) {
            u = new Update("No record found! for ", currentEntry);
        } else {
            String m = "Record " + (currentEntryIndex + 1) + " of " + results.size();
            u = new Update(m, currentEntry);
        }
        setChanged();
        notifyObservers(u);
    }
    
    // it handles related events when Specific student button is clicked.
    @Override
    public void setSpecifiedStident(String s) {
        try {
            results = queries.getResultsForStudent(s);
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModelState();
        Update u = null;
        if (currentEntryIndex < 0) {
            u = new Update(new String("No record found! for " + s), currentEntry);
        } else {
            String m = "Record " + (currentEntryIndex + 1) + " of " + results.size();
            u = new Update(m, currentEntry);
        }
        setChanged();
        notifyObservers(u);
    }
    
    // it handles related events when All Student in Range button is clicked.
    @Override
    public void setStudentsInRange(int t1, int t2) {
        try {
            results = queries.getResultsInRange(t1, t2);
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModelState();
        Update u = null;
        if (currentEntryIndex < 0) {
            u = new Update("No record found!", currentEntry);
        } else {
            String m = "Record " + (currentEntryIndex + 1) + " of " + results.size();
            u = new Update(m, currentEntry);
        }
        setChanged();
        notifyObservers(u);
    }
    
    // it handles related events when Update Exam and Total button is clicked.
    @Override
    public void updateExamAndTotal(String sid, int exam, int total) {
        int updateResult = 0;
        try {
            updateResult = queries.updateExamAndTotalMarks(sid, exam, total);
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setChanged();
        Result r = new Result();
        r.setTotal(total);
        r.setStudentID(sid);
        r.setExam(exam);
        Update u = new Update("Exam marks " + exam + " and total marks " + total + " are updated successfully for Student " + sid + " .", r);
        notifyObservers(u);
    }
    
    // it handles related events when Update grade button is clicked.
    @Override
    public void updateGrades() {
        int updateResult = 0;
        try {
            updateResult = queries.updateGrade();
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setChanged();
        Result r = new Result();
        Update u = new Update("Grades are updated successfully for all Students .", r);
        notifyObservers(u);
    }
    
    // handles events when previousButton is clicked
    @Override
    public void previous() {
        currentEntryIndex--;
        // wrap around
        if (currentEntryIndex < 0) {
            currentEntryIndex = numberOfEntries - 1;
        }
        currentEntry = results.get(currentEntryIndex);
        String m = "Record " + (currentEntryIndex + 1) + " of " + results.size();
        Update u = new Update(m, currentEntry);
        setChanged();
        notifyObservers(u);
    }
    
    // handles events when nextButton is clicked
    @Override
    public void next() {
        currentEntryIndex++;
        // wrap around
        if (currentEntryIndex >= numberOfEntries) {
            currentEntryIndex = 0;
        }
        currentEntry = results.get(currentEntryIndex);
        String m = "Record " + (currentEntryIndex + 1) + " of " + results.size();
        Update u = new Update(m, currentEntry);
        setChanged();
        notifyObservers(u);
    }
    
    // handles events when window is closed
    @Override
    public void close() {
        queries.close();
    }
    
}
