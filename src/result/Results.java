/*
 * This is the mark management system. 
 * This system is designed to help lactuers to maintain and manage marks of all the enrolled student for one subject.
 * This system is completly designed in the JAVA Swing MVC architecture.
 * The system is finalised on 15 August 2017.

 * Student ID : S0278997
 * Student Name : Vismay Patel
 * Email : s0278997@cqumail.com
 */
package result;

// here are all the required java libraries imported.
import results.controller.ResultsController;
import results.model.ResultsModel;
import results.model.ResultsQueries;
import results.view.ResultsView;

/**
 *
 * @author VBlue
 */
public class Results {
    
    // this is the main method to invoke all the required objects and classes for the system.
    // this method will start the gui.
    public static void main(String[] args) {
        
        ResultsQueries rq = new ResultsQueries();
        ResultsModel rm = new ResultsModel(rq);
        ResultsController rc = new ResultsController(rm);
        ResultsView rv = new ResultsView(rc);
        rm.addObserver(rv);
        rc.bind(rv);
    }
    
}
