package dk.cphbusiness.entities;

public class StudentHappiness {
    private String studentID;
    private int happiness;

    public StudentHappiness(String studentID, int happiness) {
        this.studentID = studentID;
        this.happiness = happiness;
    }

    public int getHappiness() {
        return happiness;
    }

    public String getStudentID() {
        return studentID;
    }
    
    
}
