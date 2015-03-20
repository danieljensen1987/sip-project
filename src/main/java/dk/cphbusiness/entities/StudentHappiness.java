package dk.cphbusiness.entities;

public class StudentHappiness {
    private String studentID;
    private String happiness;

    public StudentHappiness(String studentID, String happiness) {
        this.studentID = studentID;
        this.happiness = happiness;
    }

    public String getHappiness() {
        return happiness;
    }

    public String getStudentID() {
        return studentID;
    }
    
    
}
