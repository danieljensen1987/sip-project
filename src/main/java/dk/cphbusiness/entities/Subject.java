package dk.cphbusiness.entities;

import dk.cphbusiness.entities.Teacher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Subject {
    private String title;
    private String description;
    private Collection<Teacher> teachers = new ArrayList();
//    private String teachers;

    public Subject(String title, String description, Collection teachers) {
        this.title = title;
        this.description = description;
        this.teachers = teachers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public String toString() {
        return "Subject{" + "title=" + title
                + ", description=" + description
                + ", teachers={" + teachers + "}"
                + "}";
    }
}

