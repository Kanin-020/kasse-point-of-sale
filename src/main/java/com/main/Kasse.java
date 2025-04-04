package com.main;

import com.control.LoginController;
import com.view.SplashView;
import com.view.LoginView;

public class Kasse {

    public static void main(String[] args) throws InterruptedException {
        SplashView splashView = new SplashView();
        splashView.setVisible(true);
        Thread.sleep(1300);
        splashView.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView);

        loginView.setVisible(true);

    }

}
