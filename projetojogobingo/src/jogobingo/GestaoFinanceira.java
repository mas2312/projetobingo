package jogobingo;

class GestaoFinanceira {
    private double saldo;

    public void adicionarVenda(double valorVenda) {
        saldo += valorVenda;
    }

    public void removerPremio(double valorPremio) {
        saldo -= valorPremio;
    }

    public double getSaldo() {
        return saldo;
    }
}