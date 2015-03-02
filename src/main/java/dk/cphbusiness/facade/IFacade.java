package dk.cphbusiness.facade;


public interface IFacade {
    public void addProposal(String title, String describtion, String teachers);
    public String getProposals();
    public String getFirstRound();
    public void addToFirstRound();
}
