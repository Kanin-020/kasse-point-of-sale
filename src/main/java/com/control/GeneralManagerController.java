package com.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.UserDAO;
import com.data.User;
import com.view.GeneralManagerView;
import com.view.LoginView;
import com.view.AddUserView;
import com.view.VistaVentanaInventarista;
import com.view.VistaVentanaVendedor;

public class GeneralManagerController implements ActionListener {

    private GeneralManagerView generalManagerView;

    private DefaultTableModel userTableModel;

    public GeneralManagerController(GeneralManagerView generalManagerView) {

        this.generalManagerView = generalManagerView;

        updateTable();

        this.generalManagerView.getBotonAgregar().addActionListener(this);
        this.generalManagerView.getBotonEliminar().addActionListener(this);
        this.generalManagerView.getBotonInventario().addActionListener(this);
        this.generalManagerView.getBotonVentas().addActionListener(this);
        this.generalManagerView.getBotonSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == generalManagerView.getBotonAgregar()) {
            openAddUserView();
        } else if (source == generalManagerView.getBotonEliminar()) {
            deleteUser();
        } else if (source == generalManagerView.getBotonInventario()) {
            VistaVentanaInventarista vistaVentanaInventarista = new VistaVentanaInventarista();
            changeWindow(vistaVentanaInventarista, new ControlVentanaInventarista(vistaVentanaInventarista));
        } else if (source == generalManagerView.getBotonVentas()) {
            VistaVentanaVendedor vistaVentanaVendedor = new VistaVentanaVendedor();
            changeWindow(vistaVentanaVendedor, new ControlVentanaVendedor(vistaVentanaVendedor));
        } else if (source == generalManagerView.getBotonSalir()) {
            logout();
        }
    }

    private void openAddUserView() {
        AddUserView addUserView = new AddUserView();
        new AddUserController(addUserView, userTableModel);
        addUserView.setVisible(true);
    }

    private void deleteUser() {

        int index = generalManagerView.getTablaUsuarios().getSelectedRow();

        if (index == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un usuario",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int message = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de eliminar el usuario?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (message == JOptionPane.YES_OPTION) {
            try {

                User user = new User(
                        generalManagerView.getTablaUsuarios().getValueAt(index, 0).toString(),
                        generalManagerView.getTablaUsuarios().getValueAt(index, 1).toString());

                UserDAO.delete(user);

                updateTable();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al eliminar usuario: " + exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void changeWindow(javax.swing.JFrame view, Object controller) {

        int message = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de cambiar de modo?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (message == JOptionPane.YES_OPTION) {
            generalManagerView.dispose();
            view.setVisible(true);

            System.out.println(controller + " Activated");
        }
    }

    private void logout() {

        int message = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de cerrar sesión?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (message == JOptionPane.YES_OPTION) {
            generalManagerView.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }

    private void updateTable() {
        try {

            userTableModel = (DefaultTableModel) this.generalManagerView.getTablaUsuarios()
                    .getModel();

            userTableModel.setRowCount(0);

            ArrayList<User> userList = UserDAO.selectAll();

            for (User user : userList) {
                Object[] rows = { user.getUsername(), user.getPassword(), user.getPosition() };
                userTableModel.addRow(rows);
            }

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al cargar la tabla de usuarios: " + exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
