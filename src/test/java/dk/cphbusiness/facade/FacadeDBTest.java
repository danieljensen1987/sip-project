package dk.cphbusiness.facade;

import com.google.gson.Gson;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.entities.Teacher;
import dk.cphbusiness.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FacadeDBTest {
    private Teacher teacher1 = new Teacher("1");
    private Teacher teacher2 = new Teacher("2");
    FacadeDB facade = new FacadeDB();
    public Student student = new Student("1");
    private List<Teacher> teacherList = new ArrayList();
    private List<Teacher> teacherList2 = new ArrayList();
    Gson gson = new Gson();
    
    
    @Before
    public void setUp(){
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        Collection c1 = new ArrayList();
        Collection c2 = new ArrayList();
        Collection c3 = new ArrayList();
        Collection c4 = new ArrayList();
        c1.add("Anders");
        c1.add("Peter");
        c2.add("Peter");
        c3.add("Anders");
        c3.add("Torben");
        c4.add("Tysker");
        c4.add("Peter");
        Subject s1 = new Subject("Android", "1", c1);
        Subject s2 = new Subject("3DPrint", "1", c2);
        Subject s3 = new Subject("C#", "1", c3);
        Subject s4 = new Subject("AI", "1", c4);
        String j1 = gson.toJson(s1);
        String j2 = gson.toJson(s2);
        String j3 = gson.toJson(s3);
        String j4 = gson.toJson(s4);
        facade.addProposal(j1);
        facade.addProposal(j2);
        facade.addProposal(j3);
        facade.addProposal(j4);
        
        facade.addToFirstRound(j1);
        facade.addToFirstRound(j3);
        facade.addToFirstRound(j4);
        facade.addToFirstRound(j2);
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
        Collection c = new ArrayList();
        c.add(new Teacher("Anders"));
        c.add(new Teacher("Peter"));
        
        Subject subject1 = new Subject("testTitle", "testDesc", c);
        String describtion = subject1.getDescription();
        
        assertEquals(subject1.getTeachers().size(), 2);
        assertEquals(subject1.getTitle(), "testTitle");
        assertEquals(describtion, "testDesc");
    }
    
    @Test
    public void testAddProposal(){
        facade.addProposal("{'title':'test','description':'1',teachers:{{'name':'Peter'}}}");
        assertEquals(facade.getProposals().length(), 6);
    }
    
    @Test
    public void testCreateFirstRoundElectiveSubjects(){
        facade.addToFirstRound("test");
        assertEquals(facade.getFirstRound().length(), 6);
    }
    
    @Test
    public void testFirstRoundSelection(){
//        String available = facade.getFirstRound();
//        System.out.println(available);
//        List <Selected> priority = new ArrayList();
//        Selected s1 = new Selected(available.get(0).toString(), 1);
//        Selected s2 = new Selected(available.get(1).toString(), 2);
//        Selected s3 = new Selected(available.get(2).toString(), 1);
//        Selected s4 = new Selected(available.get(3).toString(), 2);
//        
//        priority.add(s1);
//        priority.add(s2);
//        priority.add(s3);
//        priority.add(s4);
//        
//        assertEquals(priority.size(), 4);
    }
}
