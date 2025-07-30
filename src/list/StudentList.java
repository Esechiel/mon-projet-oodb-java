package list;

import model.Student;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentList extends JFrame {

    public StudentList() {
        setTitle("Liste des étudiants");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Nom", "Matricule", "Filière", "Âge"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        data = new Object[students.size()][columnNames.length];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getFirstName() + " " + s.getLastName();
            data[i][2] = s.getRegNum();
            data[i][3] = s.getMajor();
            data[i][4] = s.getAge();
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
