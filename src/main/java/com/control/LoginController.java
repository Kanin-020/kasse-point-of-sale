package com.control;

import static com.utils.Constants.GENERAL_MANAGER;
import static com.utils.Constants.INVENTORY_MANAGER;
import static com.utils.Constants.SELLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import com.dao.UserDAO;
import com.data.User;
import com.view.LoginView;
import com.view.GeneralManagerView;
import com.view.InventoryManagerView;
import com.view.SellerView;

public class LoginController implements ActionListener {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getBotonIniciarSesion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == loginView.getBotonIniciarSesion()) {
            login();
        }
    }

    private void login() {
        String username = loginView.getFieldUsuario().getText();
        String password = new String(loginView.getFieldContraseña().getPassword());

        Optional<User> user = validateCredentials(username, password);

        if (user.isPresent()) {
            selectWindow(user.get().getPosition());
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario/Contraseña inválida",
                    "Alerta",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private Optional<User> validateCredentials(String username, String password) {
        try {

            List<User> userList = UserDAO.select(new User(username, password));

            return userList.stream()
                    .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                    .findFirst();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error en la conexión con la base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            exception.printStackTrace();
        }
        return Optional.empty();
    }

    private void selectWindow(String position) {

        JFrame view;
        Object controller;
        boolean switchActivated = false;

        switch (position) {
            case GENERAL_MANAGER:
                view = new GeneralManagerView();
                controller = new GeneralManagerController((GeneralManagerView) view);
                break;
            case INVENTORY_MANAGER:
                view = new InventoryManagerView();
                controller = new InventoryManagerController((InventoryManagerView) view);
                switchActivated = true;
                break;
            case SELLER:
                view = new SellerView();
                controller = new SellerController((SellerView) view);
                switchActivated = true;
                break;
            default:
                JOptionPane.showMessageDialog(
                        null,
                        "Rol no reconocido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
        }

        openNewWindow(view, controller, switchActivated);
    }

    private void openNewWindow(JFrame view, Object controller, boolean switchActivated) {
        if (switchActivated) {
            if (view instanceof InventoryManagerView) {
                ((InventoryManagerView) view).getBotonSwitch().setEnabled(true);
            } else if (view instanceof SellerView) {
                ((SellerView) view).getBotonSwitch().setEnabled(true);
            }
        }

        view.setVisible(true);
        loginView.dispose();
    }

}
