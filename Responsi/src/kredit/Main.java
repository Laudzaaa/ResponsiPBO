package kredit;


import javax.swing.*;
import kredit.view.KreditView;

public class Main {
 public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
    KreditView view = new KreditView();
    view.setVisible(true);
 });
 }
}