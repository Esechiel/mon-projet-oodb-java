package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private char gender;
    private Integer age;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Passport> passports = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Person() {}

    public Person(String firstName, String lastName, char gender, Date birthDate, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public List<Passport> getPassports() { return passports; }
    public void setPassports(List<Passport> passports) { this.passports = passports; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    @Override
    public String toString() {
        return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
    }
}
