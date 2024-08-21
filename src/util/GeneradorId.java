package util;

public class GeneradorId {
    private static int contadorEnvasado = 1;
    private static int contadorBebida = 1;
    private static int contadorLimpieza = 1;

    public static synchronized String generarIdentificadorEnvasado() {
        return String.format("AB%03d", contadorEnvasado++);
    }

    public static synchronized String generarIdentificadorBebida() {
        return String.format("AC%03d", contadorBebida++);
    }

    public static synchronized String generarIdentificadorLimpieza() {
        return String.format("AZ%03d", contadorLimpieza++);
    }
}
