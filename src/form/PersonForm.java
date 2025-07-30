package form;

import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class PersonForm extends JFrame {

    public PersonForm() {
        setTitle("Ajouter une personne");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField firstNameField = new JTextField();
        JTextField middleNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField birthDateField = new JTextField(); // yyyy-MM-dd
        JTextField ageField = new JTextField();

        JButton saveBtn = new JButton("Enregistrer");

        add(new JLabel("Prénom :")); add(firstNameField);
        add(new JLabel("Deuxième prénom :")); add(middleNameField);
        add(new JLabel("Nom :")); add(lastNameField);
        add(new JLabel("Sexe (M/F) :")); add(genderField);
        add(new JLabel("Date de naissance (yyyy-MM-dd) :")); add(birthDateField);
        add(new JLabel("Âge :")); add(ageField);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                char gender = genderField.getText().charAt(0);
                Date birthDate = Date.valueOf(birthDateField.getText());
                int age = Integer.parseInt(ageField.getText());

                Person p = new Person(firstName, lastName, gender, birthDate, age);
                p.setMiddleName(middleName);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();

                em.close();
                emf.close();

                JOptionPane.showMessageDialog(this, "Personne ajoutée !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
