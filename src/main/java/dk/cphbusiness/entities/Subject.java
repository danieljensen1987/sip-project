package dk.cphbusiness.entities;

public class Subject {
    private String title;
    private String description;
    private String teacher;

    public Subject(String title, String description, String teacher) {
        this.title = title;
        this.description = description;
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacher() {
        return teacher;
    }
}

