package form;

import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;

public class DepartmentForm extends JFrame {

    public DepartmentForm() {
        setTitle("Ajouter un département");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();

        JButton saveBtn = new JButton("Enregistrer");

        add(new JLabel("Nom du département :")); add(nameField);
        add(new JLabel("Description :")); add(descriptionField);
        add(new JLabel("")); add(saveBtn);

        saveBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String description = descriptionField.getText();

                Department department = new Department(name, description);

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em = emf.createEntityManager();

                em.getTransaction().begin();
                em.persist(department);
                em.getTransaction().commit();

                em.close();
                emf.close();

                JOptionPane.showMessageDialog(this, "Département ajouté !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
