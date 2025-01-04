package com.qldt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.qldt.dao.UserDao;
import com.qldt.entity.User;
import com.qldt.view.*;
public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private View view;
     
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
     
    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                view = new View();
                ConmplexObjectController controller = new ConmplexObjectController(view);
                controller.showObjView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
