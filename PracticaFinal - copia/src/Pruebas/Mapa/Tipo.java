package Pruebas.Mapa;

public enum Tipo {
    SUELO,  //s,.
    PARED,  //p,#
    VACIO,  //v,    no se imrime
    PUERTA, //d,D
    INTERACTUABLE,  //i,?   Pensado para cofres, palancas
                    //      Si son NPC son ESTÁTICOS, no se mueven, siempre fijos
    AGUA,    //a,~
}