package android.mobile.app.util;

/**
 * Created by Henrique on 24/06/14.
 */
public class BDnoti {

    //variáveis
    int _id;
    String _desc;
    String _grupo;


    public BDnoti(){

    }
    // construtor
    public BDnoti(int id, String desc, String _grupo){
        this._id = id;
        this._desc = desc;
        this._grupo = _grupo;
    }

    // constructor
    public BDnoti(String desc, String _grupo){
        this._desc = desc;
        this._grupo = _grupo;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getDesc(){
        return this._desc;
    }

    public void setDesc(String desc){
        this._desc = desc;
    }

    public String get_grupo() {
        return _grupo;
    }

    public void set_grupo(String _grupo) {
        this._grupo = _grupo;
    }
}

