package form;

import model.Student;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class StudentForm extends JFrame {

    public StudentForm() {
        setTitle("Ajouter un étudiant");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField regNumField = new JTextField();
        JTextField majorField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField birthDateField = new JTextField(); // format: yyyy-MM-dd

        JButton saveBtn = new JButton("Enregistrer");

        add(new JLabel("Matricule :")); add(regNumField);
        add(new JLabel("Filière :")); add(majorField);
        add(new JLabel("Prénom :")); add(firstNameField);
        add(new JLabel("Nom :")); add(lastNameField);
        add(new JLabel("Sexe (M/F) :")); add(genderField);
        add(new JLabel("Âge :")); add(ageField);
        add(new JLabel("Date de naissance (yyyy-MM-dd) :")); add(birthDateField);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String regNum = regNumField.getText();
                String major = majorField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                char gender = genderField.getText().charAt(0);
                int age = Integer.parseInt(ageField.getText());
                Date birthDate = Date.valueOf(birthDateField.getText());

                Student student = new Student(regNum, major, firstName, lastName, gender, birthDate, age);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();
                em.persist(student);
                em.getTransaction().commit();

                em.close();
                emf.close();

                JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
