package results.model;

/**
 *
 * @author VBlue
 */

// result class that represent results for the system.
public class Result {
    
    // required all the variables for the system.
    private String studentID;
    private int assignment1;
    private int assignment2;
    private int exam;
    private int total1;
    private int total2;
    private int total = assignment1 + assignment2 + exam;
    private String grade;
    
    // get method for studen ID
    public String getStudentID() {
        return studentID;
    }
    
    // set method for student ID
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    // get method for assignment 1 mark
    public int getAssignment1() {
        return assignment1;
    }
    
    // set method for assignment 1 mark
    public void setAssignment1(int assignment1) {
        this.assignment1 = assignment1;
    }
    
    // get method for assignment 2 mark
    public int getAssignment2() {
        return assignment2;
    }
    
    // set method for assignment 2 mark
    public void setAssignment2(int assignment2) {
        this.assignment2 = assignment2;
    }
    
    // get method for exam mark
    public int getExam() {
        return exam;
    }
    
    // set method for exam mark.
    public void setExam(int exam) {
        this.exam = exam;
    }
    
    // get method for total1 textfield
    public int getTotal1() {
        return total1;
    }
    
    // set method for total1 textfield
    public void setTotal1(int total1) {
        this.total1 = total1;
    }
    
    // get method for total2 textfield
    public int getTotal2() {
        return total2;
    }
    
    // set method for total2 textfield
    public void setTotal2(int total2) {
        this.total2 = total2;
    }
    
    // get method for total mark
    public int getTotal() {
        return total;
    }
    
    // set method for total mark
    public void setTotal(int total) {
        this.total = total;
    }
    
    // get method for total mark
    public String getGrade() {
        return grade;
    }
    
    // set method for grades
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    // format to display result output in TextArea
    @Override
    public String toString(){
        return String.format("%s\t %s\t %s\t %s\t %s\t %s%n", getStudentID(), getAssignment1(), getAssignment2(), getExam(), getTotal(), getGrade());
    }
    
}
