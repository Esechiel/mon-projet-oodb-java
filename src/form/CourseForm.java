package form;

import model.Course;
import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseForm extends JFrame {

    public CourseForm() {
        setTitle("Ajouter un cours");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();

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

        // Ajout des champs au formulaire
        add(new JLabel("Nom du cours :")); add(nameField);
        add(new JLabel("Description :")); add(descriptionField);
        add(new JLabel("Durée (en semestres ou années) :")); add(durationField);
        add(new JLabel("Département :")); add(deptComboBox);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String description = descriptionField.getText();
                int duration = Integer.parseInt(durationField.getText());
                Department department = (Department) deptComboBox.getSelectedItem();

                Course course = new Course(name, description, duration, department);

                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em2 = emf2.createEntityManager();

                em2.getTransaction().begin();
                em2.persist(course);
                em2.getTransaction().commit();

                em2.close();
                emf2.close();

                JOptionPane.showMessageDialog(this, "Cours ajouté !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
