package dk.cphbusiness.facade;

import com.google.gson.Gson;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.util.ArrayList;
import java.util.List;

public class Facade implements IFacade {

    private static Facade facade = new Facade();
    private List<Subject> proposedSubjects = new ArrayList();
    private List<Subject> firstRoundSubjects = new ArrayList();
    private List<Selected> firstRound = new ArrayList();
    private final Gson gson = new Gson();

    public static Facade getFacade(boolean b) {
        if (true) {
            facade = new Facade();
        }
        return facade;
    }

    @Override
    public void addProposal(String json) throws MinimumCharacterException {
        try {
            Subject subject = gson.fromJson(json, Subject.class);
            if (subject.getTitle() == null || subject.getTitle().length() < 1) {
                throw new MinimumCharacterException("Title must be atleast 2 characters long");

            }
            proposedSubjects.add(subject);
        } catch(NullPointerException npe){
            throw new NullPointerException("SUBJECT IS NULL");
        }

    }

    @Override
    public String getProposals() {
        return gson.toJson(proposedSubjects);
    }

    @Override
    public void addSubjectToFirstRound(String json) {
        try{
            Subject subject = gson.fromJson(json, Subject.class);
            firstRoundSubjects.add(subject);
        } catch(NullPointerException npe){
            throw new NullPointerException("SUBJECT IS NULL");
        }
    }

    @Override
    public String getFirstRoundSubjects() {
        return gson.toJson(firstRoundSubjects);
    }

    @Override
    public void addToFirstRound(String json) {
        Selected s = gson.fromJson(json, Selected.class);
        firstRound.add(s);
    }

    @Override
    public String getFirstRound() {
        return gson.toJson(firstRound);
    }

}
