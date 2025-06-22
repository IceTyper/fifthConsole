package gui.commandstab;

import gui.locale.LocaleChanger;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandFieldsPanel {
    private final JPanel fieldsPanel;
    private final JTextArea resultArea;
    private final JScrollPane resultScroll;
    private final JButton executeButton;
    private final Map<String, CommandMeta> commands;
    private Map<String, JComponent> fieldInputs = new LinkedHashMap<>();
    public String currentCommand = null;

    public CommandFieldsPanel(JPanel fieldsPanel, JTextArea resultArea, JScrollPane resultScroll, JButton executeButton, Map<String, CommandMeta> commands) {
        this.fieldsPanel = fieldsPanel;
        this.resultArea = resultArea;
        this.resultScroll = resultScroll;
        this.executeButton = executeButton;
        this.commands = commands;
    }

    public void clearAndShowResultArea(boolean big) {
        fieldsPanel.removeAll();
        resultArea.setText("");
        if (big) {
            resultArea.setRows(20);
            resultArea.setColumns(40);
            resultScroll.setPreferredSize(new Dimension(600, 400));
        } else {
            resultArea.setRows(10);
            resultArea.setColumns(30);
            resultScroll.setPreferredSize(new Dimension(350, 200));
        }
        GridBagConstraints gbcResult = new GridBagConstraints();
        gbcResult.gridx = 0;
        gbcResult.gridy = 0;
        gbcResult.gridwidth = 2;
        gbcResult.fill = GridBagConstraints.BOTH;
        gbcResult.weightx = 1.0;
        gbcResult.weighty = 1.0;
        fieldsPanel.add(resultScroll, gbcResult);
        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }

    public void showFieldsForCommand(String cmd) {
        currentCommand = cmd;
        fieldsPanel.removeAll();
        resultArea.setText("");
        CommandMeta meta = commands.get(cmd);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 2, 8);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        fieldInputs = new LinkedHashMap<>();

        if ("execute_script".equals(cmd)) {
            JLabel fileLabel = new JLabel(LocaleChanger.getString("commands.field.filename"));
            fieldsPanel.add(fileLabel, gbc);
            gbc.gridy++;
            JTextField fileField = new JTextField();
            fileField.setPreferredSize(new Dimension(220, 28));
            fieldsPanel.add(fileField, gbc);
            gbc.gridy++;
            JLabel fileHint = new JLabel(LocaleChanger.getString("commands.hint.filename"));
            fileHint.setFont(fileHint.getFont().deriveFont(Font.PLAIN, 11f));
            fieldsPanel.add(fileHint, gbc);
            gbc.gridy++;
            fieldInputs.put("file_name", fileField);

            resultArea.setRows(10);
            resultArea.setColumns(30);
            resultScroll.setPreferredSize(new Dimension(350, 200));
            fieldsPanel.add(executeButton, gbc);

            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            fieldsPanel.add(resultScroll, gbc);

            fieldsPanel.revalidate();
            fieldsPanel.repaint();
            return;
        }

        for (String field : meta.fields) {
            if (field.equals("object")) {
                gbc.gridx = 0;
                JLabel nameLabel = new JLabel(LocaleChanger.getString("commands.field.name"));
                fieldsPanel.add(nameLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField nameField = new JTextField();
                nameField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(nameField, gbc);
                gbc.gridy++;
                JLabel nameHint = new JLabel(LocaleChanger.getString("commands.hint.name"));
                nameHint.setFont(nameHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(nameHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel xLabel = new JLabel(LocaleChanger.getString("commands.field.x"));
                fieldsPanel.add(xLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField xField = new JTextField();
                xField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(xField, gbc);
                gbc.gridy++;
                JLabel xHint = new JLabel(LocaleChanger.getString("commands.hint.x"));
                xHint.setFont(xHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(xHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel yLabel = new JLabel(LocaleChanger.getString("commands.field.y"));
                fieldsPanel.add(yLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField yField = new JTextField();
                yField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(yField, gbc);
                gbc.gridy++;
                JLabel yHint = new JLabel(LocaleChanger.getString("commands.hint.y"));
                yHint.setFont(yHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(yHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel healthLabel = new JLabel(LocaleChanger.getString("commands.field.health"));
                fieldsPanel.add(healthLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField healthField = new JTextField();
                healthField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(healthField, gbc);
                gbc.gridy++;
                JLabel healthHint = new JLabel(LocaleChanger.getString("commands.hint.health"));
                healthHint.setFont(healthHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(healthHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel loyalLabel = new JLabel(LocaleChanger.getString("commands.field.loyal"));
                fieldsPanel.add(loyalLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JComboBox<String> loyalBox = new JComboBox<>(new String[]{"true", "false"});
                loyalBox.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(loyalBox, gbc);
                gbc.gridy++;
                JLabel loyalHint = new JLabel(LocaleChanger.getString("commands.hint.loyal"));
                loyalHint.setFont(loyalHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(loyalHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel weaponLabel = new JLabel(LocaleChanger.getString("commands.field.weaponType"));
                fieldsPanel.add(weaponLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JComboBox<String> weaponBox = new JComboBox<>(new String[]{"HEAVY_BOLTGUN", "GRAV_GUN", "HEAVY_FLAMER", "NONE"});
                weaponBox.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(weaponBox, gbc);
                gbc.gridy++;
                JLabel weaponHint = new JLabel(LocaleChanger.getString("commands.hint.weaponType"));
                weaponHint.setFont(weaponHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(weaponHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel meleeLabel = new JLabel(LocaleChanger.getString("commands.field.meleeWeapon"));
                fieldsPanel.add(meleeLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JComboBox<String> meleeBox = new JComboBox<>(new String[]{"CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE"});
                meleeBox.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(meleeBox, gbc);
                gbc.gridy++;
                JLabel meleeHint = new JLabel(LocaleChanger.getString("commands.hint.meleeWeapon"));
                meleeHint.setFont(meleeHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(meleeHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel chapterNameLabel = new JLabel(LocaleChanger.getString("commands.field.chapterName"));
                fieldsPanel.add(chapterNameLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField chapterNameField = new JTextField();
                chapterNameField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(chapterNameField, gbc);
                gbc.gridy++;
                JLabel chapterNameHint = new JLabel(LocaleChanger.getString("commands.hint.chapterName"));
                chapterNameHint.setFont(chapterNameHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(chapterNameHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel marinesCountLabel = new JLabel(LocaleChanger.getString("commands.field.marinesCount"));
                fieldsPanel.add(marinesCountLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField marinesCountField = new JTextField();
                marinesCountField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(marinesCountField, gbc);
                gbc.gridy++;
                JLabel marinesCountHint = new JLabel(LocaleChanger.getString("commands.hint.marinesCount"));
                marinesCountHint.setFont(marinesCountHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(marinesCountHint, gbc);
                gbc.gridy++;

                gbc.gridx = 0;
                JLabel worldLabel = new JLabel(LocaleChanger.getString("commands.field.world"));
                fieldsPanel.add(worldLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField worldField = new JTextField();
                worldField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(worldField, gbc);
                gbc.gridy++;
                JLabel worldHint = new JLabel(LocaleChanger.getString("commands.hint.world"));
                worldHint.setFont(worldHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(worldHint, gbc);
                gbc.gridy++;

                fieldInputs.put("name", nameField);
                fieldInputs.put("coordinates.x", xField);
                fieldInputs.put("coordinates.y", yField);
                fieldInputs.put("health", healthField);
                fieldInputs.put("loyal", loyalBox);
                fieldInputs.put("weaponType", weaponBox);
                fieldInputs.put("meleeWeapon", meleeBox);
                fieldInputs.put("chapter.name", chapterNameField);
                fieldInputs.put("chapter.marinesCount", marinesCountField);
                fieldInputs.put("chapter.world", worldField);
            } else if (field.equals("id")) {
                gbc.gridx = 0;
                JLabel idLabel = new JLabel(LocaleChanger.getString("commands.field.id"));
                fieldsPanel.add(idLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JTextField idField = new JTextField();
                idField.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(idField, gbc);
                gbc.gridy++;
                JLabel idHint = new JLabel(LocaleChanger.getString("commands.hint.id"));
                idHint.setFont(idHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(idHint, gbc);
                gbc.gridy++;
                fieldInputs.put("id", idField);
            } else if (field.equals("meleeWeapon")) {
                gbc.gridx = 0;
                JLabel meleeLabel = new JLabel(LocaleChanger.getString("commands.field.meleeWeapon"));
                fieldsPanel.add(meleeLabel, gbc);
                gbc.gridy++;
                gbc.gridx = 0;
                JComboBox<String> combo = new JComboBox<>(new String[]{"CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE"});
                combo.setPreferredSize(new Dimension(220, 28));
                fieldsPanel.add(combo, gbc);
                gbc.gridy++;
                JLabel meleeHint = new JLabel(LocaleChanger.getString("commands.hint.meleeWeapon.select"));
                meleeHint.setFont(meleeHint.getFont().deriveFont(Font.PLAIN, 11f));
                fieldsPanel.add(meleeHint, gbc);
                gbc.gridy++;
                fieldInputs.put("meleeWeapon", combo);
            }
        }
        fieldsPanel.putClientProperty("inputs", fieldInputs);
        resultArea.setRows(10);
        resultArea.setColumns(30);
        resultScroll.setPreferredSize(new Dimension(350, 200));
        fieldsPanel.add(executeButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        fieldsPanel.add(resultScroll, gbc);

        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }

    /**
     * Обновляет локализацию полей для текущей команды, если они отображаются.
     */
    public void updateLocale() {
        if (currentCommand != null) {
            showFieldsForCommand(currentCommand);
        }
    }

    public Map<String, JComponent> getFieldInputs() {
        return fieldInputs;
    }
}