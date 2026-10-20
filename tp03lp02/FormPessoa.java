package tp03lp02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoa extends JFrame {

    private JTextField txtNumero;
    private JTextField txtNome;
    private JTextField txtSexo;
    private JTextField txtIdade;
    private JButton btnOK;

    public FormPessoa() {
        setTitle("Cadastro de Pessoa");
        setSize(320, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 8, 8));

        // Rótulos e Campos
        add(new JLabel("  Numero:"));
        txtNumero = new JTextField("1");
        txtNumero.setEditable(false); // Campo Número não editável
        add(txtNumero);

        add(new JLabel("  Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("  Sexo (M/F):"));
        txtSexo = new JTextField();
        add(txtSexo);

        add(new JLabel("  Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        // Botão OK
        btnOK = new JButton("OK");
        add(new JLabel("")); // Espaço em branco para alinhar o botão à direita
        add(btnOK);

        // Ação do Botão OK
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPessoa();
            }
        });
    }

    private void salvarPessoa() {
        String nome = txtNome.getText().trim();
        String sexoStr = txtSexo.getText().trim();
        String idadeStr = txtIdade.getText().trim();

        // Validação: Campos obrigatórios
        if (nome.isEmpty() || sexoStr.isEmpty() || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Os campos Nome, Sexo e Idade são de preenchimento obrigatório!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            char sexo = sexoStr.toUpperCase().charAt(0);
            int idade = Integer.parseInt(idadeStr);

            // Cria o objeto Pessoa (o construtor incrementa o kp automaticamente)
            Pessoa p = new Pessoa(nome, sexo, idade);

            JOptionPane.showMessageDialog(this, 
                "Pessoa cadastrada com sucesso!\nTotal de pessoas distintas (kp): " + Pessoa.getKp(), 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);

            // Limpa os campos para o próximo cadastro e atualiza o número
            txtNome.setText("");
            txtSexo.setText("");
            txtIdade.setText("");
            txtNumero.setText(String.valueOf(Pessoa.getKp() + 1));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, insira um número inteiro válido para a idade.", 
                "Erro de Formatação", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}