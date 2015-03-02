package dk.cphbusiness.entities;

public class Teacher {
    private String teacherID;

    public Teacher(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    @Override
    public String toString() {
         return "Teacher{" + "teacherID=" + teacherID + '}';
    }
    
    
    
    
}
