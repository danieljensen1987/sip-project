package dk.cphbusiness.hello;

import java.util.ArrayList;
import java.util.List;

public class Proposal {
    
    public List <Subject> proposedSubjects = new ArrayList();
    
    public void addProposal(String title, String describtion, List teachers){
        Subject subject = new Subject(title, describtion, teachers);
        proposedSubjects.add(subject);
    }
}
