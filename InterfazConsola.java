package emulador;

import java.util.Scanner;

public class InterfazConsola {
    private CPU cpu;
    private MemoriaRAM ram;
    private Scanner scanner;

    public InterfazConsola(CPU cpu, MemoriaRAM ram) {
        this.cpu = cpu;
        this.ram = ram;
        this.scanner = new Scanner(System.in);
    }

    // metodo para limpiar la consola con espacios
    public void limpiarPantalla() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public void mostrarPanel() {
        limpiarPantalla();
        
        System.out.println("======================================================================");
        System.out.println("               CPU PROCESS EMULATOR - PANEL DE CONTROL                ");
        System.out.println("======================================================================");
        System.out.println("  lo que esta pasando ahorita mismo:");
        System.out.println("   > " + cpu.getMensajeExplicativo());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("  registros de la cpu:");
        System.out.println("    - PC (apunta a la celda)    : " + cpu.getPc());
        System.out.println("    - IR (orden actual)         : " + obtenerNombreOrden(cpu.getIr()));
        System.out.println("    - ACC (acumulador / caja)   : " + cpu.getAcc());
        System.out.println("    - estado                    : " + (cpu.isHalted() ? "terminado" : "ejecutando"));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("  memoria ram:");
        
        for (int i = 0; i <= 12; i++) {
            String senalizacion = (i == cpu.getPc()) ? " <--- [aqui esta leyendo el procesador]" : "";
            System.out.printf("    celda [%02d] = %-5d%s\n", i, ram.leer(i), senalizacion);
        }
        System.out.println("======================================================================");
        System.out.println("  controles: [enter] siguiente paso | [r] reiniciar | [s] salir");
        System.out.print("  que hacemos?: ");
    }

    private String obtenerNombreOrden(int codigo) {
        switch (codigo) {
            case CPU.PARAR_PROGRAMA: return "PARAR";
            case CPU.CARGAR_A_CAJA:  return "CARGAR";
            case CPU.GUARDAR_EN_RAM: return "GUARDAR";
            case CPU.SUMAR:          return "SUMAR";
            case CPU.RESTAR:         return "RESTAR";
            default: return "DESCONOCIDA";
        }
    }

    public void iniciar() {
        String tecla = "";
        while (!tecla.equalsIgnoreCase("S")) {
            mostrarPanel();
            tecla = scanner.nextLine();

            if (tecla.equalsIgnoreCase("R")) {
                cpu.reiniciar(0);
            } else if (tecla.equalsIgnoreCase("S")) {
                break;
            } else {
                if (!cpu.isHalted()) {
                    cpu.ejecutarCiclo();
                }
            }
        }
        System.out.println("\nsaliendo del emulador...");
    }
}