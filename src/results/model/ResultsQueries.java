package results.model;

// required java libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VBlue
 */
// this class contains all the methods to get the required data from the java derby database.
public class ResultsQueries {
    
    // database connection url with id and password
    private String PASSWORD = "vismay";
    private String URL = "jdbc:derby://localhost:1527/MMS2";
    private String USERNAME = "vismay";
    
    private Connection connection = null; // manages databaes connection
    
    // all the prepared statement objects to create prepared statement in the constructor.
    private PreparedStatement selectAllStudents = null;
    private PreparedStatement selectStudent = null;
    private PreparedStatement selectTotalInRange = null;
    private PreparedStatement updateExamAndTotal = null;
    private PreparedStatement updateGrade = null;
    
    //Result class objects to handles all the achieved results from the database.
    private Result result;
    List<Result> results;
    
    // constructor
    public ResultsQueries() {
        //handles the database connection and creates SQL statements
        try {
            
            // getting the database connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection == null) {
                System.exit(0);
            }
            
            // creating all the prepared statements for the system
            selectAllStudents = connection.prepareStatement("select * from MARKS");
            
            selectStudent = connection.prepareStatement("select * from MARKS where STUDENTID = ?");
            
            selectTotalInRange = connection.prepareStatement("select * from MARKS where TOTAL BETWEEN ? AND ?");
                    
            updateExamAndTotal = connection.prepareStatement("update MARKS set EXAM = ?, TOTAL = ? WHERE STUDENTID = ?");
                    
            updateGrade = connection.prepareStatement("update MARKS set GRADE = ? where STUDENTID = ?");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(0);
        }
        
    }
    
    // a method to get all the results from the database.
    public List<Result> getAllResults() throws SQLException {
        
        // ResultSet object to get results.
        ResultSet resultSet = null;
        
        try {
            // executes query via result set object.
            resultSet = selectAllStudents.executeQuery();
            results = new ArrayList<Result>();
            
            // storing all the results form the database into result object and in list of result objects.
            while (resultSet.next()) {
                result = new Result();

                result.setStudentID(resultSet.getString(1));
                result.setAssignment1(resultSet.getInt(2));
                result.setAssignment2(resultSet.getInt(3));
                result.setExam(resultSet.getInt(4));
                result.setTotal(resultSet.getInt(5));
                result.setGrade(resultSet.getString(6));

                results.add(result);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        
        // returing all the achieved results.
        return results;
    }
    
    // a method is to get the result for the specific student.
    public List<Result> getResultsForStudent(String sid) throws SQLException {
        
        // ResultSet object to get results.
        ResultSet resultSet = null;
        
        try {
            // setting student id for the prepared statement.
            selectStudent.setString(1, sid);
            
            // executes query via result set object.
            resultSet = selectStudent.executeQuery();
            results = new ArrayList<Result>();
            
            // storing all the results form the database into result object and in list of result objects.
            while (resultSet.next()) {
                result = new Result();

                result.setStudentID(resultSet.getString(1));
                result.setAssignment1(resultSet.getInt(2));
                result.setAssignment2(resultSet.getInt(3));
                result.setExam(resultSet.getInt(4));
                result.setTotal(resultSet.getInt(5));
                result.setGrade(resultSet.getString(6));

                results.add(result);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        // returing all the achieved results.
        return results;
    }
    
    // a method to get the results of all students in the provided mark range.
    public List<Result> getResultsInRange(int t1, int t2) throws SQLException {
        
        // ResultSet object to get results.
        ResultSet resultSet = null;
        
        try {
            // setting range of marks for the prepared statement.
            selectTotalInRange.setInt(1, t1);
            selectTotalInRange.setInt(2, t2);
            
            // executes query via result set object.
            resultSet = selectTotalInRange.executeQuery();
            results = new ArrayList<Result>();
            
            // storing all the results form the database into result object and in list of result objects.
            while (resultSet.next()) {
                result = new Result();

                result.setStudentID(resultSet.getString(1));
                result.setAssignment1(resultSet.getInt(2));
                result.setAssignment2(resultSet.getInt(3));
                result.setExam(resultSet.getInt(4));
                result.setTotal(resultSet.getInt(5));
                result.setGrade(resultSet.getString(6));

                results.add(result);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        // returing all the achieved results.
        return results;
    }
    
    // a method to update exam and total mark for a perticular student.
    public int updateExamAndTotalMarks(String sid, int e, int t) throws SQLException {
        
        // integer variable to get the numbers of rows updated in database
        int updateResult = 0;
        
        try {
            // setting the student ID, exam mark and total mark for the prepared statement.
            updateExamAndTotal.setInt(1, e);
            updateExamAndTotal.setInt(2, t);
            updateExamAndTotal.setString(3, sid);
            
            // executes query via integer object. After execution the number of rows updated will be stored into integer variable.
            updateResult = updateExamAndTotal.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } 
        
        // returning the number of rows updated.
        return updateResult;
    }
    
    // a method to update all the grades.
    public int updateGrade() throws SQLException {
        // integer variable to get the numbers of rows updated in database
        int gradeResult = 0;

        results = getAllResults();
        
        for (Result result1 : results) {
            
            // required variables to update the grades.
            String studentid = result1.getStudentID();
            int totalMarks = result1.getTotal();
            int ass1 = result1.getAssignment1();
            int ass2 = result1.getAssignment2();
            int exam = result1.getExam();
            String grade;
            
            /* This is the mark structure to set the grades.
               I have consedered that to pass every assesment marks must be more than 50% of total marks. 
               assignment 1 full marks = 20 
               assignment 2 full marks = 30
               exam full marks = 50
               total cource marks = 100 */
            if (totalMarks <= 100 && totalMarks >= 85) {
                grade = "HD";
            } else if (totalMarks < 85 && totalMarks >= 75) {
                grade = "D";
            } else if (totalMarks < 75 && totalMarks >= 65) {
                grade = "C";
            } else if ((totalMarks < 65 && totalMarks >= 50) && (ass1 >= 10 && ass2 >= 15 && exam >= 25)) {
                grade = "P";
            } else if ((totalMarks < 50 && totalMarks >= 45) && (ass1 < 10 || ass2 < 15) && (ass1 > 0 && ass2 > 0 && exam > 0)) {
                grade = "SA";
            } else if ((totalMarks < 50 && totalMarks >= 45) && (ass1 < 10 || ass2 < 15 || exam < 25) && (ass1 > 0 && ass2 > 0 && exam > 0)) {
                grade = "SE";
            } else if (totalMarks == 0 && ass1 == 0 && ass2 == 0 && exam == 0) {
                grade = "AF";
            } else if (totalMarks < 50 && exam == 0) {
                grade = "NS";
            } else {
                grade = "F";
            }
            
            try {
                // setting the student ID and grade for the prepared statement.
                updateGrade.setString(1, grade);
                updateGrade.setString(2, studentid);
                
                // executes query via integer object. After execution the number of rows updated will be stored into integer variable.
                gradeResult = updateGrade.executeUpdate();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } 
        }
        
        // returning the number of rows updated.
        return gradeResult;
    }
    
    // to close the database
    public void close() {
        try {
             connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
