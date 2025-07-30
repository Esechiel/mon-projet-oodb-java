package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    private String country;

    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    public Passport() {}

    public Passport(String number, String country, Date issueDate, Date expiryDate, Person owner) {
        this.number = number;
        this.country = country;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.owner = owner;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    @Override
    public String toString() {
        return number + " - " + country;
    }
}
