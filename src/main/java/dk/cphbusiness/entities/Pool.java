package dk.cphbusiness.entities;

import java.util.ArrayList;

public class Pool {
    private ArrayList<String> poolA;
    private ArrayList<String> poolB;
    private ArrayList<String> studentID;

    public Pool(ArrayList<String> poolA, ArrayList<String> poolB, ArrayList<String> studentID) {
        this.poolA = poolA;
        this.poolB = poolB;
        this.studentID = studentID;
    }   

    public ArrayList<String> getPoolA() {
        return poolA;
    }

    public ArrayList<String> getPoolB() {
        return poolB;
    }

    public ArrayList<String> getStudents() {
        return studentID;
    }
}
