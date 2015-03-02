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

    public static FacadeDB getFacade(boolean b) {
        if (true) {
            facade = new FacadeDB();
        }
        return facade;
    }

    @Override
    public void addProposal(String title, String describtion, String teachers) {
        Subject subject = new Subject(title, describtion, teachers);
        proposedSubjects.add(subject);
    }

    @Override
    public String getProposals() {
        Gson gson = new Gson();
        return gson.toJson(proposedSubjects);
    }

    @Override
    public String getFirstRound() {
        Gson gson = new Gson();
        return gson.toJson(firstRound);
    }

    @Override
    public void addToFirstRound(String title) {
        firstRound.add(title);
    }

}
