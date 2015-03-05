package dk.cphbusiness.entities;

public class Selected {
    
    Subject subject;
    int priority;

    public Selected(Subject subject, int priority) {
        this.subject = subject;
        this.priority = priority;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getPriority() {
        return priority;
    }  
}
