package Pruebas.Mapa;

import Estructuras.GsonUtil;
import Estructuras.IndexedList;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Celda.Tipo;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Mapa.Puerta.PuertaData;

public class MapaLoader {
    private static final char PARED = '#';
    private static final char SUELO = '_';
    private static final char PUERTA = 'P';
    private static final char INTERACTUABLE = '?';
    private static final char AGUA = '~';
    private static final char VACIO = '0';


    public static IndexedList<Habitacion> cargarHabitaciones(){
        HabitacionData[] datos=GsonUtil.cargarArray("src/DATOS/habitaciones.json", HabitacionData[].class);
        IndexedList<Habitacion>habitaciones=new IndexedList<>();
        if(datos!=null){
            for(HabitacionData d:datos){
                habitaciones.append(crearDesdeTexto(d.mapa));
            }
        }
        return habitaciones;
    }

    public static IndexedList<Puerta> cargarPuertas(){
        PuertaData[] datos=GsonUtil.cargarArray("src/DATOS/puertas.json",PuertaData[].class);
        IndexedList<Puerta>puertas=new IndexedList<>();
        if(datos!=null){
            for(PuertaData d:datos){
                puertas.append(new Puerta(d.habitacionOrigen,d.habitacionDestino,d.xOrigen,d.yOrigen,d.xDestino,d.yDestino));
            }
        }
        return puertas;
    }

    private static Habitacion crearDesdeTexto(String[] mapa){
        Celda[][] celdas=new Celda[mapa.length][];
        for(int y=0; y<mapa.length; y++){
            celdas[y]=new Celda[mapa[y].length()];
            for(int x=0;x<mapa[y].length();x++){
                char simbolo=mapa[y].charAt(x);
                celdas[y][x]=switch(simbolo){
                            case '#'->new Celda(Tipo.PARED);
                            case '_'->new Celda(Tipo.SUELO);
                            case 'P'->new Celda(Tipo.PUERTA);
                            case '?'->new Celda(Tipo.INTERACTUABLE);
                            case '~'->new Celda(Tipo.AGUA);
                            case '0'->new Celda(Tipo.VACIO);
                            default -> throw new IllegalArgumentException("Símbolo desconocido: "+simbolo);
                        };
            }
        }
        return new Habitacion(celdas);
    }
}
