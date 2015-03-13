package dk.cphbusiness.entities;

public class Selected extends Subject{
    String studentId;
    int priority;

    public Selected(String title, String description, String teacher, int priority, String studentId) {
        super(title, description, teacher);
        
        this.studentId = studentId;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    } 

    public String getStudentId() {
        return studentId;
    }
}
