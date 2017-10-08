package results.view;

import results.model.Result;

/**
 *
 * @author VBlue
 */

//interface to handle user interface for record browsing.
public interface IView {
    
    public void setBrowsing(boolean flag);
    
    void displayMessage(String s);

    void displayRecord(Result r);
    
    public void clear();
    
}
