package form;

import model.Lecturer;
import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class LecturerForm extends JFrame {

    public LecturerForm() {
        setTitle("Ajouter un enseignant");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField birthDateField = new JTextField(); // yyyy-MM-dd
        JTextField ageField = new JTextField();
        JTextField emailField = new JTextField();

        JComboBox<Department> deptComboBox = new JComboBox<>();

        JButton saveBtn = new JButton("Enregistrer");

        // Charger les départements
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        for (Department d : departments) {
            deptComboBox.addItem(d);
        }

        em.close();
        emf.close();

        // Construction du formulaire
        add(new JLabel("Prénom :")); add(firstNameField);
        add(new JLabel("Nom :")); add(lastNameField);
        add(new JLabel("Sexe (M/F) :")); add(genderField);
        add(new JLabel("Date de naissance (yyyy-MM-dd) :")); add(birthDateField);
        add(new JLabel("Âge :")); add(ageField);
        add(new JLabel("Email :")); add(emailField);
        add(new JLabel("Département :")); add(deptComboBox);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                char gender = genderField.getText().charAt(0);
                Date birthDate = Date.valueOf(birthDateField.getText());
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();
                Department department = (Department) deptComboBox.getSelectedItem();

                Lecturer lecturer = new Lecturer(firstName, lastName, gender, birthDate, age, email, department);

                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em2 = emf2.createEntityManager();

                em2.getTransaction().begin();
                em2.persist(lecturer);
                em2.getTransaction().commit();

                em2.close();
                emf2.close();

                JOptionPane.showMessageDialog(this, "Enseignant ajouté !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
