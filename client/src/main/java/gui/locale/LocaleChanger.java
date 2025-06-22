package gui.locale;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleChanger {
    private static ResourceBundle bundle;
    private static Locale currentLocale;

    public static final Locale LOCALE_RU = new Locale("ru");
    public static final Locale LOCALE_JA = new Locale("ja");
    public static final Locale LOCALE_KO = new Locale("ko");
    public static final Locale LOCALE_ZH = new Locale("zh");

    static {
        setLocale(LOCALE_RU);
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        try {
            bundle = ResourceBundle.getBundle("locales.messages", locale);
        } catch (MissingResourceException e) {
            System.err.println("Ресурсный файл для локали " + locale + " не найден");
            bundle = ResourceBundle.getBundle("locales.messages", LOCALE_RU);
        }
    }


    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            System.err.println("Ключ " + key + " не найден в ресурсном файле");
            return key;
        }
    }


    public static String getString(String key, Object... params) {
        try {
            return String.format(getCurrentLocale(), bundle.getString(key), params);
        } catch (MissingResourceException e) {
            System.err.println("Ключ " + key + " не найден в ресурсном файле");
            return "!" + key + "!";
        }
    }
    

    public static Locale[] getAvailableLocales() {
        return new Locale[] {LOCALE_RU, LOCALE_JA, LOCALE_KO, LOCALE_ZH};
    }
    

    public static String[] getLanguageNames() {
        return new String[] {
            getString("language.ru"),
            getString("language.ja"),
            getString("language.ko"),
            getString("language.zh")
        };
    }
}