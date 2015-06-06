package dpassos.com.br.basicview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private long acesso;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String message = getIntent().getStringExtra("message");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Button btOpcao = (Button)findViewById(R.id.activity_main_bt_opcao);
        btOpcao.setOnClickListener(this);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);

        acesso = sp.getLong("acesso", -1l);
        if(acesso == -1){
            acesso = System.currentTimeMillis();
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("acesso", acesso);
            editor.commit();
        }

        lista = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Acesso em : "+acesso, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("lista", lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.sair);

        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });

        builder.setNegativeButton(R.string.cancelar, null);
        builder.show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_main_bt_opcao){
            RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);

            int opcao = rg.getCheckedRadioButtonId() == R.id.radioButton2 ? 1 : 2;
            Intent it = new Intent(this, CalculadoraActivity.class);
            it.putExtra("opcao",opcao);
            startActivity(it);
        }
    }
}