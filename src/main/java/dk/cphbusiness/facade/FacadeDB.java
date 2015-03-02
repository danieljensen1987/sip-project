package dk.cphbusiness.facade;



public class FacadeDB implements IFacade{
    
    private static FacadeDB facade = new FacadeDB();
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAcryptPU");
//    EntityManager em = emf.createEntityManager();
    
    public static FacadeDB getFacade(boolean b){
        if (true){
            facade = new FacadeDB();
        }
        return facade;
    }

    @Override
    public void addProposal(String title, String describtion, String teachers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProposals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFirstRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToFirstRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
