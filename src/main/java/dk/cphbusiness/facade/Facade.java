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
import java.util.List;

public class Facade implements IFacade {

    private static Facade facade = new Facade();
    private List<String> students = new ArrayList();
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

    public void createTestData() throws MinimumCharacterException {
        proposedSubjects.add(new Subject("Android", "make good apps", "PELO"));
        proposedSubjects.add(new Subject("C#", "make more apps", "TOR"));
        proposedSubjects.add(new Subject("Arduino", "what", "TOG"));
        proposedSubjects.add(new Subject("Test", "be brainy", "LAM"));
        proposedSubjects.add(new Subject("Databases", "test", "LAM"));
        proposedSubjects.add(new Subject("Prolog", "test", "AKA"));
        proposedSubjects.add(new Subject("SW Design", "test", "AKA"));
        proposedSubjects.add(new Subject("Games", "test", "CHU"));

        for (Subject subject : proposedSubjects) {
            firstRoundSubjects.add(subject);
        }

        students.add("Bjarke Carlsen");
        students.add("Martin Olgenkjær");
        students.add("Henrik Stavnem");
        students.add("Nicklas Thomsen");

        firstRoundPriorities.add(new Selected("C#", "test", "TOR", 1, students.get(0)));
        firstRoundPriorities.add(new Selected("SW Design", "test", "TOR", 1, students.get(0)));
        firstRoundPriorities.add(new Selected("Android", "test", "TOR", 2, students.get(0)));
        firstRoundPriorities.add(new Selected("Python", "test", "TOR", 2, students.get(0)));

        firstRoundPriorities.add(new Selected("Arduino", "test", "TOR", 1, students.get(1)));
        firstRoundPriorities.add(new Selected("SW Design", "test", "TOR", 1, students.get(1)));
        firstRoundPriorities.add(new Selected("Databases", "test", "TOR", 2, students.get(1)));
        firstRoundPriorities.add(new Selected("Test", "test", "TOR", 2, students.get(1)));

        firstRoundPriorities.add(new Selected("Android", "test", "TOR", 1, students.get(2)));
        firstRoundPriorities.add(new Selected("Games", "test", "TOR", 1, students.get(2)));
        firstRoundPriorities.add(new Selected("C#", "test", "TOR", 2, students.get(2)));
        firstRoundPriorities.add(new Selected("Databases", "test", "TOR", 2, students.get(2)));

        firstRoundPriorities.add(new Selected("Python", "test", "TOR", 1, students.get(3)));
        firstRoundPriorities.add(new Selected("Arduino", "test", "TOR", 1, students.get(3)));
        firstRoundPriorities.add(new Selected("SW Design", "test", "TOR", 2, students.get(3)));
        firstRoundPriorities.add(new Selected("Test", "test", "TOR", 2, students.get(3)));
    }

    @Override
    public boolean addProposal(String json) throws MinimumCharacterException {
        boolean status = false;
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
            status = true;
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }
        return status;

    }

    @Override
    public String getProposals() {
        return gson.toJson(proposedSubjects);
    }

    @Override
    public boolean addSubjectToFirstRound(String json) throws MinimumCharacterException {
        boolean status = false;
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
            status = true;
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }
        return status;
    }

    @Override
    public String getFirstRoundSubjects() {
        return gson.toJson(firstRoundSubjects);
    }

    @Override
    public boolean addToFirstRoundPriorities(String json) {
        boolean status = false;
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
            status = true;
        } catch (NullPointerException npe) {
            throw new NullPointerException("SUBJECT IS NULL");
        }
        return status;
    }

    @Override
    public String getFirstRoundPriorities() {
        return gson.toJson(firstRoundPriorities);
    }

    @Override
    public String calculatePoint(ArrayList<String> poolA, ArrayList<String> poolB, String studentID) {
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
                        if (3 > pointA) {
                            pointA = 3;
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
                        if (3 > pointB) {
                            pointB = 3;
                        }
                    }
                }

            }
        }
        String score = "";
        switch (pointA + pointB) {
            case 6:
                score = "(0,0)";
                break;
            case 10:
                score = "(2,0)";
                break;
            case 13:
                score = "(1,0)";
                break;
            case 14:
                score = "(2,2)";
                break;
            case 17:
                score = "(1,2)";
                break;
            case 20:
                score = "(1,1)";
                break;
        }
        return score;
    }

    @Override
    public String getStudentsHappiness(String json) {
        ArrayList<StudentHappiness> happinessList = new ArrayList();
        Pool p = gson.fromJson(json, Pool.class);
        ArrayList<String> poolA = p.getPoolA();
        ArrayList<String> poolB = p.getPoolB();
        for (String student : p.getStudents()) {
            String points = calculatePoint(poolA, poolB, student);
            happinessList.add(new StudentHappiness(student, points));
        }

        return gson.toJson(happinessList);
    }

}
