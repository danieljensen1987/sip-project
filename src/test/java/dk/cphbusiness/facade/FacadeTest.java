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

    Facade facade = new Facade();
    private List<Selected> priority = new ArrayList();
    Gson gson = new Gson();

    @Before
    public void setUp() {
        facade.addProposal(gson.toJson(new Subject("Android", "make good apps", "PELO")));
        facade.addProposal(gson.toJson(new Subject("C#", "make more apps", "TOR")));
        facade.addProposal(gson.toJson(new Subject("Arduino", "what", "TOG")));
        facade.addProposal(gson.toJson(new Subject("HASKELL", "be brainy", "Tysker")));
        

        facade.addToAvailableCourses(gson.toJson(new Subject("Android", "make good apps", "PELO")));
        facade.addToAvailableCourses(gson.toJson(new Subject("C#", "make more apps", "TOR")));
        facade.addToAvailableCourses(gson.toJson(new Subject("Arduino", "what", "TOG")));
        facade.addToAvailableCourses(gson.toJson(new Subject("HASKELL", "be brainy", "Tysker")));
    }

    @Test
    public void testCreateStudent() {
        Student s = new Student("aa");
        assertNotNull(s);
        assertEquals(s.getStudentID(), "aa");
    }

    @Test
    public void testCreateTeacher() {
        Teacher t = new Teacher("AKA");
        assertNotNull(t);
        assertEquals(t.getTeacherID(), "AKA");
    }
    
    @Test
    public void testGetFacade(){
        Facade f = Facade.getFacade(false);
        assertNotNull(f);
    }

    @Test
    public void testCreateSubject() {
        Subject s = new Subject("testTitle", "testDesc", "aka");
        assertEquals(s.getTeacher(), "aka");
        assertEquals(s.getTitle(), "testTitle");
        assertEquals(s.getDescription(), "testDesc");
    }
    
    @Test
    public void testCreateSelected(){
        Subject s = new Subject("Android", "test", "AKA");
        Selected sel1 = new Selected(s, 1);
        assertEquals(sel1.getSubject(), s);
        assertEquals(sel1.getPriority(), 1);
    }

    @Test
    public void testAddProposal() {
        Subject s = new Subject("Android", "1", "aa");
        facade.addProposal(gson.toJson(s));
        
        String json = facade.getProposals();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 5);
    }

    @Test
    public void testCreateFirstRoundElectiveSubjects() {
        Subject s = new Subject("test", "test", "bb");
        facade.addToAvailableCourses(gson.toJson(s));
        String json = facade.getAvailableCourses();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }

    @Test
    public void testFirstRoundSelection() {
        Subject subject = new Subject("Android", "test", "cc");
        Selected sel = new Selected(subject, 1);
        
        facade.addToFirstRound(gson.toJson(sel));
        
        String firstRound = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(firstRound);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 1);
        
    }
}
