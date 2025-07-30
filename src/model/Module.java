package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String semester;

    @ManyToMany
    @JoinTable(
        name = "module_lecturers",
        joinColumns = @JoinColumn(name = "module_id"),
        inverseJoinColumns = @JoinColumn(name = "lecturer_id")
    )
    private Set<Lecturer> taughtBy = new HashSet<>();

    @ManyToMany(mappedBy = "takenModules")
    private Set<Student> students = new HashSet<>();

    public Module() {}

    public Module(String code, String name, String semester) {
        this.code = code;
        this.name = name;
        this.semester = semester;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public Set<Lecturer> getTaughtBy() { return taughtBy; }
    public void setTaughtBy(Set<Lecturer> taughtBy) { this.taughtBy = taughtBy; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}
