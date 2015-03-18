package dk.cphbusiness.facade;

import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.util.ArrayList;


public interface IFacade {
    public boolean addProposal(String json) throws MinimumCharacterException;
    public String getProposals();
    public boolean addSubjectToFirstRound(String json) throws MinimumCharacterException;
    public String getFirstRoundSubjects();
    public boolean addToFirstRoundPriorities(String json);
    public String getFirstRoundPriorities();
    public int calculatePoint(ArrayList<String> poolA, ArrayList<String> poolB, String studentID);
    public String getStudentsHappiness(String json);
}
