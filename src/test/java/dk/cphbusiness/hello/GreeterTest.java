package dk.cphbusiness.hello;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class GreeterTest {
    
    public Student student = new Student("1");
    private List<Teacher> teacherList = new ArrayList();
    
    private Teacher teacher1 = new Teacher("1");
    private Teacher teacher2 = new Teacher("2");

    @Test
    public void testCreateStudent() {
        assertNotNull(student);

    }

    @Test
    public void testCreateTeacher() {
        assertNotNull(teacher1);

    }

    @Test
    public void testCreateSubject() {
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        Subject subject1 = new Subject("testTitle", "testDesc", teacherList);
        
        assertEquals(subject1.getTeachers().size(), 2);
        assertEquals(subject1.getTitle(), "testTitle");
    }
}
