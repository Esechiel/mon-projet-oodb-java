package list;

import model.Module;
import model.Lecturer;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleList extends JFrame {

    public ModuleList() {
        setTitle("Liste des modules");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Nom", "Code", "Semestre", "Enseignants"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Module> modules = em.createQuery("SELECT m FROM Module m", Module.class).getResultList();
        data = new Object[modules.size()][columnNames.length];

        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            Set<Lecturer> lecturers = m.getTaughtBy();
            String lecturersNames = lecturers.stream()
                .map(l -> l.getFirstName() + " " + l.getLastName())
                .collect(Collectors.joining(", "));

            data[i][0] = m.getId();
            data[i][1] = m.getName();
            data[i][2] = m.getCode();
            data[i][3] = m.getSemester();
            data[i][4] = lecturersNames.isEmpty() ? "—" : lecturersNames;
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
