package list;

import model.Address;
import model.Person;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddressList extends JFrame {

    public AddressList() {
        setTitle("Liste des adresses");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Rue", "Ville", "Pays", "Code postal", "Propriétaire"};
        Object[][] data;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/edu.odb");
        EntityManager em = emf.createEntityManager();

        List<Address> addresses = em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
        data = new Object[addresses.size()][columnNames.length];

        for (int i = 0; i < addresses.size(); i++) {
            Address a = addresses.get(i);
            Person owner = a.getOwner();
            data[i][0] = a.getId();
            data[i][1] = a.getStreet();
            data[i][2] = a.getCity();
            data[i][3] = a.getCountry();
            data[i][4] = a.getPostalCode();
            data[i][5] = owner != null ? owner.getFirstName() + " " + owner.getLastName() : "—";
        }

        em.close();
        emf.close();

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
