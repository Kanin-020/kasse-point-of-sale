package com.control;

import static com.utils.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.dao.UserDAO;
import com.data.User;
import com.view.GeneralManagerView;
import com.view.LoginView;
import com.view.AddUserView;
import com.view.InventoryManagerView;
import com.view.SellerView;

public class GeneralManagerController implements ActionListener {

    private GeneralManagerView generalManagerView;
    private DefaultTableModel userTable;

    public GeneralManagerController(GeneralManagerView generalManagerView) {
        this.generalManagerView = generalManagerView;
        this.userTable = (DefaultTableModel) generalManagerView.getUserTable().getModel();

        updateTable();

        addActionListeners();
    }

    private void addActionListeners() {
        generalManagerView.getAddButton().addActionListener(this);
        generalManagerView.getDeleteButton().addActionListener(this);
        generalManagerView.getInventoryManagerButton().addActionListener(this);
        generalManagerView.getSellerButton().addActionListener(this);
        generalManagerView.getLogoutButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == generalManagerView.getAddButton()) {
            openAddUserView();
        } else if (source == generalManagerView.getDeleteButton()) {
            deleteUser();
        } else if (source == generalManagerView.getInventoryManagerButton()) {
            InventoryManagerView inventoryManagerView = new InventoryManagerView();
            changeWindow(inventoryManagerView, new InventoryManagerController(inventoryManagerView));
        } else if (source == generalManagerView.getSellerButton()) {
            SellerView sellerView = new SellerView();
            changeWindow(sellerView, new SellerController(sellerView));
        } else if (source == generalManagerView.getLogoutButton()) {
            logout();
        }
    }

    private void openAddUserView() {
        AddUserView addUserView = new AddUserView();
        new AddUserController(addUserView, userTable);
        addUserView.setVisible(true);
    }

    private void deleteUser() {

        int index = generalManagerView.getUserTable().getSelectedRow();

        if (index == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un usuario",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de eliminar el usuario?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            try {

                User user = new User(
                        generalManagerView.getUserTable().getValueAt(index, USER_USERNAME_INDEX).toString(),
                        generalManagerView.getUserTable().getValueAt(index, USER_PASSWORD_INDEX).toString());

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

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de cambiar de modo?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            generalManagerView.dispose();
            view.setVisible(true);

            System.out.println(controller + " Activated");
        }
    }

    private void logout() {

        int answer = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de cerrar sesión?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            generalManagerView.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }

    private void updateTable() {

        if (userTable == null) {
            JOptionPane.showMessageDialog(null, "Error: La tabla de usuarios no está inicializada.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingWorker<Void, Void> worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() {
                try {
                    userTable.setRowCount(0);

                    ArrayList<User> userList = UserDAO.selectAll();

                    for (User user : userList) {
                        Object[] rows = {
                                user.getUsername(),
                                user.getPassword(),
                                user.getPosition()
                        };
                        userTable.addRow(rows);
                    }

                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al cargar la tabla de usuarios: " + exception.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };

        worker.execute();
    
    }

}
