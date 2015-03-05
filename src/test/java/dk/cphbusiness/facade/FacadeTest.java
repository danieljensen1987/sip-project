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
        facade.addToFirstRound(gson.toJson(subject));
        String json = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }

    @Test
    public void testFirstRoundSelection() {
        
        
        String available = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(available);
        JsonArray jasonArray = element.getAsJsonArray();
        
        Subject sub1 = gson.fromJson(jasonArray.get(0), Subject.class);
        Subject sub2 = gson.fromJson(jasonArray.get(1), Subject.class);
        Subject sub3 = gson.fromJson(jasonArray.get(2), Subject.class);
        Subject sub4 = gson.fromJson(jasonArray.get(3), Subject.class);
        Selected sel1 = new Selected(sub1, 1);
        Selected sel2 = new Selected(sub2, 1);
        Selected sel3 = new Selected(sub3, 2);
        Selected sel4 = new Selected(sub4, 2);
        priority.add(sel1);
        priority.add(sel2);
        priority.add(sel3);
        priority.add(sel4);
        
        System.out.println(priority);
        assertEquals(priority.size(), 4);
        
//        System.out.println(sub);
//        Selected sel = new Selected(sub, 1);
//        priority.add(sel);
//        
//        
//        
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(json);
//        JsonArray jasonArray = element.getAsJsonArray();
//        System.out.println(jasonArray);
//        
//        List <Selected> priority = new ArrayList();
//        Selected s1 = new Selected(jasonArray.get(0), 1);
//        Selected s2 = new Selected(jasonArray.get(1).toString(), 2);
//        Selected s3 = new Selected(jasonArray.get(2).toString(), 1);
//        Selected s4 = new Selected(jasonArray.get(3).toString(), 2);
//        
//        
//        priority.add(s1);
//        priority.add(s2);
//        priority.add(s3);
//        priority.add(s4);
//        System.out.println(gson.toJson(priority));
////        assertEquals(priority.size(), 4);
    }
}
