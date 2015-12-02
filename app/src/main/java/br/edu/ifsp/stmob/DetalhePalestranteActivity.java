package br.edu.ifsp.stmob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Palestrante;

public class DetalhePalestranteActivity extends AppCompatActivity {

    private TextView Apresentacao; // variaveis com leitra maiuscula por conta do banco
    private TextView detalhe;
    private Button Latte;
    private PalestranteDAO plsDao;
    private Palestrante palestrante;
    int cod=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_palestrante);
        Apresentacao  = (TextView) findViewById(R.id.idApresentacao);
        detalhe = (TextView) findViewById(R.id.idDetalhe);
        Latte = (Button) findViewById(R.id.idLatte);
        plsDao = new PalestranteDAO(getApplicationContext());
        Latte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse(palestrante.getPltLattes()));
                startActivity(browserIntent);



            }
        });

        //String html = "< a href = 'http://www.google.com.br'>";
        //Latte = (TextView) findViewById(R.id.);

    }

    protected void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            if (!extras.getString("pls").isEmpty()) {
                cod = Integer.parseInt(extras.getString("pls"));
            }
        }

        palestrante = plsDao.getByID(cod);

        Apresentacao.setText(palestrante.getPltNome());
        detalhe.setText(palestrante.getPltApresentacao());


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
}
