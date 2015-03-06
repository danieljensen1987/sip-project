package dk.cphbusiness.facade;

import dk.cphbusiness.exceptions.MinimumCharacterException;


public interface IFacade {
    public void addProposal(String json) throws MinimumCharacterException;
    public String getProposals();
    public void addSubjectToFirstRound(String json) throws MinimumCharacterException;
    public String getFirstRoundSubjects();
    public void addToFirstRound(String json);
    public String getFirstRound();
}
