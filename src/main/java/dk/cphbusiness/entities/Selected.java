package dk.cphbusiness.entities;

public class Selected {
    
    Subject subject;
    int priority;

    public Selected(Subject subject, int priority) {
        this.subject = subject;
        this.priority = priority;
    }
    
//    public Selected(String title){
//        this.title = title;
//    }

    public Subject getSubject() {
        return subject;
    }
}
