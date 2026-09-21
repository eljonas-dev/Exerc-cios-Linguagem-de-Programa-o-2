/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tp02;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author aluno
 */
public class Form extends Frame {
    public Form(){
        setTitle("TP02 - LP2I4");
        setSize(400, 180);
        
        setLayout(new BorderLayout());
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        //Painel superior
        Panel painelSuperior = new Panel();
        painelSuperior.setLayout(new GridLayout(3, 2, 10, 10)); // 3 linhas, 2 colunas, espaçamento 10px

        painelSuperior.add(new Label("Nome:"));
        painelSuperior.add(new TextField());

        painelSuperior.add(new Label("Idade:"));
        painelSuperior.add(new TextField());

        painelSuperior.add(new Label("Endereço:"));
        painelSuperior.add(new TextField());
        
        //Painel inferior
        Panel painelInferior = new Panel();
        painelInferior.setLayout(new GridLayout(1, 4, 5, 5));
        
        painelInferior.add(new Button("Ok"));
        painelInferior.add(new Button("Limpar"));
        painelInferior.add(new Button("Mostrar"));
        painelInferior.add(new Button("Sair"));
        
        //Adicionando ao frame principal
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        
        setVisible(true);
    } 
}
