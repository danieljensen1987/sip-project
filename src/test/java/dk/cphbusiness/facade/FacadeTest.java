package dk.cphbusiness.facade;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

public class FacadeTest {

    private Teacher teacher1 = new Teacher("1");
    private Teacher teacher2 = new Teacher("2");
    Facade facade = new Facade();
    public Student student = new Student("1");
    private List<Teacher> teacherList = new ArrayList();
    private List<Teacher> teacherList2 = new ArrayList();
    private List<Selected> priority = new ArrayList();
    Gson gson = new Gson();

    @Before
    public void setUp() {
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        Subject s1 = new Subject("Android", "1");
        s1.addTeacher(new Teacher("Anders"));
        s1.addTeacher(new Teacher("Peter"));
        Subject s2 = new Subject("3DPrint", "1");
        s2.addTeacher(new Teacher("Peter"));
        Subject s3 = new Subject("C#", "1");
        s3.addTeacher(new Teacher("Anders"));
        s3.addTeacher(new Teacher("Torben"));
        Subject s4 = new Subject("AI", "1");
        s4.addTeacher(new Teacher("Tysker"));
        s4.addTeacher(new Teacher("Peter"));
        String j1 = gson.toJson(s1);
        String j2 = gson.toJson(s2);
        String j3 = gson.toJson(s3);
        String j4 = gson.toJson(s4);
        facade.addProposal(j1);
        facade.addProposal(j2);
        facade.addProposal(j3);
        facade.addProposal(j4);

        facade.addToAvailableCourses(j1);
        facade.addToAvailableCourses(j3);
        facade.addToAvailableCourses(j4);
        facade.addToAvailableCourses(j2);
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
    public void testGetFacade(){
        Facade f = Facade.getFacade(false);
        assertNotNull(f);
    }

    @Test
    public void testCreateSubject() {
        Subject subject1 = new Subject("testTitle", "testDesc");
        subject1.addTeacher(new Teacher("Anders"));
        subject1.addTeacher(new Teacher("Peter"));
        String describtion = subject1.getDescription();

        assertEquals(subject1.getTeachers().size(), 2);
        assertEquals(subject1.getTitle(), "testTitle");
        assertEquals(describtion, "testDesc");
    }
    
    @Test
    public void testCreateSelected(){
        Subject sub1 = new Subject("Android", "test");
        Selected sel1 = new Selected(sub1, 1);
         
        assertEquals(sel1.getSubject(), sub1);
        assertEquals(sel1.getPriority(), 1);
    }

    @Test
    public void testAddProposal() {
        Subject s1 = new Subject("Android", "1");
        s1.addTeacher(new Teacher("Anders"));
        String j1 = gson.toJson(s1);
        facade.addProposal(j1);
        
        String json = facade.getProposals();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 5);
    }

    @Test
    public void testCreateFirstRoundElectiveSubjects() {
        Subject subject = new Subject("test", "test");
        subject.addTeacher(new Teacher("test teacher"));
        facade.addToAvailableCourses(gson.toJson(subject));
        String json = facade.getAvailableCourses();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }

    @Test
    public void testFirstRoundSelection() {
        Subject sub1 = new Subject("Android", "test");
        Selected sel1 = new Selected(sub1, 1);
        
        facade.addToFirstRound(gson.toJson(sel1));
        
        String firstRound = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(firstRound);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 1);
        
    }
}
