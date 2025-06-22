package gui;

import connection.Message;
import connection.User;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import gui.commandstab.CommandsTab;
import gui.locale.LocaleChanger;
import gui.locale.LocalePanel;
import models.SpaceMarine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static gui.locale.LocalePanel.font;

public class MainMenu extends JFrame {
    private final LocalePanel localePanel;
    private final JLabel userLabel = new JLabel();
    private final AvatarPanel avatarPanel;
    private final JTabbedPane tabbedPane;
    private final VisualisationTab visualizationTab;

    public MainMenu() {
        super(LocaleChanger.getString("mainmenu.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        localePanel = new LocalePanel(this::updateLocale);
        add(localePanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(200, 800));
        leftPanel.setBackground(Color.WHITE);

        User user = UDPDatagramClient.getUser();
        avatarPanel = new AvatarPanel(user != null ? user.username() : "?");
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(40));
        leftPanel.add(avatarPanel);
        leftPanel.add(Box.createVerticalStrut(20));

        userLabel.setFont(new Font(font, Font.PLAIN, 18));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(userLabel);

        add(leftPanel, BorderLayout.WEST);

        tabbedPane = new JTabbedPane();

        CommandsTab commandsTab = new CommandsTab();
        TableTab tableTab = new TableTab();

        visualizationTab = new VisualisationTab();

        tabbedPane.addTab(LocaleChanger.getString("mainmenu.tab.commands"), commandsTab);
        tabbedPane.addTab(LocaleChanger.getString("mainmenu.tab.table"), tableTab);
        tabbedPane.addTab(LocaleChanger.getString("mainmenu.tab.visualization"), visualizationTab);

        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(e -> {
            int idx = tabbedPane.getSelectedIndex();
            if (idx == 1) {
                tableTab.refreshTable();
            }
            if (idx == 2) {
                refreshVisualizationTab();
            }
        });

        updateLocale();
        setLocationRelativeTo(null);
    }

    private void refreshVisualizationTab() {
        SwingUtilities.invokeLater(() -> {
            try {
                User user = UDPDatagramClient.getUser();
                if (user == null) return;
                Message msg = new Message("show", new Object[]{user});
                Message response = Sender.sendAndReceive(msg);

                List<SpaceMarine> marines = new ArrayList<>();
                if (response != null && response.args().length > 1 && response.args()[1] != null) {
                    Object[] arr = response.args();
                    for (int i = 1; i < arr.length; i++) {
                        Object o = arr[i];
                        if (o instanceof SpaceMarine sm) {
                            marines.add(sm);
                        }
                    }
                }
                visualizationTab.refreshData(marines);
            } catch (Exception ignored) {
            }
        });
    }

    private void updateLocale() {
        setTitle(LocaleChanger.getString("mainmenu.title"));
        User user = UDPDatagramClient.getUser();
        userLabel.setText(user != null ? user.username() : "?");
        localePanel.updateLocale();
        avatarPanel.repaint();
        tabbedPane.setTitleAt(0, LocaleChanger.getString("mainmenu.tab.commands"));
        tabbedPane.setTitleAt(1, LocaleChanger.getString("mainmenu.tab.table"));
        tabbedPane.setTitleAt(2, LocaleChanger.getString("mainmenu.tab.visualization"));

        Component comp0 = tabbedPane.getComponentAt(0);
        if (comp0 instanceof gui.commandstab.CommandsTab commandsTab) {
            commandsTab.updateLocale();
        }

        Component comp = tabbedPane.getComponentAt(1);
        if (comp instanceof TableTab tableTab) {
            tableTab.updateLocale();
        }
        repaint();
    }

    private static class AvatarPanel extends JPanel {
        private final String username;

        public AvatarPanel(String username) {
            this.username = username;
            setPreferredSize(new Dimension(100, 100));
            setMaximumSize(new Dimension(100, 100));
            setMinimumSize(new Dimension(100, 100));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
            g2.fillOval(0, 0, 100, 100);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font(font, Font.PLAIN, 48));
            String letter = username != null && !username.isEmpty()
                    ? username.substring(0, 1).toUpperCase(LocaleChanger.getCurrentLocale())
                    : "?";
            FontMetrics fm = g2.getFontMetrics();
            int x = (100 - fm.stringWidth(letter)) / 2;
            int y = (100 - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(letter, x, y);
        }
    }
}