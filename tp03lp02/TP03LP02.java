/*
 * João Victor dos Santos, Luis Tiago Ferreira.
 */
package tp03lp02;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author aluno
 */
public class TP03LP02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        SwingUtilities.invokeLater(() -> {
           new Form().setVisible(true);
        });
        /*
        SwingUtilities.invokeLater(() -> {
            new FormPessoa().setVisible(true);
        });
        
        SwingUtilities.invokeLater(() -> new FormPessoaV2().setVisible(true));
        */
        SwingUtilities.invokeLater(() -> new FormPessoaV3().setVisible(true));
       
    }
}
