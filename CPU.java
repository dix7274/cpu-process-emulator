package emulador;

public class CPU {
    // mis variables de los registros de la cpu
    private int pc;     
    private int ir;     
    private int acc;    
    private boolean halted;
    private MemoriaRAM ram;
    private String mensajeExplicativo;

    // mis codigos para las instrucciones
    public static final int PARAR_PROGRAMA = 0; 
    public static final int CARGAR_A_CAJA = 1;  
    public static final int GUARDAR_EN_RAM = 2; 
    public static final int SUMAR        = 3;   
    public static final int RESTAR       = 4;   

    public CPU(MemoriaRAM ram) {
        this.ram = ram;
        this.mensajeExplicativo = "la cpu esta lista para arrancar";
        reiniciar(0);
    }

    public void reiniciar(int direccionInicio) {
        this.pc = direccionInicio;
        this.ir = 0;
        this.acc = 0;
        this.halted = false;
        this.mensajeExplicativo = "reinicie el sistema, el pc vuelve a 0";
    }

    public void ejecutarCiclo() {
        if (halted) {
            mensajeExplicativo = "el programa ya paro, presiona R o S";
            return;
        }

        // aqui hago la fase de fetch (leo la instruccion de la ram)
        ir = ram.leer(pc);
        int celdaAfectada = ram.leer(pc + 1);
        pc += 2; // muevo el pc 2 casillas

        // aqui decodifico y ejecuto segun la orden que leyo el ir
        switch (ir) {
            case PARAR_PROGRAMA:
                halted = true;
                mensajeExplicativo = "fin del programa, encontre el halt";
                break;
                
            case CARGAR_A_CAJA:
                acc = ram.leer(celdaAfectada);
                mensajeExplicativo = "cargue el numero de la celda " + celdaAfectada + " al acumulador (valor: " + acc + ")";
                break;
                
            case GUARDAR_EN_RAM:
                ram.escribir(celdaAfectada, acc);
                mensajeExplicativo = "guarde el resultado del acumulador (" + acc + ") en la celda " + celdaAfectada;
                break;
                
            case SUMAR:
                int valorASumar = ram.leer(celdaAfectada);
                acc += valorASumar;
                mensajeExplicativo = "sume el valor de la celda " + celdaAfectada + " (" + valorASumar + "). total: " + acc;
                break;
                
            case RESTAR:
                int valorARestar = ram.leer(celdaAfectada);
                acc -= valorARestar;
                mensajeExplicativo = "reste el valor de la celda " + celdaAfectada + " (" + valorARestar + "). total: " + acc;
                break;
                
            default:
                halted = true;
                mensajeExplicativo = "error, orden desconocida";
                break;
        }
    }

    public int getPc() { return pc; }
    public int getIr() { return ir; }
    public int getAcc() { return acc; }
    public boolean isHalted() { return halted; }
    public String getMensajeExplicativo() { return mensajeExplicativo; }
}