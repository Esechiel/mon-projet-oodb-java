package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Lecturer extends Person {

    private String email;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @ManyToOne
    private Department department;

    @ManyToMany(mappedBy = "taughtBy")
    private Set<Module> modules = new HashSet<>();

    public Lecturer() {}

    public Lecturer(String firstName, String lastName, char gender, Date birthDate, int age, String email, Department department) {
        super(firstName, lastName, gender, birthDate, age);
        this.email = email;
        this.hireDate = new Date(); // par défaut la date du jour
        this.department = department;
    }

    // Getters et Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + email;
    }
}
