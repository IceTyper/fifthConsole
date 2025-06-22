package gui;

import gui.locale.LocaleChanger;
import models.SpaceMarine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class VisualisationTab extends JPanel {
    private final VisualisationCanvas canvas;
    private final Map<String, Color> userColors = new HashMap<>();
    private List<SpaceMarine> marines = new ArrayList<>();

    public VisualisationTab() {
        setLayout(new BorderLayout());
        setBackground(new Color(10, 12, 30));
        canvas = new VisualisationCanvas();
        add(canvas, BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SpaceMarine clicked = canvas.getMarineAt(e.getX(), e.getY());
                if (clicked != null) {
                    JOptionPane.showMessageDialog(
                            VisualisationTab.this,
                            getMarineInfo(clicked),
                            LocaleChanger.getString("visualization.info.title"),
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
    }

    public void refreshData(List<SpaceMarine> newMarines) {
        this.marines = new ArrayList<>(newMarines);
        canvas.setMarines(this.marines);
        canvas.repaint();
    }

    private Color getColorForUser(String username) {
        if (!userColors.containsKey(username)) {
            ThreadLocalRandom rnd = ThreadLocalRandom.current();
            Color color = new Color(rnd.nextInt(80, 220), rnd.nextInt(80, 220), rnd.nextInt(80, 220));
            userColors.put(username, color);
        }
        return userColors.get(username);
    }

    private String getMarineInfo(SpaceMarine sm) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(sm.getId()).append("\n");
        sb.append(LocaleChanger.getString("tabletab.col.name")).append(": ").append(sm.getName()).append("\n");
        sb.append(LocaleChanger.getString("tabletab.col.x")).append(": ").append(sm.getCoordinates() != null ? sm.getCoordinates().getX() : "").append("\n");
        sb.append(LocaleChanger.getString("tabletab.col.y")).append(": ").append(sm.getCoordinates() != null ? sm.getCoordinates().getY() : "").append("\n");
        sb.append(LocaleChanger.getString("tabletab.col.health")).append(": ").append(sm.getHealth()).append("\n");
        sb.append(LocaleChanger.getString("tabletab.col.owner")).append(": ").append(sm.getOwner()).append("\n");
        return sb.toString();
    }

    private class VisualisationCanvas extends Canvas {
        private final int PADDING = 40;
        private final int MIN_SIZE = 30;
        private final int MAX_SIZE = 100;
        private List<SpaceMarine> marines = new ArrayList<>();

        public VisualisationCanvas() {
            setBackground(new Color(10, 12, 30));
        }

        public void setMarines(List<SpaceMarine> marines) {
            this.marines = marines;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(900, 700);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(10, 12, 30));
            g.fillRect(0, 0, getWidth(), getHeight());

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ThreadLocalRandom rnd = ThreadLocalRandom.current();
            for (int i = 0; i < 120; i++) {
                int x = rnd.nextInt(getWidth());
                int y = rnd.nextInt(getHeight());
                int size = rnd.nextInt(1, 3);
                g2.setColor(new Color(180 + rnd.nextInt(60), 180 + rnd.nextInt(60), 200 + rnd.nextInt(55)));
                g2.fillOval(x, y, size, size);
            }

            if (marines == null) return;

            long minX = marines.stream().mapToLong(m -> m.getCoordinates() != null ? m.getCoordinates().getX() : 0).min().orElse(-100);
            long maxX = marines.stream().mapToLong(m -> m.getCoordinates() != null ? m.getCoordinates().getX() : 0).max().orElse(100);
            int minY = marines.stream().mapToInt(m -> m.getCoordinates() != null ? m.getCoordinates().getY() : 0).min().orElse(-100);
            int maxY = marines.stream().mapToInt(m -> m.getCoordinates() != null ? m.getCoordinates().getY() : 0).max().orElse(100);

            int w = getWidth() - 2 * PADDING;
            int h = getHeight() - 2 * PADDING;

            double scaleX = (maxX == minX) ? 1 : (double) w / (maxX - minX + 1);
            double scaleY = (maxY == minY) ? 1 : (double) h / (maxY - minY + 1);

            for (SpaceMarine sm : marines) {
                if (sm.getCoordinates() == null) continue;
                long x = sm.getCoordinates().getX();
                int y = sm.getCoordinates().getY();
                int drawX = (int) ((x - minX) * scaleX) + PADDING;
                int drawY = h - (int) ((y - minY) * scaleY) + PADDING;

                int size = MIN_SIZE;
                try {
                    if (sm.getHealth() != null) {
                        double health = sm.getHealth();
                        double minHealth = marines.stream().filter(m -> m.getHealth() != null).mapToDouble(SpaceMarine::getHealth).min().orElse(1);
                        double maxHealth = marines.stream().filter(m -> m.getHealth() != null).mapToDouble(SpaceMarine::getHealth).max().orElse(1);
                        if (maxHealth > minHealth) {
                            size = (int) (MIN_SIZE + (health - minHealth) / (maxHealth - minHealth) * (MAX_SIZE - MIN_SIZE));
                        }
                    }
                } catch (Exception ignored) {
                }

                Color color = getColorForUser(sm.getOwner() != null ? sm.getOwner() : "unknown");
                g2.setColor(color);
                g2.fillOval(drawX - size / 2, drawY - size / 2, size, size);

                g2.setColor(Color.WHITE);
                g2.drawOval(drawX - size / 2, drawY - size / 2, size, size);

                g2.setColor(Color.WHITE);
                String idStr = String.valueOf(sm.getId());
                FontMetrics fm = g2.getFontMetrics();
                int tx = drawX - fm.stringWidth(idStr) / 2;
                int ty = drawY + fm.getAscent() / 2 - 4;
                g2.drawString(idStr, tx, ty);
            }
        }

        public SpaceMarine getMarineAt(int x, int y) {
            if (marines == null) return null;
            long minX = marines.stream().mapToLong(m -> m.getCoordinates() != null ? m.getCoordinates().getX() : 0).min().orElse(-100);
            long maxX = marines.stream().mapToLong(m -> m.getCoordinates() != null ? m.getCoordinates().getX() : 0).max().orElse(100);
            int minY = marines.stream().mapToInt(m -> m.getCoordinates() != null ? m.getCoordinates().getY() : 0).min().orElse(-100);
            int maxY = marines.stream().mapToInt(m -> m.getCoordinates() != null ? m.getCoordinates().getY() : 0).max().orElse(100);

            int w = getWidth() - 2 * PADDING;
            int h = getHeight() - 2 * PADDING;

            double scaleX = (maxX == minX) ? 1 : (double) w / (maxX - minX + 1);
            double scaleY = (maxY == minY) ? 1 : (double) h / (maxY - minY + 1);

            for (SpaceMarine sm : marines) {
                if (sm.getCoordinates() == null) continue;
                long objX = sm.getCoordinates().getX();
                int objY = sm.getCoordinates().getY();
                int drawX = (int) ((objX - minX) * scaleX) + PADDING;
                int drawY = h - (int) ((objY - minY) * scaleY) + PADDING;

                int size = MIN_SIZE;
                try {
                    if (sm.getHealth() != null) {
                        double health = sm.getHealth();
                        double minHealth = marines.stream().filter(m -> m.getHealth() != null).mapToDouble(SpaceMarine::getHealth).min().orElse(1);
                        double maxHealth = marines.stream().filter(m -> m.getHealth() != null).mapToDouble(SpaceMarine::getHealth).max().orElse(1);
                        if (maxHealth > minHealth) {
                            size = (int) (MIN_SIZE + (health - minHealth) / (maxHealth - minHealth) * (MAX_SIZE - MIN_SIZE));
                        }
                    }
                } catch (Exception ignored) {
                }

                int dx = x - drawX;
                int dy = y - drawY;
                if (dx * dx + dy * dy <= (size / 2) * (size / 2)) {
                    return sm;
                }
            }
            return null;
        }
    }
}
