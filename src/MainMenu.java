import form.*;
import list.*;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Menu Principal");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 10, 10));

        // Boutons d’ajout
        JButton addPersonBtn = new JButton("Ajouter Personne");
        JButton addStudentBtn = new JButton("Ajouter Étudiant");
        JButton addLecturerBtn = new JButton("Ajouter Enseignant");
        JButton addAddressBtn = new JButton("Ajouter Adresse");
        JButton addPassportBtn = new JButton("Ajouter Passeport");
        JButton addDepartmentBtn = new JButton("Ajouter Département");
        JButton addCourseBtn = new JButton("Ajouter Cours");
        JButton addModuleBtn = new JButton("Ajouter Module");

        // Boutons de listing
        JButton listPersonBtn = new JButton("Liste des Personnes");
        JButton listStudentBtn = new JButton("Liste des Étudiants");
        JButton listLecturerBtn = new JButton("Liste des Enseignants");
        JButton listAddressBtn = new JButton("Liste des Adresses");
        JButton listPassportBtn = new JButton("Liste des Passeports");
        JButton listDepartmentBtn = new JButton("Liste des Départements");
        JButton listCourseBtn = new JButton("Liste des Cours");
        JButton listModuleBtn = new JButton("Liste des Modules");

        // Ajout des boutons au panneau
        add(addPersonBtn);
        add(addStudentBtn);
        add(addLecturerBtn);
        add(addAddressBtn);
        add(addPassportBtn);
        add(addDepartmentBtn);
        add(addCourseBtn);
        add(addModuleBtn);

        add(listPersonBtn);
        add(listStudentBtn);
        add(listLecturerBtn);
        add(listAddressBtn);
        add(listPassportBtn);
        add(listDepartmentBtn);
        add(listCourseBtn);
        add(listModuleBtn);

        // Actions
        addPersonBtn.addActionListener(e -> new PersonForm());
        addStudentBtn.addActionListener(e -> new StudentForm());
        addLecturerBtn.addActionListener(e -> new LecturerForm());
        addAddressBtn.addActionListener(e -> new AddressForm());
        addPassportBtn.addActionListener(e -> new PassportForm());
        addDepartmentBtn.addActionListener(e -> new DepartmentForm());
        addCourseBtn.addActionListener(e -> new CourseForm());
        addModuleBtn.addActionListener(e -> new ModuleForm());

        listPersonBtn.addActionListener(e -> new PersonList());
        listStudentBtn.addActionListener(e -> new StudentList());
        listLecturerBtn.addActionListener(e -> new LecturerList());
        listAddressBtn.addActionListener(e -> new AddressList());
        listPassportBtn.addActionListener(e -> new PassportList());
        listDepartmentBtn.addActionListener(e -> new DepartmentList());
        listCourseBtn.addActionListener(e -> new CourseList());
        listModuleBtn.addActionListener(e -> new ModuleList());

        setVisible(true);
    }
}
