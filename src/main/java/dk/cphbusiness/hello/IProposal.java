package dk.cphbusiness.hello;

import java.util.ArrayList;

public interface IProposal {
    void addProposal(String title, String description, ArrayList teachers);
    boolean hasProposal(String title);
}
