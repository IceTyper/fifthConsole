package gui;

import connection.Message;
import connection.User;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import gui.locale.LocaleChanger;
import models.SpaceMarine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gui.locale.LocalePanel.font;

public class TableTab extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private List<SpaceMarine> allMarines = new ArrayList<>();
    private List<SpaceMarine> filteredMarines = new ArrayList<>();
    private int sortColumn = -1;
    private boolean sortAsc = true;
    private JTextField filterField = new JTextField(20);
    private JButton filterButton = new JButton();
    private JButton resetButton = new JButton();
    private JButton deleteButton = new JButton();
    private JLabel filterLabel = new JLabel();

    public TableTab() {
        setLayout(new BorderLayout());
        updateLocale();

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFont(new Font(font, Font.PLAIN, 18));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font(font, Font.BOLD, 18));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setAutoCreateRowSorter(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterField.setFont(new Font(font, Font.PLAIN, 16));
        filterButton.setFont(new Font(font, Font.PLAIN, 16));
        resetButton.setFont(new Font(font, Font.PLAIN, 16));
        deleteButton.setFont(new Font(font, Font.PLAIN, 16));
        filterLabel.setFont(new Font(font, Font.PLAIN, 16));
        topPanel.add(filterLabel);
        topPanel.add(filterField);
        topPanel.add(filterButton);
        topPanel.add(resetButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                if (col >= 0) {
                    if (sortColumn == col) sortAsc = !sortAsc;
                    else {
                        sortColumn = col;
                        sortAsc = true;
                    }
                    applySortAndFilter();
                }
            }
        });

        filterButton.addActionListener(e -> applySortAndFilter());
        filterField.addActionListener(e -> applySortAndFilter());
        resetButton.addActionListener(e -> {
            filterField.setText("");
            applySortAndFilter();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.convertRowIndexToModel(table.getSelectedRow());
                    int col = table.convertColumnIndexToModel(table.getSelectedColumn());
                    if (isUserObject(row) && isEditableColumn(col)) {
                        editCell(row, col);
                    }
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int viewRow = table.getSelectedRow();
            if (viewRow == -1) return;
            int modelRow = table.convertRowIndexToModel(viewRow);
            if (modelRow < 0 || modelRow >= filteredMarines.size()) {
                JOptionPane.showMessageDialog(this, LocaleChanger.getString("tabletab.delete.invalidrow"));
                return;
            }
            SpaceMarine sm = filteredMarines.get(modelRow);
            User user = UDPDatagramClient.getUser();
            if (user != null && user.username().equals(sm.getOwner())) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        LocaleChanger.getString("tabletab.delete.confirm", sm.getId()),
                        LocaleChanger.getString("tabletab.delete.title"),
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    removeById(sm.getId());
                }
            } else {
                JOptionPane.showMessageDialog(this, LocaleChanger.getString("tabletab.delete.notowner"));
            }
        });
    }

    public void refreshTable() {
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
                allMarines = marines;
                applySortAndFilter();
            } catch (Exception ignored) {
            }
        });
    }

    private void applySortAndFilter() {
        String filter = filterField.getText().trim().toLowerCase();
        Stream<SpaceMarine> stream = allMarines.stream();
        if (!filter.isEmpty()) {
            stream = stream.filter(m -> Arrays.stream(getMarineFields(m))
                    .anyMatch(val -> val != null && val.toString().toLowerCase().contains(filter)));
        }
        List<SpaceMarine> result = stream.collect(Collectors.toList());
        if (sortColumn >= 0) {
            Comparator<SpaceMarine> cmp = getComparator(sortColumn);
            if (cmp != null) {
                result = result.stream()
                        .sorted(sortAsc ? cmp : cmp.reversed())
                        .collect(Collectors.toList());
            }
        }
        filteredMarines = result;
        fillTable(filteredMarines);
    }

    private Comparator<SpaceMarine> getComparator(int col) {
        switch (col) {
            case 0:
                return Comparator.comparing(SpaceMarine::getId, Comparator.nullsLast(Long::compareTo));
            case 1:
                return Comparator.comparing(SpaceMarine::getName, Comparator.nullsLast(String::compareTo));
            case 2:
                return Comparator.comparing(m -> m.getCoordinates() != null ? m.getCoordinates().getX() : null, Comparator.nullsLast(Long::compareTo));
            case 3:
                return Comparator.comparing(m -> m.getCoordinates() != null ? m.getCoordinates().getY() : null, Comparator.nullsLast(Integer::compareTo));
            case 4:
                return Comparator.comparing(SpaceMarine::getCreationDate, Comparator.nullsLast(java.time.LocalDateTime::compareTo));
            case 5:
                return Comparator.comparing(SpaceMarine::getHealth, Comparator.nullsLast(Long::compareTo));
            case 6:
                return Comparator.comparing(SpaceMarine::getLoyal);
            case 7:
                return Comparator.comparing(SpaceMarine::getWeaponType, Comparator.nullsLast(Enum::compareTo));
            case 8:
                return Comparator.comparing(SpaceMarine::getMeleeWeapon, Comparator.nullsLast(Enum::compareTo));
            case 9:
                return Comparator.comparing(m -> m.getChapter() != null ? m.getChapter().getName() : null, Comparator.nullsLast(String::compareTo));
            case 10:
                return Comparator.comparing(m -> m.getChapter() != null ? m.getChapter().getMarinesCount() : null, Comparator.nullsLast(Long::compareTo));
            case 11:
                return Comparator.comparing(m -> m.getChapter() != null ? m.getChapter().getWorld() : null, Comparator.nullsLast(String::compareTo));
            case 12:
                return Comparator.comparing(SpaceMarine::getOwner, Comparator.nullsLast(String::compareTo));
            default:
                return null;
        }
    }

    private void fillTable(List<SpaceMarine> marines) {
        tableModel.setRowCount(0);
        for (SpaceMarine sm : marines) {
            Object[] row = getMarineFields(sm);
            tableModel.addRow(row);
        }
    }

    private Object[] getMarineFields(SpaceMarine sm) {
        return new Object[]{
                sm.getId(),
                sm.getName(),
                sm.getCoordinates() != null ? sm.getCoordinates().getX() : null,
                sm.getCoordinates() != null ? sm.getCoordinates().getY() : null,
                sm.getCreationDate() != null ? dateFormatter.format(sm.getCreationDate()) : null,
                sm.getHealth(),
                sm.getLoyal(),
                sm.getWeaponType(),
                sm.getMeleeWeapon(),
                sm.getChapter() != null ? sm.getChapter().getName() : null,
                sm.getChapter() != null ? sm.getChapter().getMarinesCount() : null,
                sm.getChapter() != null ? sm.getChapter().getWorld() : null,
                sm.getOwner()
        };
    }

    private boolean isUserObject(int row) {
        int modelRow = table.convertRowIndexToModel(row);
        if (modelRow < 0 || modelRow >= filteredMarines.size()) return false;
        User user = UDPDatagramClient.getUser();
        return user != null && user.username().equals(filteredMarines.get(modelRow).getOwner());
    }

    private boolean isEditableColumn(int col) {
        return col != 0 && col != 4 && col != 12;
    }

    private void editCell(int row, int col) {
        SpaceMarine sm = filteredMarines.get(row);
        String oldValue = tableModel.getValueAt(row, col) != null ? tableModel.getValueAt(row, col).toString() : "";
        String newValue = JOptionPane.showInputDialog(this, LocaleChanger.getString("tabletab.edit.newvalue"), oldValue);
        if (newValue == null) return;
        try {
            List<String> fields = new ArrayList<>(Arrays.asList(
                    sm.getName() != null ? sm.getName() : "",
                    sm.getCoordinates() != null && sm.getCoordinates().getX() != null ? sm.getCoordinates().getX().toString() : "",
                    sm.getCoordinates() != null && sm.getCoordinates().getY() != null ? sm.getCoordinates().getY().toString() : "",
                    sm.getHealth() != null ? sm.getHealth().toString() : "",
                    Boolean.toString(sm.getLoyal()),
                    sm.getWeaponType() != null ? sm.getWeaponType().toString() : "",
                    sm.getMeleeWeapon() != null ? sm.getMeleeWeapon().toString() : "",
                    (sm.getChapter() != null && sm.getChapter().getName() != null) ? sm.getChapter().getName() : "",
                    (sm.getChapter() != null) ? Long.toString(sm.getChapter().getMarinesCount()) : "",
                    (sm.getChapter() != null && sm.getChapter().getWorld() != null) ? sm.getChapter().getWorld() : ""
            ));

            int fieldIdx = switch (col) {
                case 1 -> 0; // name
                case 2 -> 1; // coordinates.x
                case 3 -> 2; // coordinates.y
                case 5 -> 3; // health
                case 6 -> 4; // loyal
                case 7 -> 5; // weaponType
                case 8 -> 6; // meleeWeapon
                case 9 -> 7; // chapter.name
                case 10 -> 8; // chapter.marinesCount
                case 11 -> 9; // chapter.world
                default -> -1;
            };
            if (fieldIdx == -1) return;

            switch (fieldIdx) {
                case 0: // name
                    if (newValue.trim().isEmpty())
                        throw new IllegalArgumentException("Имя не может быть пустым или состоять из пробелов.");
                    break;
                case 1: // coordinates.x
                    if (newValue.trim().isEmpty() || Long.parseLong(newValue) <= -483)
                        throw new IllegalArgumentException("Координата x должна быть целым числом больше -483.");
                    break;
                case 2: // coordinates.y
                    if (newValue.trim().isEmpty() || Integer.parseInt(newValue) <= -325)
                        throw new IllegalArgumentException("Координата y должна быть целым числом больше -325.");
                    break;
                case 3: // health
                    if (newValue.trim().isEmpty() || Long.parseLong(newValue) <= 0)
                        throw new IllegalArgumentException("Здоровье должно быть целым числом больше 0.");
                    break;
                case 4: // loyal
                    if (!newValue.equalsIgnoreCase("true") && !newValue.equalsIgnoreCase("false"))
                        throw new IllegalArgumentException("Лояльность должна быть true или false.");
                    break;
                case 5: // weaponType
                    if (!Arrays.asList("HEAVY_BOLTGUN", "GRAV_GUN", "HEAVY_FLAMER").contains(newValue))
                        throw new IllegalArgumentException("Огнестрельное оружие: HEAVY_BOLTGUN, GRAV_GUN, HEAVY_FLAMER.");
                    break;
                case 6: // meleeWeapon
                    if (!Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST").contains(newValue))
                        throw new IllegalArgumentException("Холодное оружие: CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST.");
                    break;
                case 7: // chapter.name
                    if (newValue.trim().isEmpty())
                        throw new IllegalArgumentException("Название части не может быть пустым или состоять из пробелов.");
                    break;
                case 8: // chapter.marinesCount
                    if (newValue.trim().isEmpty())
                        throw new IllegalArgumentException("Количество людей в части не может быть пустым.");
                    long count = Long.parseLong(newValue);
                    if (count <= 0 || count > 1000)
                        throw new IllegalArgumentException("Количество людей в части: 1..1000.");
                    break;
            }

            fields.set(fieldIdx, newValue);

            sendUpdate(sm.getId(), fields);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, LocaleChanger.getString("tabletab.edit.error") + ex.getMessage());
        }
    }

    private void sendUpdate(Long id, List<String> fields) {
        try {
            User user = UDPDatagramClient.getUser();
            Object[] args = new Object[]{
                    user,
                    id.toString(),
                    fields.get(0), // name
                    fields.get(1), // coordinates.x
                    fields.get(2), // coordinates.y
                    fields.get(3), // health
                    fields.get(4), // loyal
                    fields.get(5), // weaponType
                    fields.get(6), // meleeWeapon
                    fields.get(7), // chapter.name
                    fields.get(8), // chapter.marinesCount
                    fields.get(9)  // chapter.world
            };
            Message msg = new Message("update", args);
            Message response = Sender.sendAndReceive(msg);
            String serverMsg = null;
            if (response != null && response.args().length > 1 && response.args()[1] != null) {
                serverMsg = response.args()[1].toString();
            }
            JOptionPane.showMessageDialog(this, serverMsg != null ? serverMsg : LocaleChanger.getString("commands.no_response"));
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, LocaleChanger.getString("tabletab.edit.updateerror") + ex.getMessage());
        }
    }

    private void removeById(Long id) {
        try {
            User user = UDPDatagramClient.getUser();
            Message msg = new Message("remove_by_id", new Object[]{user, id.toString()});
            Message response = Sender.sendAndReceive(msg);
            String serverMsg = null;
            if (response != null && response.args().length > 1 && response.args()[1] != null) {
                serverMsg = response.args()[1].toString();
            } else if (response != null && response.args().length > 0 && response.args()[0] != null) {
                serverMsg = response.args()[0].toString();
            }
            JOptionPane.showMessageDialog(this, serverMsg != null ? serverMsg : LocaleChanger.getString("commands.no_response"));
            refreshTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, LocaleChanger.getString("tabletab.delete.error") + ex.getMessage());
        }
    }


    public void updateLocale() {
        columnNames = new String[]{
                LocaleChanger.getString("tabletab.col.id"),
                LocaleChanger.getString("tabletab.col.name"),
                LocaleChanger.getString("tabletab.col.x"),
                LocaleChanger.getString("tabletab.col.y"),
                LocaleChanger.getString("tabletab.col.creationdate"),
                LocaleChanger.getString("tabletab.col.health"),
                LocaleChanger.getString("tabletab.col.loyal"),
                LocaleChanger.getString("tabletab.col.weapontype"),
                LocaleChanger.getString("tabletab.col.meleeweapon"),
                LocaleChanger.getString("tabletab.col.chaptername"),
                LocaleChanger.getString("tabletab.col.marinescount"),
                LocaleChanger.getString("tabletab.col.world"),
                LocaleChanger.getString("tabletab.col.owner")
        };
        filterButton.setText(LocaleChanger.getString("tabletab.filter"));
        resetButton.setText(LocaleChanger.getString("tabletab.reset"));
        deleteButton.setText(LocaleChanger.getString("tabletab.delete"));
        filterLabel.setText(LocaleChanger.getString("tabletab.search"));
        if (tableModel != null) {
            tableModel.setColumnIdentifiers(columnNames);
        }
        if (table != null && table.getTableHeader() != null) {
            table.getTableHeader().repaint();
        }
        repaint();
    }

    public List<SpaceMarine> getFilteredMarines() {
        return filteredMarines;
    }
}