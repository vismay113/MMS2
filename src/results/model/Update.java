/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package results.model;

/**
 *
 * @author VBlue
 */
public class Update {
    
    private String message;
    private Result result;
    
    // constructor with parameters to set message for text area and result to display in the gui text fields.
    public Update(String m, Result r) {
        this.message = m;
        this.result = r;
    }
    
    // return message
    public String getMessage() {
        return message;
    }
    
    // return Result
    public Result getResult() {
        return result;
    }
    
}
