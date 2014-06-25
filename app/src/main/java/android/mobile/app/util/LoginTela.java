package android.mobile.app.util;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class LoginTela extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tela);

        DatabaseHandler db = new DatabaseHandler(this);
        Button btnEntrar = (Button) findViewById(R.id.Entrar);

        Log.d("Insert: ", "Inserting ..");
        db.addNotificacao(new BDnoti("Aula de Mobile", "Engenharia de Software"));
        db.addNotificacao(new BDnoti("Prova de Web", "Engenharia de Software"));
        db.addNotificacao(new BDnoti("Ponto Facultativo", "UFG"));

        // Reading all contacts
        Log.d("Lendo: ", "Lendo Notificações..");
        List<BDnoti> contacts = db.getAllNotification();

        for (BDnoti cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Descricao: " + cn.getDesc() + " ,Grupo: " + cn.get_grupo();
            // Writing Contacts to log
            Log.d("Descricao: ", log);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_tela, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void MyClickMethod(View v)
    {
        Intent myIntent = new Intent(LoginTela.this, notificacoes.class);

        LoginTela.this.startActivity(myIntent);
    }

}
