import java.awt.*;
import javax.swing.*;

public class FormTrabalhoPratico extends JFrame {
    //Componentes de busca
    private JTextField txtBuscaNome;
    private JButton btnPesquisar;

    //Componentes do formulário
    private JTextField txtNome;
    private JTextField txtSalario;
    private JTextField txtCargo;

    //Botões de navegação 
    private JButton btnAnterior;
    private JButton btnProximo;

    public FormTrabalhoPratico() {
        //Configurações da janela
        setTitle("Trabalho Prático 04");
        setSize(400,260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        //1- PAINEL SUPERIOOOR:
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        painelSuperior.add(new JLabel("Nome:"));
        txtBuscaNome = new JTextField(18);
        painelSuperior.add(txtBuscaNome);

        btnPesquisar = new JButton("Pesquisar");

        //Painel auxiliar pro botão pesquisar abaixo do campo de busca
        JPanel painelBuscaCompleto = new JPanel();
        painelBuscaCompleto.setLayout(new BoxLayout(painelBuscaCompleto, BoxLayout.Y_AXIS));

        //Painel para alinhar o botão no centro
        JPanel painelBotaoBusca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotaoBusca.add(btnPesquisar);

        painelBuscaCompleto.add(painelSuperior);
        painelBuscaCompleto.add(painelBotaoBusca);

        //2- PAINEL CENTRAAL
        JPanel painelCentral = new JPanel(new GridLayout(3, 2, 5, 5));

        txtNome = new JTextField();
        txtSalario = new JTextField();
        txtCargo = new JTextField();

        painelCentral.add(new JLabel(" Nome:"));
        painelCentral.add(txtNome);

        painelCentral.add(new JLabel(" Salário:"));
        painelCentral.add(txtNome);

        painelCentral.add(new JLabel(" Cargo"));
        painelCentral.add(txtCargo);

        //3- PAINEL INFERIOOR
        JPanel painelInferior = new JPanel(new GridLayout(1, 2, 2 , 0));

        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");

        painelInferior.add(btnAnterior);
        painelInferior.add(btnProximo);

        //ADICIONANDO PAINÉIS AO FRAME PRINCIPAL
        add(painelBuscaCompleto, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormTrabalhoPratico form = new FormTrabalhoPratico();
            form.setVisible(true);
        });
    }
}