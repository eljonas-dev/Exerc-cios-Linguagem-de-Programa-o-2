/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp03lp02;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author aluno
 */
public class BotaoClickListener implements ActionListener {
    private Form form;
    
    public BotaoClickListener(Form form) {
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();   
        
        try {
            if ("0123456789.".contains(comando)){
                if (form.novoNumero) {
                    form.txtDisplay.setText(comando.equals(".") ? "0." : comando);
                    form.novoNumero = false;           
            } else {
                    if (comando.equals(".") && form.txtDisplay.getText().contains(".")) {
                        return;
                    }
                    form.txtDisplay.setText(form.txtDisplay.getText() + comando);
                }
        }
            else if ("+-*/".contains(comando)) {
                form.valor1 = Double.parseDouble(form.txtDisplay.getText().replace(",", "."));
                form.operacao = comando;
                form.novoNumero = true;
            }
            else if (comando.equals("=")) {
                if(!form.operacao.isEmpty()) {
                    double valor2 = Double.parseDouble(form.txtDisplay.getText().replace(",", "."));
                    double resultado = calcular(form.valor1, valor2, form.operacao);
                    
                limparMemoria();
                    if (resultado == (long) resultado) {
                        form.txtDisplay.setText(String.format("%d", (long) resultado));
                    } else {
                        form.txtDisplay.setText(String.valueOf(resultado));
                    }
                }
            }
            
            else if (comando.equals("C")) {
                limparMemoria();
            }
    } catch (ArithmeticException ex) {
        JOptionPane.showMessageDialog(null, "Erro aritméico " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        limparMemoria();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Entrada de número inválida!", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
        limparMemoria();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        limparMemoria();
    } finally {
            System.out.println("Ação fo botão '" + comando + "' processada.");
        }

  }
    private double calcular(double n1, double n2, String op) throws ArithmeticException {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/":
                if (n2 == 0) {
                    throw new ArithmeticException("Divisão por zero não permitida!");
                }
                    return n1 / n2;
            default: return 0;     
        }
    }
    
    private void limparMemoria() {
        form.valor1 = 0;
        form.operacao = "";
        form.txtDisplay.setText("0");
        form.novoNumero = true;
    }
}
