package android.mobile.app.util;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class notificacoes extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    List<RowItem> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);
           DatabaseHandler db = new DatabaseHandler(this);
        ListView notificacao = (ListView) findViewById(R.id.notificao);

        List<BDnoti> noti = db.getAllNotification();
        rowItems = new ArrayList<RowItem>();

        for (BDnoti cn : noti) {
            String log = "Id: "+cn.getID()+" ,Descricao: " + cn.getDesc() + " ,Grupo: " + cn.get_grupo();
            RowItem item = new RowItem(cn.getID(), cn.get_grupo(), cn.getDesc());


            rowItems.add(item);

        }

       /* for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }*/

        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        notificacao.setAdapter(adapter);
        notificacao.setOnItemClickListener(this);

           // This is the array adapter, it takes the context of the activity as a
           // first parameter, the type of list view as a second parameter and your
           // array as a third parameter.
          /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                   this,
                   android.R.layout.simple_list_item_1,
                   your_array_list );

           notificacao.setAdapter(arrayAdapter);

           notificacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();

                Intent myIntent = new Intent(notificacoes.this, notificacao.class);

                notificacoes.this.startActivity(myIntent);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notificacoes, menu);

       // final Intent mostraConfig = new Intent(notificacoes.this, config.class);

        return true;
    }

    public boolean verificaUFG(String grupo) {
        Switch UFG = (Switch) findViewById(R.id.switchUFG);

        if (UFG.isChecked()){
            return true;
        }else
        {
            if (grupo == "UFG"){
                return false;
            }

        }

        return false;
    }

    public boolean verificaES(String grupo) {
        Switch ES = (Switch) findViewById(R.id.switchES);

        if (ES.isChecked()){
            return true;
        }else
        {
            if (grupo == "UFG"){
                return false;
            }

        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            final Intent mostraConfig = new Intent(notificacoes.this, config.class);

            notificacoes.this.startActivity(mostraConfig);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(10);
        toast.show();
    }

}
