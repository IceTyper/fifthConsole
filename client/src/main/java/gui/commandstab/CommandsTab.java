package gui.commandstab;

import gui.locale.LocaleChanger;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static gui.locale.LocalePanel.font;

public class CommandsTab extends JPanel {
    private final JPanel fieldsPanel = new JPanel(new GridBagLayout());
    private final JScrollPane fieldsScroll = new JScrollPane(fieldsPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private final JPanel buttonsPanel = new JPanel();
    private final JScrollPane buttonsScroll = new JScrollPane(buttonsPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private final Map<String, CommandMeta> commands = new LinkedHashMap<>();
    private final Map<String, JButton> commandButtons = new LinkedHashMap<>();
    private final JButton executeButton = new JButton();
    private final JTextArea resultArea = new JTextArea(10, 30);
    private final JScrollPane resultScroll = new JScrollPane(resultArea);
    private final CommandFieldsPanel commandFieldsPanel;
    private final CommandExecutor commandExecutor;
    private String currentCommand = null;

    public CommandsTab() {
        setLayout(new BorderLayout());

        commands.put("help", new CommandMeta("help", new String[]{}));
        commands.put("info", new CommandMeta("info", new String[]{}));
        commands.put("add", new CommandMeta("add", new String[]{"object"}));
        commands.put("update", new CommandMeta("update", new String[]{"id", "object"}));
        commands.put("remove_by_id", new CommandMeta("remove_by_id", new String[]{"id"}));
        commands.put("clear", new CommandMeta("clear", new String[]{}));
        commands.put("remove_head", new CommandMeta("remove_head", new String[]{}));
        commands.put("add_if_max", new CommandMeta("add_if_max", new String[]{"object"}));
        commands.put("remove_lower", new CommandMeta("remove_lower", new String[]{"object"}));
        commands.put("sum_of_health", new CommandMeta("sum_of_health", new String[]{}));
        commands.put("filter_greater_than_melee_weapon", new CommandMeta("filter_greater_than_melee_weapon", new String[]{"meleeWeapon"}));
        commands.put("print_field_ascending_health", new CommandMeta("print_field_ascending_health", new String[]{}));
        commands.put("execute_script", new CommandMeta("execute_script", new String[]{"file_name"}));

        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(8, 0, 8, 0);

        int maxWidth = 0;
        Font btnFont = new Font(font, Font.PLAIN, 16);
        for (String cmd : commands.keySet()) {
            JButton tmp = new JButton(cmd);
            tmp.setFont(btnFont);
            int width = tmp.getPreferredSize().width;
            if (width > maxWidth) maxWidth = width;
        }
        maxWidth += 30;

        commandFieldsPanel = new CommandFieldsPanel(fieldsPanel, resultArea, resultScroll, executeButton, commands);
        commandExecutor = new CommandExecutor(resultArea, commands, fieldsPanel);

        int row = 0;
        for (String cmd : commands.keySet()) {
            JButton btn = new JButton(cmd);
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(maxWidth, 40));
            btn.setMaximumSize(new Dimension(maxWidth, 40));
            btn.setMinimumSize(new Dimension(maxWidth, 40));
            commandButtons.put(cmd, btn);
            CommandMeta meta = commands.get(cmd);
            if (meta.fields.length == 0) {
                btn.addActionListener(e -> {
                    currentCommand = cmd;
                    commandFieldsPanel.clearAndShowResultArea(true);
                    commandFieldsPanel.currentCommand = null;
                    commandExecutor.executeCommand(cmd, null);
                });
            } else {
                btn.addActionListener(e -> {
                    currentCommand = cmd;
                    commandFieldsPanel.showFieldsForCommand(cmd);
                });
            }
            gbc.gridy = row++;
            buttonsPanel.add(btn, gbc);
        }
        JPanel leftGap = new JPanel();
        leftGap.setPreferredSize(new Dimension(40, 10));
        JPanel buttonsWrapper = new JPanel(new BorderLayout());
        buttonsWrapper.add(leftGap, BorderLayout.WEST);

        buttonsScroll.setBorder(null);
        buttonsScroll.getVerticalScrollBar().setUnitIncrement(32);
        buttonsScroll.setMinimumSize(new Dimension(maxWidth + 40, 100));
        buttonsScroll.setPreferredSize(new Dimension(maxWidth + 40, 600));
        buttonsScroll.setMaximumSize(new Dimension(maxWidth + 40, Integer.MAX_VALUE));
        buttonsWrapper.add(buttonsScroll, BorderLayout.CENTER);

        fieldsScroll.getVerticalScrollBar().setUnitIncrement(32);

        add(buttonsWrapper, BorderLayout.WEST);
        add(fieldsScroll, BorderLayout.CENTER);

        executeButton.addActionListener(e -> {
            if ("execute_script".equals(currentCommand)) {
                commandExecutor.executeScriptCommand(commandFieldsPanel.getFieldInputs());
            } else {
                commandExecutor.executeCommand(currentCommand, commandFieldsPanel.getFieldInputs());
            }
        });
        executeButton.setFont(btnFont);
        executeButton.setPreferredSize(new Dimension(maxWidth, 40));
        executeButton.setMaximumSize(new Dimension(maxWidth, 40));
        executeButton.setMinimumSize(new Dimension(maxWidth, 40));

        resultArea.setEditable(false);
        resultArea.setFont(new Font(font, Font.BOLD, 18));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultScroll.setPreferredSize(new Dimension(350, 200));

        updateLocale();
    }


    public void updateLocale() {
        executeButton.setText(LocaleChanger.getString("commands.execute"));
        commandFieldsPanel.updateLocale();
        repaint();
    }
}