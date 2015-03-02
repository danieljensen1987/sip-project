package dk.cphbusiness.facade;

import com.google.gson.Gson;
import dk.cphbusiness.entities.Subject;
import java.util.ArrayList;
import java.util.List;

public class FacadeDB implements IFacade {

    private static FacadeDB facade = new FacadeDB();
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAcryptPU");
//    EntityManager em = emf.createEntityManager();
    private List<Subject> proposedSubjects = new ArrayList();
    private List firstRound = new ArrayList();
    private final Gson gson = new Gson();

    public static FacadeDB getFacade(boolean b) {
        if (true) {
            facade = new FacadeDB();
        }
        return facade;
    }

    @Override
    public void addProposal(String json) {
        Subject subject = gson.fromJson(json, Subject.class);
        proposedSubjects.add(subject);
    }

    @Override
    public String getProposals() {
        
        return gson.toJson(proposedSubjects);
    }

    @Override
    public String getFirstRound() {
        return gson.toJson(firstRound);
    }

    @Override
    public void addToFirstRound(String title) {
        firstRound.add(title);
    }

}
