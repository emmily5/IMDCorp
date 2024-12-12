package enums;

public enum Formacao {
    ESPECIALIZACAO(0.25),
    MESTRADO(0.50),
    DOUTORADO(0.75);

    private final double adicional;

    Formacao(double adicional) {
        this.adicional = adicional;
    }

    public double getAdicional() {
        return adicional;
    }
}