package com.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.dao.UserDAO;
import com.data.User;
import com.view.AddUserView;

public class AddUserController implements ActionListener {

    private AddUserView addUserView;
    private DefaultTableModel userTable;

    public AddUserController(AddUserView addUserView, DefaultTableModel userTable) {
        this.addUserView = addUserView;
        this.userTable = userTable;

        this.addUserView.getBotonAgregar().addActionListener(this);
        this.addUserView.getBotonCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object source = evento.getSource();

        if (source == addUserView.getBotonAgregar()) {
            addUser();
        } else if (source == addUserView.getBotonCancelar()) {
            cancel();
        }
    }

    private void addUser() {

        try {

            String username = addUserView.getFieldUsuario().getText().trim();
            String password = addUserView.getFieldContraseña().getText().trim();
            String position = addUserView.getComboBoxCargo().getSelectedItem().toString();

            if (username.isEmpty() || password.isEmpty() || position.isEmpty() || position.equals("None")) {

                JOptionPane.showMessageDialog(
                        addUserView,
                        "Los campos no pueden estar vacíos",
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            User user = new User(username, password, position);

            UserDAO.add(user);

            userTable.addRow(new Object[] { user.getUsername(), user.getPassword(), user.getPosition() });

            JOptionPane.showMessageDialog(
                    addUserView,
                    "Usuario agregado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            addUserView.dispose();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(addUserView, "Error al agregar usuario: " + exception.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        addUserView.dispose();
    }
}