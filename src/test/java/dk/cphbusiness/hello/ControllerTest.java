package dk.cphbusiness.hello;

import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.entities.Teacher;
import dk.cphbusiness.entities.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ControllerTest {
    private Teacher teacher1 = new Teacher("1");
    private Teacher teacher2 = new Teacher("2");
    Controller prop = new Controller();
    public Student student = new Student("1");
    private List<Teacher> teacherList = new ArrayList();
    private List<Teacher> teacherList2 = new ArrayList();
    
    
    @Before
    public void setUp(){
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        prop.addProposal("Android", "1", teacherList);
        prop.addProposal("C#", "1", teacherList);
        prop.addProposal("3DPrint", "1", teacherList);
        prop.addProposal("AI", "1", teacherList);
        prop.addProposal("Haskel", "1", teacherList);
        
        for (int i = 0; i < prop.getProposedSubjects().size(); i++) {
            prop.AddToFirstRound(prop.getProposedSubjects().get(i).getTitle());
        }
        
    }
    

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
        
        Subject subject1 = new Subject("testTitle", "testDesc", teacherList);
        String describtion = subject1.getDescription();
        
        assertEquals(subject1.getTeachers().size(), 2);
        assertEquals(subject1.getTitle(), "testTitle");
        assertEquals(describtion, "testDesc");
    }
    
    @Test
    public void testAddProposal(){
        prop.addProposal("NewProposal", "This is a description", teacherList2);
        assertEquals(prop.getProposedSubjects().size(), 6);
    }
    
    @Test
    public void testCreateFirstRoundElectiveSubjects(){
        prop.AddToFirstRound("test");
        assertEquals(prop.getFirstRound().size(), 6);
    }
    
    @Test
    public void testFirstRoundSelection(){
        List available = prop.getFirstRound();
        System.out.println(available);
        List <Selected> priority = new ArrayList();
        Selected s1 = new Selected(available.get(0).toString(), 1);
        Selected s2 = new Selected(available.get(1).toString(), 2);
        Selected s3 = new Selected(available.get(2).toString(), 1);
        Selected s4 = new Selected(available.get(3).toString(), 2);
        
        priority.add(s1);
        priority.add(s2);
        priority.add(s3);
        priority.add(s4);
        
        assertEquals(priority.size(), 4);
    }
}
