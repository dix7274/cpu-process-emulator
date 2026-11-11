package emulador;

public class MemoriaRAM {
    private int[] celdas;

    public MemoriaRAM(int tamano) {
        this.celdas = new int[tamano];
    }

    public int leer(int direccion) {
        if (direccion < 0 || direccion >= celdas.length) {
            throw new IndexOutOfBoundsException("Dirección de RAM fuera de rango: " + direccion);
        }
        return celdas[direccion];
    }

    public void escribir(int direccion, int valor) {
        if (direccion < 0 || direccion >= celdas.length) {
            throw new IndexOutOfBoundsException("Dirección de RAM fuera de rango: " + direccion);
        }
        celdas[direccion] = valor;
    }

    public int getTamano() {
        return celdas.length;
    }
}