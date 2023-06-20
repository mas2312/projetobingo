package jogobingo;

class Venda {
    private int numeroCartela;
    private double valorCartela;

    public Venda(int numeroCartela, double valorCartela) {
        this.numeroCartela = numeroCartela;
        this.valorCartela = valorCartela;
    }

    public int getNumeroCartela() {
        return numeroCartela;
    }

    public void setNumeroCartela(int numeroCartela) {
        this.numeroCartela = numeroCartela;
    }

    public double getValorCartela() {
        return valorCartela;
    }

    public void setValorCartela(double valorCartela) {
        this.valorCartela = valorCartela;
    }
}

