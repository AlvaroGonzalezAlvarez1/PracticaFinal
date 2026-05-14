package Pruebas.Juego;

import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        scanner=new Scanner(System.in);
    }

    public String getAccion() {
        System.out.print("Accion (w/a/s/d): ");
        String accion=scanner.nextLine();
        return accion;
    }

    public boolean hayAcciones() {
        return true;
    }
}

//Solo para Juego por consola, para juegoFX no es necesario