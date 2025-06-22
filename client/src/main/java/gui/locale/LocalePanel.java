package gui.locale;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class LocalePanel extends JPanel {
    public final static String font = "Malgun Gothic";
    private final JComboBox<String> localeBox;


    public LocalePanel(Runnable onLocaleChange) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        localeBox = new JComboBox<>(LocaleChanger.getLanguageNames());
        localeBox.setSelectedIndex(getCurrentLocaleIndex());
        localeBox.setFont(new Font(font, Font.PLAIN, 18));
        localeBox.setPreferredSize(new Dimension(200, 40));
        add(localeBox);

        localeBox.addActionListener(e -> {
            int idx = localeBox.getSelectedIndex();
            LocaleChanger.setLocale(LocaleChanger.getAvailableLocales()[idx]);
            if (onLocaleChange != null) onLocaleChange.run();
            updateLocale();
        });
    }

    private int getCurrentLocaleIndex() {
        Locale current = LocaleChanger.getCurrentLocale();
        Locale[] locales = LocaleChanger.getAvailableLocales();
        for (int i = 0; i < locales.length; i++) {
            Locale l = locales[i];
            if (l.getLanguage().equals(current.getLanguage()) &&
                (l.getCountry().isEmpty() || l.getCountry().equals(current.getCountry()))) {
                return i;
            }
        }
        return 0;
    }

    public void updateLocale() {
        int idx = getCurrentLocaleIndex();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(LocaleChanger.getLanguageNames());
        localeBox.setModel(model);
        localeBox.setSelectedIndex(idx);
        localeBox.repaint();
    }
}
