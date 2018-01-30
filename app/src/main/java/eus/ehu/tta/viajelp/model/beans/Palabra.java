package eus.ehu.tta.viajelp.model.beans;

/**
 * Created by edwin on 3/01/18.
 */

public class Palabra {
    private int idPalabra;

    private String palabraEng;

    private String palabraEsp;

    private int idUsuario;


    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabraEng() {
        return palabraEng;
    }

    public void setPalabraEng(String palabraEng) {
        this.palabraEng = palabraEng;
    }

    public String getPalabraEsp() {
        return palabraEsp;
    }

    public void setPalabraEsp(String palabraEsp) {
        this.palabraEsp = palabraEsp;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
