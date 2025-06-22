package gui.commandstab;

import connection.Message;
import connection.User;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import gui.locale.LocaleChanger;

import javax.swing.*;
import java.util.Map;

public class CommandExecutor {
    private final JTextArea resultArea;
    private final Map<String, CommandMeta> commands;
    private final JPanel fieldsPanel;

    public CommandExecutor(JTextArea resultArea, Map<String, CommandMeta> commands, JPanel fieldsPanel) {
        this.resultArea = resultArea;
        this.commands = commands;
        this.fieldsPanel = fieldsPanel;
    }

    public void executeCommand(String cmd, Map<String, JComponent> fieldInputs) {
        if (cmd == null) return;
        CommandMeta meta = commands.get(cmd);
        Object[] args;
        try {
            args = collectArgs(meta, fieldInputs);
        } catch (Exception ex) {
            resultArea.setText(LocaleChanger.getString("commands.error.args") + ex.getMessage());
            return;
        }
        try {
            Message msg = new Message(cmd, prependUser(args));
            Message response = Sender.sendAndReceive(msg);
            resultArea.setText(formatServerResponse(response));
        } catch (Exception ex) {
            resultArea.setText(LocaleChanger.getString("commands.error.send") + ex.getMessage());
        }
    }

    public void executeScriptCommand(Map<String, JComponent> fieldInputs) {
        try {
            String filename = "";
            if (fieldInputs != null && fieldInputs.containsKey("file_name")) {
                filename = ((JTextField) fieldInputs.get("file_name")).getText().trim();
            }
            Message msg = new Message("execute_script", prependUser(new Object[]{filename}));
            Message response = Sender.sendAndReceive(msg);
            resultArea.setText(formatServerResponse(response));
        } catch (Exception ex) {
            resultArea.setText(LocaleChanger.getString("commands.error.send") + ex.getMessage());
        }
    }

    private String formatServerResponse(Message response) {
        if (response == null || response.args().length <= 1) {
            return LocaleChanger.getString("commands.no_response");
        }
        StringBuilder resultText = new StringBuilder();
        Object[] arr = response.args();
        boolean allMessages = true;
        for (int i = 1; i < arr.length; i++) {
            if (!(arr[i] instanceof Message)) {
                allMessages = false;
                break;
            }
        }
        if (allMessages) {
            for (int i = 1; i < arr.length; i++) {
                try {
                    resultText.append(formatServerResponse((Message) arr[i]));
                } catch (Exception e) {
                    resultText.append(e.getMessage()).append("\n");
                }
            }
        } else {
            for (int i = 1; i < arr.length; i++) {
                Object result = arr[i];
                if (result instanceof String str) {
                    String[] lines = str.split("\\r?\\n");
                    for (String line : lines) {
                        resultText.append(line).append("\n");
                    }
                } else if (result instanceof String[] strArr) {
                    for (String s : strArr) {
                        resultText.append(s).append("\n");
                    }
                } else if (result instanceof Object[] objArr) {
                    for (Object o : objArr) {
                        resultText.append(o).append("\n");
                    }
                } else if (result != null) {
                    resultText.append(result.toString()).append("\n");
                }
            }
        }
        return resultText.toString();
    }

    private Object[] collectArgs(CommandMeta meta, Map<String, JComponent> fieldInputs) {
        if (meta.fields.length == 0) return new Object[]{};
        if ("execute_script".equals(meta.name)) {
            String filename = "";
            if (fieldInputs != null && fieldInputs.containsKey("file_name")) {
                filename = ((JTextField) fieldInputs.get("file_name")).getText().trim();
            }
            return new Object[]{filename};
        }
        if (meta.fields.length == 2 && meta.fields[0].equals("id") && meta.fields[1].equals("object")) {
            Object[] args = new Object[11];
            int argIndex = 0;
            String text = ((JTextField) fieldInputs.get("id")).getText().trim();
            if (text.isEmpty()) throw new IllegalArgumentException("id cannot be empty");
            args[argIndex++] = text;
            args[argIndex++] = ((JTextField) fieldInputs.get("name")).getText();
            args[argIndex++] = ((JTextField) fieldInputs.get("coordinates.x")).getText();
            args[argIndex++] = ((JTextField) fieldInputs.get("coordinates.y")).getText();
            args[argIndex++] = ((JTextField) fieldInputs.get("health")).getText();
            args[argIndex++] = ((JComboBox<?>) fieldInputs.get("loyal")).getSelectedItem().toString();
            args[argIndex++] = ((JComboBox<?>) fieldInputs.get("weaponType")).getSelectedItem().toString();
            args[argIndex++] = ((JComboBox<?>) fieldInputs.get("meleeWeapon")).getSelectedItem().toString();
            args[argIndex++] = ((JTextField) fieldInputs.get("chapter.name")).getText();
            args[argIndex++] = ((JTextField) fieldInputs.get("chapter.marinesCount")).getText();
            args[argIndex++] = ((JTextField) fieldInputs.get("chapter.world")).getText();
            return args;
        }
        Object[] args = new Object[meta.fields.length == 1 && meta.fields[0].equals("object") ? 10 : meta.fields.length];
        int argIndex = 0;
        for (String field : meta.fields) {
            if (field.equals("object")) {
                args[argIndex++] = ((JTextField) fieldInputs.get("name")).getText();
                args[argIndex++] = ((JTextField) fieldInputs.get("coordinates.x")).getText();
                args[argIndex++] = ((JTextField) fieldInputs.get("coordinates.y")).getText();
                args[argIndex++] = ((JTextField) fieldInputs.get("health")).getText();
                args[argIndex++] = ((JComboBox<?>) fieldInputs.get("loyal")).getSelectedItem().toString();
                args[argIndex++] = ((JComboBox<?>) fieldInputs.get("weaponType")).getSelectedItem().toString();
                args[argIndex++] = ((JComboBox<?>) fieldInputs.get("meleeWeapon")).getSelectedItem().toString();
                args[argIndex++] = ((JTextField) fieldInputs.get("chapter.name")).getText();
                args[argIndex++] = ((JTextField) fieldInputs.get("chapter.marinesCount")).getText();
                args[argIndex++] = ((JTextField) fieldInputs.get("chapter.world")).getText();
            } else if (field.equals("id")) {
                String text = ((JTextField) fieldInputs.get("id")).getText().trim();
                if (text.isEmpty()) throw new IllegalArgumentException("id cannot be empty");
                args[argIndex++] = text;
            } else if (field.equals("meleeWeapon")) {
                args[argIndex++] = ((JComboBox<?>) fieldInputs.get("meleeWeapon")).getSelectedItem().toString();
            }
        }
        if (argIndex < args.length) {
            Object[] trimmed = new Object[argIndex];
            System.arraycopy(args, 0, trimmed, 0, argIndex);
            return trimmed;
        }
        return args;
    }

    private Object[] prependUser(Object[] args) {
        User user = UDPDatagramClient.getUser();
        Object[] result = new Object[args.length + 1];
        result[0] = user;
        System.arraycopy(args, 0, result, 1, args.length);
        return result;
    }
}
