package com.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

import com.dao.UserDAO;
import com.data.User;
import com.data.UserPosition;
import com.view.LoginView;
import com.view.VistaVentanaAdministrador;
import com.view.VistaVentanaInventarista;
import com.view.VistaVentanaVendedor;

public class LoginController implements ActionListener {

    private final LoginView loginView;

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
            JOptionPane.showMessageDialog(null, "Usuario/Contraseña inválida", "Alerta",
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
        switch (position) {
            case UserPosition.GENERAL_MANAGER:
                openNewWindow(
                        new VistaVentanaAdministrador(),
                        new ControlVentanaAdministrador(new VistaVentanaAdministrador()),
                        false);
                break;
            case UserPosition.INVENTORY_MANAGER:
                openNewWindow(
                        new VistaVentanaInventarista(),
                        new ControlVentanaInventarista(new VistaVentanaInventarista()),
                        true);
                break;
            case UserPosition.SELLER:
                openNewWindow(new VistaVentanaVendedor(),
                        new ControlVentanaVendedor(new VistaVentanaVendedor()),
                        true);
                break;
            default:
                JOptionPane.showMessageDialog(
                        null,
                        "Rol no reconocido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                break;

        }
    }

    private void openNewWindow(javax.swing.JFrame view, Object controller, boolean switchActivated) {

        if (switchActivated && view instanceof VistaVentanaInventarista) {
            ((VistaVentanaInventarista) view).getBotonSwitch().setEnabled(true);
        } else if (switchActivated && view instanceof VistaVentanaVendedor) {
            ((VistaVentanaVendedor) view).getBotonSwitch().setEnabled(true);
        }

        view.setVisible(true);
        loginView.dispose();
    }

}
