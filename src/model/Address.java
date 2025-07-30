package model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String country;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

    public Address() {}

    public Address(String street, String city, String country, String postalCode, Person owner) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.owner = owner;
    }

    // Getters et setters
    public Long getId() { return id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    @Override
    public String toString() {
        return street + ", " + city + ", " + country;
    }
}
