package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "department")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturers = new ArrayList<>();

    public Department() {}

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    public List<Lecturer> getLecturers() { return lecturers; }
    public void setLecturers(List<Lecturer> lecturers) { this.lecturers = lecturers; }

    @Override
    public String toString() {
        return name;
    }
}
