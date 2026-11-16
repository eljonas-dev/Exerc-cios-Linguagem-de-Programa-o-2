import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormTrabalhoPratico extends JFrame {

    private JTextField txtBuscaNome;
    private JButton btnPesquisar;

    private JTextField txtNome;
    private JTextField txtSalario;
    private JTextField txtCargo;

    private JButton btnAnterior;
    private JButton btnProximo;

    private Connection conn;
    private List<String[]> resultados;
    private int indiceAtual;

    public FormTrabalhoPratico() {
        setTitle("TRABALHO PRATICO 04");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        resultados = new ArrayList<>();
        indiceAtual = -1;

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
        String sql = "SELECT f.nome_func, f.sal_func, c.ds_cargo "
                + "FROM tbfuncs f "
                + "INNER JOIN tbcargos c ON f.cod_cargo = c.cd_cargo "
                + "WHERE f.nome_func LIKE ?";

        try {
            if (conn == null || conn.isClosed()) {
                conectarBanco();
                if (conn == null) {
                    return;
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%" + txtBuscaNome.getText().trim() + "%");

                try (ResultSet rs = pstmt.executeQuery()) {
                    resultados.clear();
                    while (rs.next()) {
                        String nome = rs.getString("nome_func").trim();
                        String salario = String.format("%.2f", rs.getDouble("sal_func")).replace(",", ".");
                        String cargo = rs.getString("ds_cargo").trim();
                        resultados.add(new String[]{nome, salario, cargo});
                    }
                }
            }

            if (!resultados.isEmpty()) {
                indiceAtual = 0;
                exibirRegistroAtual();
            } else {
                indiceAtual = -1;
                limparCampos();
                JOptionPane.showMessageDialog(this, "Nenhum registro encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro na pesquisa: " + ex.getMessage());
        }
    }

    private void moverAnterior() {
        if (resultados.isEmpty()) {
            return;
        }
        if (indiceAtual > 0) {
            indiceAtual--;
            exibirRegistroAtual();
        } else {
            JOptionPane.showMessageDialog(this, "Você já está no primeiro registro.");
        }
    }

    private void moverProximo() {
        if (resultados.isEmpty()) {
            return;
        }
        if (indiceAtual < resultados.size() - 1) {
            indiceAtual++;
            exibirRegistroAtual();
        } else {
            JOptionPane.showMessageDialog(this, "Você já está no último registro.");
        }
    }

    private void exibirRegistroAtual() {
        String[] registro = resultados.get(indiceAtual);
        txtNome.setText(registro[0]);
        txtSalario.setText(registro[1]);
        txtCargo.setText(registro[2]);
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