package eus.ehu.tta.viajelp.model.beans;

/**
 * Created by edwin on 3/01/18.
 */

public class Frase {
    private int idFrases;

    private String audio;

    private String fraseEng;

    private String fraseEsp;

    private String situacion;

    private String tipo;//F o ...

    private int usuarioAsk;

    private int usuarioAns;

    private String ansUser;

    private String askUser;



    public int getIdFrases() {
        return idFrases;
    }

    public String getAnsUser() {
        return ansUser;
    }

    public void setAnsUser(String ansUser) {
        this.ansUser = ansUser;
    }

    public String getAskUser() {
        return askUser;
    }

    public void setAskUser(String askUser) {
        this.askUser = askUser;
    }

    public void setIdFrases(int idFrases) {
        this.idFrases = idFrases;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getFraseEng() {
        return fraseEng;
    }

    public void setFraseEng(String fraseEng) {
        this.fraseEng = fraseEng;
    }

    public String getFraseEsp() {
        return fraseEsp;
    }

    public void setFraseEsp(String fraseEsp) {
        this.fraseEsp = fraseEsp;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUsuarioAsk() {
        return usuarioAsk;
    }

    public void setUsuarioAsk(int usuarioAsk) {
        this.usuarioAsk = usuarioAsk;
    }

    public int getUsuarioAns() {
        return usuarioAns;
    }

    public void setUsuarioAns(int usuarioAns) {
        this.usuarioAns = usuarioAns;
    }
}
