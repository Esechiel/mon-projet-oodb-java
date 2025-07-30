package form;

import model.Address;
import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddressForm extends JFrame {

    public AddressForm() {
        setTitle("Ajouter une adresse");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField streetField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField countryField = new JTextField();
        JTextField postalCodeField = new JTextField();

        JComboBox<Person> personComboBox = new JComboBox<>();

        JButton saveBtn = new JButton("Enregistrer");

        // Charger les personnes depuis la base
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        for (Person p : people) {
            personComboBox.addItem(p);
        }

        em.close();
        emf.close();

        // Construction du formulaire
        add(new JLabel("Rue :")); add(streetField);
        add(new JLabel("Ville :")); add(cityField);
        add(new JLabel("Pays :")); add(countryField);
        add(new JLabel("Code postal :")); add(postalCodeField);
        add(new JLabel("Propriétaire :")); add(personComboBox);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String street = streetField.getText();
                String city = cityField.getText();
                String country = countryField.getText();
                String postalCode = postalCodeField.getText();
                Person owner = (Person) personComboBox.getSelectedItem();

                Address address = new Address(street, city, country, postalCode, owner);

                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em2 = emf2.createEntityManager();

                em2.getTransaction().begin();
                em2.persist(address);
                em2.getTransaction().commit();

                em2.close();
                emf2.close();

                JOptionPane.showMessageDialog(this, "Adresse ajoutée !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
