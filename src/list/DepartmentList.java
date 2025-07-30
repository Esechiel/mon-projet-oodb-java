package list;

import model.Department;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DepartmentList extends JFrame {

    public DepartmentList() {
        setTitle("Liste des départements");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Nom", "Description"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        data = new Object[departments.size()][columnNames.length];

        for (int i = 0; i < departments.size(); i++) {
            Department d = departments.get(i);
            data[i][0] = d.getId();
            data[i][1] = d.getName();
            data[i][2] = d.getDescription();
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
