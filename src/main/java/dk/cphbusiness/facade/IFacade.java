package dk.cphbusiness.facade;

import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.util.ArrayList;


public interface IFacade {
    public void addProposal(String json) throws MinimumCharacterException;
    public String getProposals();
    public void addSubjectToFirstRound(String json) throws MinimumCharacterException;
    public String getFirstRoundSubjects();
    public void addToFirstRoundPriorities(String json);
    public String getFirstRoundPriorities();
    public int calculatePoint(ArrayList<String> poolA, ArrayList<String> poolB, String studentID);
}
