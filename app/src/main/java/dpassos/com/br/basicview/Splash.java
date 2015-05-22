package dpassos.com.br.basicview;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Esta Activity mostra como enviar um dado para uma proxima tela.
 * Na parte de Layout usamos um frameLayout com uma progress bar
 * ao centro.
 */
public class Splash extends ActionBarActivity implements Runnable{
    private static int MAX_REPEAT = 4;
    Handler h;
    private int executionTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        executionTime = getIntent().getIntExtra("executionTime", 1);

        TextView txt = (TextView)findViewById(R.id.txtExecutionTime);
        txt.setText(Integer.toString(executionTime));

        h = new Handler();
        h.postDelayed(this, 5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
    protected void onDestroy() {
        h.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * Vamos deixar a splash em loop por algumas execucoes e depois abir uma nova Activity
     * informado em uma mensagem quantas veses foi repetida.
     *
     */
    @Override
    public void run() {
        if(executionTime >= MAX_REPEAT){
            Intent it = new Intent(this, MainActivity.class);
            it.putExtra("message", this.getClass().getSimpleName()+" foi repetida "+executionTime+" vezes");
            startActivity(it);
            finish();
        }else{
            Intent it = new Intent(this, Splash.class);
            it.putExtra("executionTime", executionTime + 1);
            startActivity(it);
            finish();
        }
    }
}
