package dk.cphbusiness.facade;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.cphbusiness.entities.Pool;
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
        facade.createTestData();
    }

    @Test
    public void testCreateSubject() {
        Subject s = new Subject("testTitle", "testDesc", "aka");
        assertEquals(s.getTeacher(), "aka");
        assertEquals(s.getTitle(), "testTitle");
        assertEquals(s.getDescription(), "testDesc");
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
    public void testGetFacade() {
        Facade f = Facade.getFacade(false);
        assertNotNull(f);
    }

    @Test
    public void testAddProposal() throws MinimumCharacterException {
        Subject s = new Subject("Android", "1", "aa");
        assertTrue(facade.addProposal(gson.toJson(s)));
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
    public void testGetProposals(){
        String json = facade.getProposals();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 8);
    }

    @Test
    public void testAddSubjectToFirstRound() throws MinimumCharacterException {
        Subject s = new Subject("test", "test", "bb");
        assertTrue(facade.addSubjectToFirstRound(gson.toJson(s)));
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
    public void testGetFirstRoundSubjects(){
        String json = facade.getFirstRoundSubjects();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 8);
    }

    @Test
    public void testAddTofirstRoundPriorities() {
        Selected s1 = new Selected("aa", "ab", "ac", 1, "aa");
        Selected s2 = new Selected("bb", "bb", "bc", 1, "aa");
        Selected s3 = new Selected("cc", "cb", "cc", 2, "aa");
        Selected s4 = new Selected("dd", "db", "dc", 2, "aa");
        ArrayList<Selected> arr = new ArrayList();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        assertTrue(facade.addToFirstRoundPriorities(gson.toJson(arr)));
    }

    @Test(expected = NullPointerException.class)
    public void testAddTofirstRoundPrioritiesIsNull() {
        facade.addToFirstRoundPriorities(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTofirstRoundPrioritiesLessThanFour() {
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
    public void testAddTofirstRoundPrioritiesMoreThanFour() {
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
    public void testAddTofirstRoundPrioritiesMoreThanTwoOfFirstIsSelected() {
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
    public void getFirstRoundPriorities(){
        String json = facade.getFirstRoundPriorities();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(jasonArray.size(), 16);
    }
    
    @Test
    public void testCalculatePoints(){
        ArrayList<String> poolA = new ArrayList();
        ArrayList<String> poolB = new ArrayList();
        poolA.add("Arduino");
        poolA.add("");
        poolB.add("SW Design");
        poolB.add("");
        String points1 = facade.calculatePoint(poolA, poolB, "Bjarke Carlsen");
        String points2 = facade.calculatePoint(poolA, poolB, "Martin Olgenkjær");
        String points3 = facade.calculatePoint(poolA, poolB, "Henrik Stavnem");
        String points4 = facade.calculatePoint(poolA, poolB, "Nicklas Thomsen");
        assertEquals("(1,0)", points1); 
        assertEquals("(1,1)", points2); 
        assertEquals("(0,0)", points3); 
        assertEquals("(1,2)", points4); 
    }  
    
    @Test
    public void testGetStudentsHappiness(){
        ArrayList<String> poolA = new ArrayList();
        ArrayList<String> poolB = new ArrayList();
        ArrayList<String> students = new ArrayList();
        poolA.add("Arduino");
        poolA.add("");
        poolB.add("SW Design");
        poolB.add("");
        students.add("Bjarke Carlsen");
        students.add("Martin Olgenkjær");
        students.add("Henrik Stavnem");
        students.add("Nicklas Thomsen");
        Pool p = new Pool(poolA, poolB, students);
        String json = facade.getStudentsHappiness(gson.toJson(p));
//        System.out.println(json);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();
        assertEquals(4, jasonArray.size());
    }
}
