package dk.cphbusiness.hello;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class GreeterTest {
    
    Proposal prop = new Proposal();
    public Student student = new Student("1");
    private List<Teacher> teacherList = new ArrayList();
    private List<Teacher> teacherList2 = new ArrayList();
    
    private Teacher teacher1 = new Teacher("1");
    private Teacher teacher2 = new Teacher("2");

    @Test
    public void testCreateStudent() {
        assertNotNull(student);
        String StudID = student.getStudentID();
        assertEquals(StudID, "1");
    }

    @Test
    public void testCreateTeacher() {
        assertNotNull(teacher1);        
        String ID = teacher1.getTeacherID();
        assertEquals(ID, "1");
    }

    @Test
    public void testCreateSubject() {
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        Subject subject1 = new Subject("testTitle", "testDesc", teacherList);
        String describtion = subject1.getDescription();
        
        assertEquals(subject1.getTeachers().size(), 2);
        assertEquals(subject1.getTitle(), "testTitle");
        assertEquals(describtion, "testDesc");
    }
    
    @Test
    public void testAddProposal(){
        teacherList2.add(teacher1);
        teacherList2.add(teacher2);
        //Subject newSubject = new Subject("testTitle", "testDesc", teacherList2);
        prop.addProposal("NewProposal", "This is a description", teacherList2);
        assertEquals(prop.proposedSubjects.size(), 1);
    }
}
