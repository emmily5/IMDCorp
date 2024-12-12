package enums;

public enum Nivel {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8);

    private final int valor;

    Nivel(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}