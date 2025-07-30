package form;

import model.Passport;
import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class PassportForm extends JFrame {

    public PassportForm() {
        setTitle("Ajouter un passeport");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField numberField = new JTextField();
        JTextField countryField = new JTextField();
        JTextField issueDateField = new JTextField(); // format: yyyy-MM-dd
        JTextField expiryDateField = new JTextField(); // format: yyyy-MM-dd

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

        // Formulaire
        add(new JLabel("Numéro :")); add(numberField);
        add(new JLabel("Pays :")); add(countryField);
        add(new JLabel("Date d'émission (yyyy-MM-dd) :")); add(issueDateField);
        add(new JLabel("Date d'expiration (yyyy-MM-dd) :")); add(expiryDateField);
        add(new JLabel("Propriétaire :")); add(personComboBox);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String number = numberField.getText();
                String country = countryField.getText();
                Date issueDate = Date.valueOf(issueDateField.getText());
                Date expiryDate = Date.valueOf(expiryDateField.getText());
                Person owner = (Person) personComboBox.getSelectedItem();

                Passport passport = new Passport(number, country, issueDate, expiryDate, owner);

                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em2 = emf2.createEntityManager();

                em2.getTransaction().begin();
                em2.persist(passport);
                em2.getTransaction().commit();

                em2.close();
                emf2.close();

                JOptionPane.showMessageDialog(this, "Passeport ajouté !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
