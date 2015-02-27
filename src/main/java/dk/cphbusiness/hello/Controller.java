package dk.cphbusiness.hello;

import dk.cphbusiness.entities.Subject;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    
    private List <Subject> proposedSubjects = new ArrayList();
    private List firstRound = new ArrayList();
    
    public void addProposal(String title, String describtion, List teachers){
        Subject subject = new Subject(title, describtion, teachers);
        proposedSubjects.add(subject);
    }

    public List<Subject> getProposedSubjects() {
        return proposedSubjects;
    }

    public List getFirstRound() {
        return firstRound;
    }
    
    public void AddToFirstRound(String title){
        firstRound.add(title);
    }
}
