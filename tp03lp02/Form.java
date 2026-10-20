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
public class Form extends JFrame {
    public JTextField txtDisplay;
    public double valor1 = 0;
    public String operacao = "";
    public boolean novoNumero = true;
    
    public Form() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        txtDisplay = new JTextField("0");
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 20));
        txtDisplay.setEditable(false);
        add(txtDisplay, BorderLayout.NORTH);
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(5, 4, 3, 3));
        
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "",  "",  ""  
        };
        
        for (String texto : botoes){
            if (texto.isEmpty()) {
                panelBotoes.add(new JLabel(""));
            } else {
                JButton btn = new JButton(texto);
                btn.setFont(new Font("Arial", Font.PLAIN, 16));
                btn.addActionListener(new BotaoClickListener(this));
                panelBotoes.add(btn);
            }
            
            add(panelBotoes, BorderLayout.CENTER);
        }
    }
    
}
