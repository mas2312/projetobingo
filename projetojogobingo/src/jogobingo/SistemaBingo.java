package jogobingo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaBingo extends JFrame {
    
	 
	
	private static final long serialVersionUID = 1L;
	private static List<Cliente> clientes = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();
    private static Premio premioRodada = new Premio(20.0);
    private static GestaoFinanceira gestaoFinanceira = new GestaoFinanceira();
    private static JPanel panel;
    private static JLabel label;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sistema de Bingo");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        label = new JLabel("== Sistema de Bingo ==");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(label, constraints);

        JButton cadastrarClienteButton = new JButton("Cadastrar cliente");
        cadastrarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(cadastrarClienteButton, constraints);

        JButton editarClienteButton = new JButton("Editar cliente");
        editarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(editarClienteButton, constraints);

        JButton deletarClienteButton = new JButton("Deletar cliente");
        deletarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarCliente();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(deletarClienteButton, constraints);

        JButton venderCartelaButton = new JButton("Vender cartela");
        venderCartelaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                venderCartela();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(venderCartelaButton, constraints);

        JButton realizarSorteioButton = new JButton("Realizar sorteio");
        realizarSorteioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarSorteio();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(realizarSorteioButton, constraints);

        JButton verSaldoButton = new JButton("Ver saldo");
        verSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verSaldo();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(verSaldoButton, constraints);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void cadastrarCliente() {
        String nome = "";
        String sobrenome = "";
        String telefone = "";

        boolean nomeValido = false;
        while (!nomeValido) {
            nome = JOptionPane.showInputDialog(panel, "Digite o nome do cliente:");
            nomeValido = validarNome(nome);

            if (!nomeValido) {
                JOptionPane.showMessageDialog(panel, "Nome inválido. O nome não pode conter números. Tente novamente.");
            }
        }

        boolean sobrenomeValido = false;
        while (!sobrenomeValido) {
            sobrenome = JOptionPane.showInputDialog(panel, "Digite o sobrenome do cliente:");
            sobrenomeValido = validarNome(sobrenome);

            if (!sobrenomeValido) {
                JOptionPane.showMessageDialog(panel, "Sobrenome inválido. O sobrenome não pode conter números. Tente novamente.");
            }
        }

        boolean telefoneValido = false;
        while (!telefoneValido) {
            telefone = JOptionPane.showInputDialog(panel, "Digite o telefone do cliente:");
            telefoneValido = validarTelefone(telefone);

            if (!telefoneValido) {
                JOptionPane.showMessageDialog(panel, "Telefone inválido. O telefone deve conter apenas números. Tente novamente.");
            }
        }

        Cliente cliente = new Cliente(nome, sobrenome, telefone);
        clientes.add(cliente);

        JOptionPane.showMessageDialog(panel, "Cliente cadastrado com sucesso.");
    }

    private static boolean validarNome(String nome) {
        return !nome.matches(".*\\d.*");
    }

    private static boolean validarTelefone(String telefone) {
        return telefone.matches("[0-9]+");
    }

    private static void editarCliente() {
        String nome = JOptionPane.showInputDialog(panel, "Digite o nome do cliente que deseja editar:");

        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            String novoNome = JOptionPane.showInputDialog(panel, "Digite o novo nome do cliente:");
            clienteEncontrado.setNome(novoNome);
            String novoSobrenome = JOptionPane.showInputDialog(panel, "Digite o novo sobrenome do cliente:");
            clienteEncontrado.setSobrenome(novoSobrenome);
            String novoTelefone = JOptionPane.showInputDialog(panel, "Digite o novo telefone do cliente:");
            clienteEncontrado.setTelefone(novoTelefone);
            JOptionPane.showMessageDialog(panel, "Cliente editado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(panel, "Cliente não encontrado.");
        }
    }

    private static void deletarCliente() {
        String nome = JOptionPane.showInputDialog(panel, "Digite o nome do cliente que deseja deletar:");

        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            clientes.remove(clienteEncontrado);
            JOptionPane.showMessageDialog(panel, "Cliente deletado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(panel, "Cliente não encontrado.");
        }
    }

    private static void venderCartela() {
        String nome = JOptionPane.showInputDialog(panel, "Digite o nome do cliente que deseja vender a cartela:");

        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            String numeroCartelaStr = JOptionPane.showInputDialog(panel, "Digite o número da cartela (de 1 a 100):");
            int numeroCartela = Integer.parseInt(numeroCartelaStr);
            String valorCartelaStr = JOptionPane.showInputDialog(panel, "Digite o valor da cartela:");
            double valorCartela = Double.parseDouble(valorCartelaStr);

            Venda venda = new Venda(numeroCartela, valorCartela);
            vendas.add(venda);
            gestaoFinanceira.adicionarVenda(valorCartela);

            JOptionPane.showMessageDialog(panel, "Cartela vendida com sucesso.");
        } else {
            JOptionPane.showMessageDialog(panel, "Cliente não encontrado.");
        }
    }

    private static void realizarSorteio() {
        Random random = new Random();
        int numeroSorteado = random.nextInt(100) + 1;
        JOptionPane.showMessageDialog(panel, "Número sorteado: " + numeroSorteado);

        for (Venda venda : vendas) {
            if (venda.getNumeroCartela() == numeroSorteado) {
                double premioCliente = premioRodada.getValorRodada() / countNumerosSorteados();
                JOptionPane.showMessageDialog(panel, "Parabéns! O cliente da cartela " + venda.getNumeroCartela() + " ganhou R$" + premioCliente + ".");
                gestaoFinanceira.removerPremio(premioCliente);
            }
        }
    }

    private static void verSaldo() {
        JOptionPane.showMessageDialog(panel, "Saldo atual: R$" + gestaoFinanceira.getSaldo());
    }

    private static int countNumerosSorteados() {
        Map<Integer, Integer> numerosSorteados = new HashMap<>();

        for (Venda venda : vendas) {
            int numeroCartela = venda.getNumeroCartela();
            numerosSorteados.put(numeroCartela, numerosSorteados.getOrDefault(numeroCartela, 0) + 1);
        }

        return numerosSorteados.size();
    }
}
