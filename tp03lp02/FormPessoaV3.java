package tp03lp02;

import javax.swing.*;
import java.awt.*;

public class FormPessoaV3 extends JFrame {

    private JTextField txtNumero;
    private JTextField txtNome;
    private JRadioButton rbMasculino;
    private JRadioButton rbFeminino;
    private ButtonGroup bgSexo;
    private JTextField txtIdade;
    private JButton btnOK, btnLimpar, btnMostrar, btnSair;
    private Pessoa pessoaCadastrada;

    public FormPessoaV3() {
        setTitle("Semana 06 - Exercicio 01");
        setSize(360, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // Painel dos Campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        panelCampos.add(new JLabel(" Numero:"));
        txtNumero = new JTextField("1");
        txtNumero.setEditable(false);
        panelCampos.add(txtNumero);

        panelCampos.add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        panelCampos.add(txtNome);

        panelCampos.add(new JLabel(" Sexo:"));
        
        // JRadioButton agrupados
        rbMasculino = new JRadioButton("M");
        rbFeminino = new JRadioButton("F");
        bgSexo = new ButtonGroup();
        bgSexo.add(rbMasculino);
        bgSexo.add(rbFeminino);

        JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSexo.add(rbMasculino);
        panelSexo.add(rbFeminino);
        panelCampos.add(panelSexo);

        panelCampos.add(new JLabel(" Idade:"));
        txtIdade = new JTextField();
        panelCampos.add(txtIdade);

        add(panelCampos, BorderLayout.CENTER);

        // Painel dos Botões
        JPanel panelBotoes = new JPanel(new GridLayout(1, 4, 5, 5));
        btnOK = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        panelBotoes.add(btnOK);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnMostrar);
        panelBotoes.add(btnSair);

        add(panelBotoes, BorderLayout.SOUTH);

        // Eventos
        btnOK.addActionListener(e -> salvar());
        btnLimpar.addActionListener(e -> limpar());
        btnMostrar.addActionListener(e -> mostrar());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String idadeStr = txtIdade.getText().trim();

        if (nome.isEmpty() || (!rbMasculino.isSelected() && !rbFeminino.isSelected()) || idadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            char sexo = rbMasculino.isSelected() ? 'M' : 'F';
            int idade = Integer.parseInt(idadeStr);

            pessoaCadastrada = new Pessoa(nome, sexo, idade);
            JOptionPane.showMessageDialog(this, "Pessoa cadastrada! (kp = " + Pessoa.getKp() + ")", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtNumero.setText(String.valueOf(Pessoa.getKp() + 1));
            limpar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        txtNome.setText("");
        bgSexo.clearSelection();
        txtIdade.setText("");
    }

    private void mostrar() {
        if (pessoaCadastrada == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma pessoa cadastrada ainda!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Nome: " + pessoaCadastrada.getNome() + 
                "\nSexo: " + pessoaCadastrada.getSexo() + 
                "\nIdade: " + pessoaCadastrada.getIdade() + 
                "\nTotal (kp): " + Pessoa.getKp(), 
                "Dados da Pessoa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}