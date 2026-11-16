//João Victor dos Santos, Luis Tiago Ferreira

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class FormTrabalhoPratico extends JFrame {

    private JTextField txtBuscaNome;
    private JButton btnPesquisar;

    private JTextField txtNome;
    private JTextField txtSalario;
    private JTextField txtCargo;

    private JButton btnAnterior;
    private JButton btnProximo;

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public FormTrabalhoPratico() {
        setTitle("TRABALHO PRATICO 04");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        painelSuperior.add(new JLabel("Nome:"));
        txtBuscaNome = new JTextField(18);
        painelSuperior.add(txtBuscaNome);

        btnPesquisar = new JButton("Pesquisar");
        JPanel painelBuscaCompleto = new JPanel();
        painelBuscaCompleto.setLayout(new BoxLayout(painelBuscaCompleto, BoxLayout.Y_AXIS));

        JPanel painelBotaoBusca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotaoBusca.add(btnPesquisar);

        painelBuscaCompleto.add(painelSuperior);
        painelBuscaCompleto.add(painelBotaoBusca);

        JPanel painelCentral = new JPanel(new GridLayout(3, 2, 5, 5));

        txtNome = new JTextField();
        txtSalario = new JTextField();
        txtCargo = new JTextField();

        txtNome.setEditable(false);
        txtSalario.setEditable(false);
        txtCargo.setEditable(false);

        painelCentral.add(new JLabel("  Nome:"));
        painelCentral.add(txtNome);

        painelCentral.add(new JLabel("  Salário:"));
        painelCentral.add(txtSalario);

        painelCentral.add(new JLabel("  Cargo:"));
        painelCentral.add(txtCargo);

        JPanel painelInferior = new JPanel(new GridLayout(1, 2, 2, 0));

        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");

        painelInferior.add(btnAnterior);
        painelInferior.add(btnProximo);

        add(painelBuscaCompleto, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        conectarBanco();
        configurarEventos();
    }

    private void conectarBanco() {
        try {
            conn = Conexao.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    private void configurarEventos() {
        btnPesquisar.addActionListener(e -> pesquisar());
        btnAnterior.addActionListener(e -> moverAnterior());
        btnProximo.addActionListener(e -> moverProximo());
    }

    private void pesquisar() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }

            String sql = "SELECT f.nome_func, f.sal_func, c.ds_cargo "
                    + "FROM tbfuncs f "
                    + "INNER JOIN tbcargos c ON f.cod_cargo = c.cd_cargo "
                    + "WHERE f.nome_func LIKE ?";

            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, "%" + txtBuscaNome.getText().trim() + "%");

            rs = pstmt.executeQuery();

            if (rs.next()) {
                exibirRegistroAtual();
            } else {
                limparCampos();
                JOptionPane.showMessageDialog(this, "Nenhum registro encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + ex.getMessage());
        }
    }

    private void moverAnterior() {
        try {
            if (rs != null && !rs.isClosed()) {
                if (rs.previous()) {
                    exibirRegistroAtual();
                } else {
                    rs.first();
                    JOptionPane.showMessageDialog(this, "Você já está no primeiro registro.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao navegar: " + ex.getMessage());
        }
    }

    private void moverProximo() {
        try {
            if (rs != null && !rs.isClosed()) {
                if (rs.next()) {
                    exibirRegistroAtual();
                } else {
                    rs.last();
                    JOptionPane.showMessageDialog(this, "Você já está no último registro.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao navegar: " + ex.getMessage());
        }
    }

    private void exibirRegistroAtual() throws SQLException {
        txtNome.setText(rs.getString("nome_func").trim());
        txtSalario.setText(String.format("%.2f", rs.getDouble("sal_func")).replace(",", "."));
        txtCargo.setText(rs.getString("ds_cargo").trim());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSalario.setText("");
        txtCargo.setText("");
    }

    public static void main(String[] args) {
        Conexao.inicializarBanco();
        SwingUtilities.invokeLater(() -> {
            FormTrabalhoPratico form = new FormTrabalhoPratico();
            form.setVisible(true);
        });
    }
}