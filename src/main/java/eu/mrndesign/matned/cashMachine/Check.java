package eu.mrndesign.matned.cashMachine;

import javax.swing.*;

public class Check {

    public static boolean isNumeric(String value) {
        try {
            long isNumeric = Long.parseLong(value.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static String getPassword(JPasswordField field){
        StringBuilder stringBuilder = new StringBuilder();
        for (char el: field.getPassword()) {
            stringBuilder.append(el);
        }
        return String.valueOf(stringBuilder);
    }
}
