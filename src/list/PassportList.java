package list;

import model.Passport;
import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PassportList extends JFrame {

    public PassportList() {
        setTitle("Liste des passeports");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Numéro", "Pays", "Émis le", "Expire le", "Propriétaire"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Passport> passports = em.createQuery("SELECT p FROM Passport p", Passport.class).getResultList();
        data = new Object[passports.size()][columnNames.length];

        for (int i = 0; i < passports.size(); i++) {
            Passport p = passports.get(i);
            Person owner = p.getOwner();
            data[i][0] = p.getId();
            data[i][1] = p.getNumber();
            data[i][2] = p.getCountry();
            data[i][3] = p.getIssueDate();
            data[i][4] = p.getExpiryDate();
            data[i][5] = owner != null ? owner.getFirstName() + " " + owner.getLastName() : "—";
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
