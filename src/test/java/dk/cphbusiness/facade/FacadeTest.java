package dk.cphbusiness.facade;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.entities.Teacher;
import dk.cphbusiness.entities.Student;
import dk.cphbusiness.exceptions.MinimumCharacterException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FacadeTest {

    Facade facade = new Facade();
    Gson gson = new Gson();

    @Before
    public void setUp() throws MinimumCharacterException {
        facade.addProposal(gson.toJson(new Subject("Android", "make good apps", "PELO")));
        facade.addProposal(gson.toJson(new Subject("C#", "make more apps", "TOR")));
        facade.addProposal(gson.toJson(new Subject("Arduino", "what", "TOG")));
        facade.addProposal(gson.toJson(new Subject("HASKELL", "be brainy", "Tysker")));
        facade.addSubjectToFirstRound(gson.toJson(new Subject("Android", "make good apps", "PELO")));
        facade.addSubjectToFirstRound(gson.toJson(new Subject("C#", "make more apps", "TOR")));
        facade.addSubjectToFirstRound(gson.toJson(new Subject("Arduino", "what", "TOG")));
        facade.addSubjectToFirstRound(gson.toJson(new Subject("HASKELL", "be brainy", "Tysker")));
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
    public void testCreateSubject() {
        Subject s = new Subject("testTitle", "testDesc", "aka");
        assertEquals(s.getTeacher(), "aka");
        assertEquals(s.getTitle(), "testTitle");
        assertEquals(s.getDescription(), "testDesc");
    }
    
    @Test
    public void testAddProposal() throws MinimumCharacterException {
        Subject s = new Subject("Android", "1", "aa");
        facade.addProposal(gson.toJson(s));
        String json = facade.getProposals();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }
    
    @Test (expected = NullPointerException.class)
    public void testAddProposalIsNull() throws MinimumCharacterException {
        facade.addProposal(null);
    }
    
    @Test (expected = MinimumCharacterException.class)
    public void testAddProposalTitleIsNull() throws MinimumCharacterException {
        Subject s = new Subject(null, "1", "aa");
        facade.addProposal(gson.toJson(s));
    }
    
    @Test
    public void testAddSubjectToFirstRound() throws MinimumCharacterException{
        Subject s = new Subject("test", "test", "bb");
        facade.addSubjectToFirstRound(gson.toJson(s));
        String json = facade.getFirstRoundSubjects();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }
    
//    @Test(expected = NullPointerException.class)
//    public void testAddSubjectToFirstRoundIsNull() throws MinimumCharacterException{
//        facade.addSubjectToFirstRound(null);
//    }
    
    @Test
    public void testGetFacade(){
        Facade f = Facade.getFacade(false);
        assertNotNull(f);
    }
    
    @Test
    public void testCreateSelected(){
        Selected sel = new Selected("Android", "test", "AKA", 1);
        assertEquals(sel.getTitle(), "Android");
        assertEquals(sel.getDescription(), "test");
        assertEquals(sel.getTeacher(), "AKA");
        assertEquals(sel.getPriority(), 1);
    }

    @Test
    public void testFirstRoundSelection() {
        Selected sel = new Selected("Android", "test", "cc", 1);
        facade.addToFirstRound(gson.toJson(sel));
        
        String firstRound = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(firstRound);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 1);
        
    }
    
    @Test
    public void testFirstRoundSelectionj() {
        Selected sel = new Selected("Android", "test", "cc", 1);
        facade.addToFirstRound(gson.toJson(sel));
        
        String firstRound = facade.getFirstRound();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(firstRound);
        JsonArray jasonArray = element.getAsJsonArray();
        
        assertEquals(jasonArray.size(), 1);
        
    }
}
