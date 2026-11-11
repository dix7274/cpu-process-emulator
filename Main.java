package emulador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // operacion quiere hacer el usuario
        System.out.println("==================================================");
        System.out.println("       CPU PROCESS EMULATOR - MENU PRINCIPAL      ");
        System.out.println("==================================================");
        System.out.println("1. Sumar dos numeros");
        System.out.println("2. Restar dos numeros");
        System.out.print("Elige una opcion (1 o 2): ");
        int opcion = scanner.nextInt();

        System.out.print("Escribe el primer numero: ");
        int numero1 = scanner.nextInt();

        System.out.print("Escribe el segundo numero: ");
        int numero2 = scanner.nextInt();

        // creo la ram con 256 espacios
        MemoriaRAM ram = new MemoriaRAM(256);

        // guardo los numeros en las posiciones 10 y 11 de la ram
        ram.escribir(10, numero1);
        ram.escribir(11, numero2);

        // programo las instrucciones en la ram desde la posicion 0
        ram.escribir(0, CPU.CARGAR_A_CAJA);
        ram.escribir(1, 10);

        // aqui evaluamos si el usuario escogio sumar o restar
        if (opcion == 2) {
            ram.escribir(2, CPU.RESTAR);
        } else {
            ram.escribir(2, CPU.SUMAR);
        }
        ram.escribir(3, 11);

        // guardo el resultado final en la celda 12
        ram.escribir(4, CPU.GUARDAR_EN_RAM);
        ram.escribir(5, 12);

        // orden para detener el programa
        ram.escribir(6, CPU.PARAR_PROGRAMA);

        // inicializo la cpu y la consola interactiva
        CPU cpu = new CPU(ram);
        InterfazConsola interfaz = new InterfazConsola(cpu, ram);
        interfaz.iniciar();
    }
}
