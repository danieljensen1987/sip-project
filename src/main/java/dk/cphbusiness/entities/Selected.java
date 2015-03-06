package dk.cphbusiness.entities;

public class Selected extends Subject{
    int priority;

    public Selected(String title, String description, String teacher, int priority) {
        super(title, description, teacher);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }  
}
