package dk.cphbusiness.hello;

import java.util.ArrayList;

public class ProposalStub implements IProposal{
    private ArrayList proposals = new ArrayList();

    @Override
    public void addProposal(String title, String description, ArrayList teachers) {
        Subject sub = new Subject(title, description, teachers);
        proposals.add(sub);
    }

    @Override
    public boolean hasProposal(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
