package form;

import model.Module;
import model.Lecturer;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ModuleForm extends JFrame {

    public ModuleForm() {
        setTitle("Ajouter un module");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField semesterField = new JTextField();

        DefaultListModel<Lecturer> lecturerListModel = new DefaultListModel<>();
        JList<Lecturer> lecturerJList = new JList<>(lecturerListModel);
        lecturerJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane lecturerScroll = new JScrollPane(lecturerJList);

        // Charger les enseignants
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Lecturer> lecturers = em.createQuery("SELECT l FROM Lecturer l", Lecturer.class).getResultList();
        for (Lecturer l : lecturers) {
            lecturerListModel.addElement(l);
        }

        em.close();
        emf.close();

        JButton saveBtn = new JButton("Enregistrer");

        formPanel.add(new JLabel("Code du module :")); formPanel.add(codeField);
        formPanel.add(new JLabel("Nom du module :")); formPanel.add(nameField);
        formPanel.add(new JLabel("Semestre :")); formPanel.add(semesterField);
        formPanel.add(new JLabel("Enseignants :")); formPanel.add(lecturerScroll);

        add(formPanel, BorderLayout.CENTER);
        add(saveBtn, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> {
            try {
                String code = codeField.getText();
                String name = nameField.getText();
                String semester = semesterField.getText();
                List<Lecturer> selectedLecturers = lecturerJList.getSelectedValuesList();

                Module module = new Module(code, name, semester);
                Set<Lecturer> lecturersSet = new HashSet<>(selectedLecturers);
                module.setTaughtBy(lecturersSet);

                EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
                EntityManager em2 = emf2.createEntityManager();

                em2.getTransaction().begin();
                em2.persist(module);
                em2.getTransaction().commit();

                em2.close();
                emf2.close();

                JOptionPane.showMessageDialog(this, "Module ajouté !");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
