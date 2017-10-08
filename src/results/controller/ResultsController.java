package results.controller;

// required java libraries.
import results.model.IModel;
import results.view.IView;

/**
 *
 * @author VBlue
 */
public class ResultsController {
    
    IModel model;
    IView view;
    
    public ResultsController(IModel m) {
        model = m;
    }
    
    //reference to the IView interface
    // bind view
    public void bind(IView v) {
        view = v;
    }
    
    // it handles related events when All Student button is clicked.
    public void resultsForAllStudents () {
        model.setAllStudents();
        view.setBrowsing(true);
    }
    
    // it handles related events when Specific student button is clicked.
    public void resultForSpecificStudent(String s) {
        model.setSpecifiedStident(s);
        view.setBrowsing(false);
    }
    
    // it handles related events when All Student in Range button is clicked.
    public void resultsForStudentsInRange(int t1, int t2) {
        model.setStudentsInRange(t1, t2);
        view.setBrowsing(true);
    }
    
    // it handles related events when Update Exam and Total button is clicked.
    public void updateExamAndTotal(String sid, int exam, int total) {
        model.updateExamAndTotal(sid, exam, total);
        view.setBrowsing(false);
    }
    
    // it handles related events when Update grade button is clicked.
    public void updateGrade() {
        model.updateGrades();
        view.setBrowsing(false);
    }
    
    // handles events when nextButton is clicked
    public void next() {
        model.next();
    }

    // handles events when previousButton is clicked
    public void previous() {
        model.previous();
    }

    // handles events when window closed
    public void close() {
        model.close();
    }
    
}
