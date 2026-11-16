//João Victor dos Santos, Luis Tiago Ferreira

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ContadorRunnable extends JPanel implements Runnable {

    private int maximo;
    private int prioridade;
    private DefaultListModel<Integer> listModel;
    private JList<Integer> lista;
    private JLabel lblFinal;

    public ContadorRunnable() {
        setLayout(new BorderLayout(5, 10));

        listModel = new DefaultListModel<>();
        lista = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(100, 160));

        lblFinal = new JLabel("Final", JLabel.CENTER);
        lblFinal.setVisible(false);

        add(scrollPane, BorderLayout.CENTER);
        add(lblFinal, BorderLayout.SOUTH);
    }

    public void configurar(int maximo, int prioridade) {
        this.maximo = maximo;
        this.prioridade = prioridade;
        limpar();
    }

    public void limpar() {
        listModel.clear();
        lblFinal.setVisible(false);
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(prioridade);

        for (int i = 0; i < maximo; i++) {
            final int valor = i;
            SwingUtilities.invokeLater(() -> {
                listModel.addElement(valor);
                int lastIndex = listModel.getSize() - 1;
                if (lastIndex >= 0) {
                    lista.ensureIndexIsVisible(lastIndex);
                }
            });

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        SwingUtilities.invokeLater(() -> lblFinal.setVisible(true));
    }
}