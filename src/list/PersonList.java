package list;

import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonList extends JFrame {

    public PersonList() {
        setTitle("Liste des personnes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Prénom", "Nom", "Genre", "Date de naissance", "Âge"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        data = new Object[people.size()][columnNames.length];

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            data[i][0] = p.getId();
            data[i][1] = p.getFirstName();
            data[i][2] = p.getLastName();
            data[i][3] = p.getGender();
            data[i][4] = p.getBirthDate();
            data[i][5] = p.getAge();
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
