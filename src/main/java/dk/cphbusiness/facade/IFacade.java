package dk.cphbusiness.facade;

import dk.cphbusiness.exceptions.MinimumCharacterException;


public interface IFacade {
    public void addProposal(String json) throws MinimumCharacterException;
    public String getProposals();
    public void addSubjectToFirstRound(String json) throws MinimumCharacterException;
    public String getFirstRoundSubjects();
    public void addToFirstRoundPriorities(String json);
    public String getFirstRoundPriorities();
    public int calculatePoint(String poola, String poolb, String studentID);
}
