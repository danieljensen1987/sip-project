package dk.cphbusiness.facade;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.util.ArrayList;
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

    @Test(expected = NullPointerException.class)
    public void testAddProposalIsNull() throws MinimumCharacterException {
        facade.addProposal(null);
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddProposalTitleIsNull() throws MinimumCharacterException {
        Subject s = new Subject(null, "1", "aa");
        facade.addProposal(gson.toJson(s));
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddProposalDescriptionIsNull() throws MinimumCharacterException {
        Subject s = new Subject("c#", null, "aa");
        facade.addProposal(gson.toJson(s));
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddProposalTeacherIsNull() throws MinimumCharacterException {
        Subject s = new Subject("c#", "bb", null);
        facade.addProposal(gson.toJson(s));
    }

    @Test
    public void testAddSubjectToFirstRound() throws MinimumCharacterException {
        Subject s = new Subject("test", "test", "bb");
        facade.addSubjectToFirstRound(gson.toJson(s));
        String json = facade.getFirstRoundSubjects();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 5);
    }

    @Test(expected = NullPointerException.class)
    public void testAddSubjectToFirstRoundIsNull() throws MinimumCharacterException {
        facade.addSubjectToFirstRound(null);
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddSubjectToFirstRoundTitleIsNull() throws MinimumCharacterException {
        Subject s = new Subject(null, "1", "aa");
        facade.addSubjectToFirstRound(gson.toJson(s));
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddSubjectToFirstRoundDescriptionIsNull() throws MinimumCharacterException {
        Subject s = new Subject("c#", null, "aa");
        facade.addSubjectToFirstRound(gson.toJson(s));
    }

    @Test(expected = MinimumCharacterException.class)
    public void testAddSubjectToFirstRoundTeacherIsNull() throws MinimumCharacterException {
        Subject s = new Subject("c#", "bb", null);
        facade.addSubjectToFirstRound(gson.toJson(s));
    }

    @Test
    public void testGetFacade() {
        Facade f = Facade.getFacade(false);
        assertNotNull(f);
    }

    @Test
    public void testCreateSelected() {
        Selected sel = new Selected("Android", "test", "AKA", 1, "aa");
        assertEquals(sel.getTitle(), "Android");
        assertEquals(sel.getDescription(), "test");
        assertEquals(sel.getTeacher(), "AKA");
        assertEquals(sel.getPriority(), 1);
        assertEquals(sel.getStudentId(), "aa");
    }

    @Test
    public void testaddTofirstRoundPriorities() {
        Selected s1 = new Selected("aa", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("bb", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("cc", "cb", "cc", 2, "aa");
        Selected s4 = new Selected("dd", "db", "dc", 2, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        facade.addToFirstRoundPriorities(gson.toJson(arr));
        String firstRound = facade.getFirstRoundPriorities();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(firstRound);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 4);
    }

    @Test(expected = NullPointerException.class)
    public void testaddTofirstRoundPrioritiesIsNull() {
        facade.addToFirstRoundPriorities(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaddTofirstRoundPrioritiesLessThanFour() {
        Selected s1 = new Selected("aa", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("bb", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("cc", "cb", "cc", 2, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        facade.addToFirstRoundPriorities(gson.toJson(arr));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaddTofirstRoundPrioritiesMoreThanFour() {
        Selected s1 = new Selected("aa", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("bb", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("cc", "cb", "cc", 2, "aa");
        Selected s4 = new Selected("dd", "db", "dc", 2, "aa");
        Selected s5 = new Selected("ee", "eb", "ec", 23, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        arr.add(s5);
        facade.addToFirstRoundPriorities(gson.toJson(arr));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testaddTofirstRoundPrioritiesMoreThanTwoOfFirstIsSelected() {
        Selected s1 = new Selected("aa", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("bb", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("cc", "cb", "cc", 1, "aa");
        Selected s4 = new Selected("dd", "db", "dc", 2, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        facade.addToFirstRoundPriorities(gson.toJson(arr));
    }
    
    @Test
    public void test(){
        Selected s1 = new Selected("C#", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("Prolog", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("Android", "cb", "cc", 2, "aa");
        Selected s4 = new Selected("Haskell", "db", "dc", 2, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        facade.addToFirstRoundPriorities(gson.toJson(arr));
        
        int i = facade.calculatePoint("C#", "Android", "aa");
        
        assertEquals(i, 17);       
    }  
}
