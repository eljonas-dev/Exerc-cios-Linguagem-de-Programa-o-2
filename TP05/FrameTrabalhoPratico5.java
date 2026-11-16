//João Victor dos Santos, Luis Tiago Ferreira

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FrameTrabalhoPratico5 extends JFrame {

    private JTextField txtMaximo;
    private JTextField txtPrioridade1;
    private JTextField txtPrioridade2;
    private JButton btnIniciar;
    private JButton btnLimpar;
    private ContadorRunnable painelLista1;
    private ContadorRunnable painelLista2;

    public FrameTrabalhoPratico5() {
        setTitle("Trabalho Prático 5");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelSuperior.add(new JLabel("Máximo:"), gbc);

        gbc.gridx = 1;
        txtMaximo = new JTextField("1000", 12);
        painelSuperior.add(txtMaximo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelSuperior.add(new JLabel("Prioridade 1:"), gbc);

        gbc.gridx = 1;
        txtPrioridade1 = new JTextField("1", 12);
        painelSuperior.add(txtPrioridade1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelSuperior.add(new JLabel("Prioridade 2:"), gbc);

        gbc.gridx = 1;
        txtPrioridade2 = new JTextField("10", 12);
        painelSuperior.add(txtPrioridade2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        painelSuperior.add(new JLabel("Número entre 1 e 10"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnIniciar = new JButton("Iniciar");
        painelSuperior.add(btnIniciar, gbc);

        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 10));
        painelLista1 = new ContadorRunnable();
        painelLista2 = new ContadorRunnable();
        painelCentral.add(painelLista1);
        painelCentral.add(painelLista2);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnLimpar = new JButton("Limpar");
        painelInferior.add(btnLimpar);

        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        btnIniciar.addActionListener(e -> iniciarThreads());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void iniciarThreads() {
        try {
            int maximo = Integer.parseInt(txtMaximo.getText().trim());
            int p1 = Integer.parseInt(txtPrioridade1.getText().trim());
            int p2 = Integer.parseInt(txtPrioridade2.getText().trim());

            if (maximo <= 0) {
                JOptionPane.showMessageDialog(this, "O valor máximo deve ser maior que zero.");
                return;
            }

            if (p1 < 1 || p1 > 10 || p2 < 1 || p2 > 10) {
                JOptionPane.showMessageDialog(this, "As prioridades devem estar entre 1 e 10.");
                return;
            }

            painelLista1.configurar(maximo, p1);
            painelLista2.configurar(maximo, p2);

            Thread t1 = new Thread(painelLista1);
            Thread t2 = new Thread(painelLista2);

            t1.start();
            t2.start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos com números inteiros válidos.");
        }
    }

    private void limparCampos() {
        txtMaximo.setText("");
        txtPrioridade1.setText("");
        txtPrioridade2.setText("");
        painelLista1.limpar();
        painelLista2.limpar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrameTrabalhoPratico5().setVisible(true);
        });
    }
}