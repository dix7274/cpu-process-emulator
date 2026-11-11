# CPU Process Emulator

Hola, este es mi proyecto de un emulador de procesador hecho en Java. Lo armé para simular cómo funciona la arquitectura básica de Von Neumann, con su memoria RAM, sus registros internos y un ciclo de ejecución paso a paso que se puede ver desde la consola.

## ¿Qué incluye el proyecto?

El código está organizado dentro del paquete `emulador`[cite: 1] y se divide en estas clases:

- **Main.java:** Es el punto de partida del programa. Aquí configuro la memoria inicial y cargo el código de prueba[cite: 1].
- **MemoriaRAM.java:** Simula la memoria principal con un arreglo de 256 posiciones, con métodos para leer y escribir datos de forma segura[cite: 1].
- **CPU.java:** Es el núcleo del emulador. Maneja los registros (`PC`, `IR`, `ACC`) y se encarga de ejecutar el ciclo de buscar, decodificar y ejecutar cada instrucción (*Fetch-Decode-Execute*)[cite: 1].
- **InterfazConsola.java:** Controla la interacción visual en la terminal para que puedas ver el estado del procesador en tiempo real[cite: 1].

## Instrucciones para ejecutarlo

Si quieres correrlo en tu computador (yo uso Visual Studio Code), solo sigue estos pasos:

1. Asegúrate de tener instalado Java (JDK) en tu equipo.
2. Clona o descarga esta carpeta en tu computador.
3. Abre la carpeta del proyecto en tu entorno de desarrollo.
4. Compila y ejecuta el archivo `Main.java`[cite: 1].
5. En la consola podrás presionar **Enter** para avanzar ciclo por ciclo, la tecla **R** para reiniciar o **S** para salir.
