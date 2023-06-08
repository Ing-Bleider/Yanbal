package modelo;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Eventos {

    public void textKeyPress(KeyEvent evt) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    public void numberKeyPress(KeyEvent evt) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
        //Declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        String text = textField.getText();

        if ((car < '0' || car > '9') && car != '.' && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if (car == '.' && text.contains(".")) {
            evt.consume();
        }
    }

}
