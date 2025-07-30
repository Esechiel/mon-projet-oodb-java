package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private int duration; // en années ou semestres

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "enrolledOn")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "takes")
    private Set<Student> enrolledStudents = new HashSet<>();

    public Course() {}

    public Course(String name, String description, int duration, Department department) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.department = department;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public Set<Student> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(Set<Student> enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    @Override
    public String toString() {
        return name;
    }
}
