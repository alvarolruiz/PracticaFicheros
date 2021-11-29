package Entities;

public class RegistroIndex {
    public String dni; //9*2
    public int pos; //4
    public static final int TAMANO_REGISTRO = (9*2) + 4;

    public RegistroIndex(String dni, int pos) {
        this.dni = dni;
        this.pos = pos;
    }

    public String getDni() {
        return dni;
    }

    public int getPos() {
        return pos;
    }
}
