package dk.cphbusiness.facade;


public interface IFacade {
    public void addProposal(String json);
    public String getProposals();
    public String getFirstRound();
    public void addToFirstRound(String json);
}
