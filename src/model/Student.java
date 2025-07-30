package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends Person {
    @Column(unique = true, nullable = false)
    private String regNum;

    private String major;

    private int marks;

    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> takes = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "student_modules",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private Set<Module> takenModules = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "enrolled_course_id")
    private Course enrolledOn;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Student tutor;

    @OneToMany(mappedBy = "tutor")
    private List<Student> tutees = new ArrayList<>();

    public Student() {}

    public Student(String regNum, String major, String firstName, String lastName, char gender, Date birthDate, int age) {
        super(firstName, lastName, gender, birthDate, age);
        this.regNum = regNum;
        this.major = major;
    }

    // Getters et Setters
    public String getRegNum() { return regNum; }
    public void setRegNum(String regNum) { this.regNum = regNum; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }

    public Set<Course> getTakes() { return takes; }
    public void setTakes(Set<Course> takes) { this.takes = takes; }

    public Set<Module> getTakenModules() { return takenModules; }
    public void setTakenModules(Set<Module> takenModules) { this.takenModules = takenModules; }

    public Course getEnrolledOn() { return enrolledOn; }
    public void setEnrolledOn(Course enrolledOn) { this.enrolledOn = enrolledOn; }

    public Student getTutor() { return tutor; }
    public void setTutor(Student tutor) { this.tutor = tutor; }

    public List<Student> getTutees() { return tutees; }
    public void setTutees(List<Student> tutees) { this.tutees = tutees; }

    @Override
    public String toString() {
        return getRegNum() + " - " + super.toString();
    }
}
