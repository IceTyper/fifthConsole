package gui;

import connection.Message;
import connection.User;
import connectionchamber.HashMD5;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import gui.locale.LocaleChanger;
import gui.locale.LocalePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static gui.locale.LocalePanel.font;

public class RegistrationFrame extends JFrame {
    private final JTextField loginField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JLabel statusLabel = new JLabel(" ");
    private final JLabel loginLabel = new JLabel();
    private final JLabel passwordLabel = new JLabel();
    private JButton registerButton;
    private JButton loginButton;
    private LocalePanel localePanel;

    public RegistrationFrame() {
        super(LocaleChanger.getString("window.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 320);
        setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(10, 10, 10, 10);
        gridBag.fill = GridBagConstraints.BOTH;

        Font lFont = new Font(font, Font.PLAIN, 18);

        localePanel = new LocalePanel(this::updateLocale);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.NORTHEAST;
        add(localePanel, gridBag);
        gridBag.gridwidth = 1;
        gridBag.anchor = GridBagConstraints.CENTER;

        Font labelFont = new Font(font, Font.BOLD, 18);
        loginLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        Font fieldFont = new Font(font, Font.PLAIN, 18);
        loginField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        loginField.setPreferredSize(new Dimension(250, 40));
        passwordField.setPreferredSize(new Dimension(250, 40));

        loginLabel.setText(LocaleChanger.getString("label.login"));
        passwordLabel.setText(LocaleChanger.getString("label.password"));

        gridBag.gridx = 0;
        gridBag.gridy = 1;
        add(loginLabel, gridBag);
        gridBag.gridx = 1;
        add(loginField, gridBag);

        gridBag.gridx = 0;
        gridBag.gridy = 2;
        add(passwordLabel, gridBag);
        gridBag.gridx = 1;
        add(passwordField, gridBag);

        registerButton = new JButton(LocaleChanger.getString("button.register"));
        registerButton.setFont(lFont);
        registerButton.setPreferredSize(new Dimension(180, 40));
        loginButton = new JButton(LocaleChanger.getString("button.login"));
        loginButton.setFont(lFont);
        loginButton.setPreferredSize(new Dimension(180, 40));

        gridBag.gridx = 0;
        gridBag.gridy = 3;
        add(registerButton, gridBag);
        gridBag.gridx = 1;
        add(loginButton, gridBag);

        statusLabel.setFont(lFont);
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 2;
        add(statusLabel, gridBag);
        gridBag.gridwidth = 1;

        registerButton.addActionListener(e -> handleRegister());
        loginButton.addActionListener(e -> handleLogin());

        setLocationRelativeTo(null);
    }

    private void disableButtons() {
        registerButton.setEnabled(false);
        loginButton.setEnabled(false);
    }

    private void enableButtons() {
        registerButton.setEnabled(true);
        loginButton.setEnabled(true);
    }

    private void handleRegister() {
        String login = loginField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();
        if (login.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    LocaleChanger.getString("error.emptyfields"));
            return;
        }
        statusLabel.setText(LocaleChanger.getString("status.waiting"));
        disableButtons();

        new Thread(() -> {
            try {
                User newUser = new User(login, HashMD5.hash(pass));
                Message msgOut = new Message("register_user",
                        new Object[]{newUser, "5325c93c9e4697ff4395b23b9077123d", "register"});
                Message msgIn = Sender.sendAndReceive(msgOut);

                SwingUtilities.invokeLater(() -> {
                    if (msgIn != null && msgIn.args().length > 1
                            && msgIn.args()[1] instanceof Boolean
                            && (Boolean) msgIn.args()[1]) {
                        UDPDatagramClient.setUser(newUser);
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("success.registration"));
                        dispose();
                        new MainMenu().setVisible(true); // цепочка обязанностей
                    } else {
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("error.user.exists"));
                    }
                });
            } catch (IOException | ClassNotFoundException ex) {
                if (ex instanceof IOException && ex.getMessage() != null
                        && ex.getMessage().contains("30 секунд")) {
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText(LocaleChanger.getString("status.server.unavailable"));
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("error.connection"),
                                LocaleChanger.getString("error.connection.title"),
                                JOptionPane.ERROR_MESSAGE);
                    });
                } else {
                    SwingUtilities.invokeLater(() ->
                            statusLabel.setText(LocaleChanger.getString("error.registration") + ex.getMessage())
                    );
                }
            } catch (NoSuchAlgorithmException e) {
                SwingUtilities.invokeLater(() ->
                        statusLabel.setText(LocaleChanger.getString("error.hash") + e.getMessage())
                );
            } finally {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("");
                    enableButtons(); // разблокируем обе кнопки
                });
            }
        }).start();
    }

    private void handleLogin() {
        String login = loginField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();
        if (login.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    LocaleChanger.getString("error.emptyfields"));
            return;
        }
        statusLabel.setText(LocaleChanger.getString("status.waiting"));
        disableButtons(); // блокируем обе кнопки

        new Thread(() -> {
            try {
                User loginUser = new User(login, HashMD5.hash(pass));
                Message msgOut = new Message("register_user",
                        new Object[]{loginUser, "5325c93c9e4697ff4395b23b9077123d", "login"});
                Message msgIn = Sender.sendAndReceive(msgOut);

                SwingUtilities.invokeLater(() -> {
                    if (msgIn != null && msgIn.args().length > 1
                            && msgIn.args()[1] instanceof Boolean
                            && (Boolean) msgIn.args()[1]) {
                        UDPDatagramClient.setUser(loginUser);
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("success.login"));
                        dispose();
                        new MainMenu().setVisible(true); // цепочка обязанностей
                    } else {
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("error.wrong.credentials"));
                    }
                });
            } catch (IOException | ClassNotFoundException ex) {
                if (ex instanceof IOException && ex.getMessage() != null
                        && ex.getMessage().contains("30 секунд")) {
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText(LocaleChanger.getString("status.server.unavailable"));
                        JOptionPane.showMessageDialog(this,
                                LocaleChanger.getString("error.connection"),
                                LocaleChanger.getString("error.connection.title"),
                                JOptionPane.ERROR_MESSAGE);
                    });
                } else {
                    SwingUtilities.invokeLater(() ->
                            statusLabel.setText(LocaleChanger.getString("error.login") + ex.getMessage())
                    );
                }
            } catch (NoSuchAlgorithmException e) {
                SwingUtilities.invokeLater(() ->
                        statusLabel.setText(LocaleChanger.getString("error.hash") + e.getMessage())
                );
            } finally {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("");
                    enableButtons();
                });
            }
        }).start();
    }

    // Метод для обновления всех надписей и кнопок при смене локали
    private void updateLocale() {
        setTitle(LocaleChanger.getString("window.title"));
        loginLabel.setText(LocaleChanger.getString("label.login"));
        passwordLabel.setText(LocaleChanger.getString("label.password"));
        if (registerButton != null) registerButton.setText(LocaleChanger.getString("button.register"));
        if (loginButton != null) loginButton.setText(LocaleChanger.getString("button.login"));
        if (localePanel != null) localePanel.updateLocale();
        repaint();
    }
}