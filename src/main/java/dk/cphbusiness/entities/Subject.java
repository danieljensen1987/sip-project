/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.entities;

import dk.cphbusiness.entities.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Søren
 */
public class Subject {
    private String title;
    private String description;
    private List <Teacher> teachers = new ArrayList();

    public Subject(String title, String description, List teachers) {
        this.title = title;
        this.description = description;
        this.teachers = teachers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }    
  
}

