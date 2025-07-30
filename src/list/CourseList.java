package list;

import model.Course;
import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseList extends JFrame {

    public CourseList() {
        setTitle("Liste des cours");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Nom", "Description", "Durée", "Département"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
        data = new Object[courses.size()][columnNames.length];

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            Department d = c.getDepartment();
            data[i][0] = c.getId();
            data[i][1] = c.getName();
            data[i][2] = c.getDescription();
            data[i][3] = c.getDuration();
            data[i][4] = d != null ? d.getName() : "—";
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
