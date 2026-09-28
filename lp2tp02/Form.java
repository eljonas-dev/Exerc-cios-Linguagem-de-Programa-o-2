/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedev.lp2tp02;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class Form extends Frame {
    private java.util.List listaAlunos;
    
    public Form(){
        setTitle("TP02 - LP2I4");
        setSize(400, 180);
        
        setLayout(new BorderLayout());
        
        listaAlunos = new ArrayList<>();
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        TextField txtNome = new TextField();
        TextField txtIdade = new TextField();
        TextField txtEndereco = new TextField();
        
        //Painel superior
        Panel painelSuperior = new Panel();
        painelSuperior.setLayout(new GridLayout(3, 2, 10, 10)); // 3 linhas, 2 colunas, espaçamento 10px
        
        painelSuperior.add(new Label("Nome:"));
        painelSuperior.add(txtNome);

        painelSuperior.add(new Label("Idade:"));
        painelSuperior.add(txtIdade);

        painelSuperior.add(new Label("Endereço:"));
        painelSuperior.add(txtEndereco);
        
        //Painel inferior
        Panel painelInferior = new Panel();
        painelInferior.setLayout(new GridLayout(1, 4, 5, 5));
      
        Button btnOk = new Button("Ok");
        Button btnLimpar = new Button("Limpar");
        Button btnMostrar = new Button("Mostrar");
        Button btnSair = new Button("Sair");
        
        
        
        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);
        
        //Adicionando ao frame principal
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        
        btnOk.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   String nome = txtNome.getText();
                   int idade = Integer.parseInt(txtIdade.getText());
                   String endereco = txtEndereco.getText();
                   
                   Aluno novoAluno = new Aluno();
                   
                   novoAluno.setNome(nome);
                   novoAluno.setIdade(idade);
                   novoAluno.setEndereco(endereco);
                   
                   listaAlunos.add(novoAluno);
                   System.out.println("Aluno cadastrado com sucesso: " + novoAluno);
               } catch (NumberFormatException ex) {
                   System.out.println("Por favor, digite um número válido para a idade.");
               }
           }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
               txtNome.setText("");
               txtIdade.setText("");
               txtEndereco.setText("");
           } 
        });
        
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mensagem = "Resultado\n";
                for (Object obj : listaAlunos) {
                    Aluno a = (Aluno) obj; // Faz a conversão manual aqui
                    mensagem += "Id: " + a.getUuid() + " Nome: " + a.getNome() + "\n";
                }
                javax.swing.JOptionPane.showMessageDialog(Form.this, mensagem);
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        setVisible(true);
    } 
}