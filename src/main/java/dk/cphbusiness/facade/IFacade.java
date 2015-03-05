package dk.cphbusiness.facade;


public interface IFacade {
    public void addProposal(String json);
    public String getProposals();
    public String getAvailableCourses();
    public void addToAvailableCourses(String json);
    public void addToFirstRound(String json);
    public String getFirstRound();
}
