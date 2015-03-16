package dk.cphbusiness.facade;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dk.cphbusiness.entities.Pool;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.StudentHappiness;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Facade implements IFacade {

    private static Facade facade = new Facade();
    private List<Subject> proposedSubjects = new ArrayList();
    private List<Subject> firstRoundSubjects = new ArrayList();
    private List<Selected> firstRoundPriorities = new ArrayList();
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
            if (subject.getTitle() == null || subject.getTitle().isEmpty()) {
                throw new MinimumCharacterException("Title must not be empty");
            }
            if (subject.getDescription() == null || subject.getDescription().isEmpty()) {
                throw new MinimumCharacterException("Description must not be empty");
            }
            if (subject.getTeacher() == null || subject.getDescription().isEmpty()) {
                throw new MinimumCharacterException("Teacher must not be empty");
            }
            proposedSubjects.add(subject);
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }

    }

    @Override
    public String getProposals() {
        return gson.toJson(proposedSubjects);
    }

    @Override
    public void addSubjectToFirstRound(String json) throws MinimumCharacterException {
        try {
            Subject subject = gson.fromJson(json, Subject.class);
            if (subject.getTitle() == null || subject.getTitle().isEmpty()) {
                throw new MinimumCharacterException("Title must not be empty");
            }
            if (subject.getDescription() == null || subject.getDescription().isEmpty()) {
                throw new MinimumCharacterException("Description must not be empty");
            }
            if (subject.getTeacher() == null || subject.getDescription().isEmpty()) {
                throw new MinimumCharacterException("Teacher must not be empty");
            }
            firstRoundSubjects.add(subject);
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }
    }

    @Override
    public String getFirstRoundSubjects() {
        return gson.toJson(firstRoundSubjects);
    }

    @Override
    public void addToFirstRoundPriorities(String json) {
        try {
            int first = 0;
            int second = 0;

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            JsonArray jasonArray = element.getAsJsonArray();
            if (jasonArray.size() != 4) {
                throw new IllegalArgumentException("Only 4 priorities must be selected");
            }
            for (int i = 0; i < jasonArray.size(); i++) {
                Selected s = gson.fromJson(jasonArray.get(i), Selected.class);
                if (s.getPriority() == 1) {
                    first++;
                }
                if (s.getPriority() == 2) {
                    second++;
                }
            }
            if (first != 2 && second != 2) {
                throw new IllegalArgumentException("Only two of first and second priority must be selected");
            }
            for (int i = 0; i < jasonArray.size(); i++) {
                Selected s = gson.fromJson(jasonArray.get(i), Selected.class);
                firstRoundPriorities.add(s);
            }
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }
    }

    @Override
    public String getFirstRoundPriorities() {
        return gson.toJson(firstRoundPriorities);
    }

    @Override
    public int calculatePoint(ArrayList<String> poolA, ArrayList<String> poolB, String studentID) {
        int pointA = 0;
        int pointB = 0;

        for (Selected selected : firstRoundPriorities) {
            if (selected.getStudentId().equals(studentID)) {
                for (String a : poolA) {
                    if (a.equals(selected.getTitle())) {
                        switch (selected.getPriority()) {
                            case 1:
                                if (10 > pointA) {
                                    pointA = 10;
                                }
                                break;
                            case 2:
                                if (7 > pointA) {
                                    pointA = 7;
                                }
                                break;
                        }
                    } else {
                        if(4 > pointA){
                            pointA = 4;
                        }
                        
                    }
                }
                for (String b : poolB) {
                    if (b.equals(selected.getTitle())) {
                        switch (selected.getPriority()) {
                            case 1:
                                if (10 > pointB) {
                                    pointB = 10;
                                }
                                break;
                            case 2:
                                if (7 > pointB) {
                                    pointB = 7;
                                }
                                break;
                        }
                    } else {
                        if(4 > pointB){
                            pointB = 4;
                        }
                    }
                }

            }
        }
        return pointA + pointB;
    }

    @Override
    public String getCalculatedPoints(String json) {
        HashMap<String, Integer> happinessList = new HashMap();
        ArrayList<StudentHappiness> sh = new ArrayList();
        
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jasonArray = element.getAsJsonArray();

        for (int i = 0; i < jasonArray.size(); i++) {
            Pool p = gson.fromJson(jasonArray.get(i), Pool.class);
            sh.add(new StudentHappiness(p.getStudentID(), calculatePoint(p.getPoolA(), p.getPoolB(), p.getStudentID())));
        }
        
        return gson.toJson(sh);
    }

}
