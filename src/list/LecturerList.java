package list;

import model.Lecturer;
import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LecturerList extends JFrame {

    public LecturerList() {
        setTitle("Liste des enseignants");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Nom", "Email", "Département", "Date d'embauche"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Lecturer> lecturers = em.createQuery("SELECT l FROM Lecturer l", Lecturer.class).getResultList();
        data = new Object[lecturers.size()][columnNames.length];

        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer l = lecturers.get(i);
            Department d = l.getDepartment();
            data[i][0] = l.getId();
            data[i][1] = l.getFirstName() + " " + l.getLastName();
            data[i][2] = l.getEmail();
            data[i][3] = d != null ? d.getName() : "—";
            data[i][4] = l.getHireDate();
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
