package br.edu.ifsp.stmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;

public class GradeAtividadesCompletaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_atividades_completa);
        ListView lvAtividades = (ListView) findViewById(R.id.listView2);
        lvAtividades.setOnItemClickListener(selecionarAtividade);



    }

    private AdapterView.OnItemClickListener selecionarAtividade = new AdapterView.OnItemClickListener() {
        AtividadeDAO atvDao = new AtividadeDAO(getApplicationContext());
        List <Atividade> atividades = atvDao.listAll();

        public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
            Intent it = new Intent(getApplicationContext(), DetalheAtividadeActivity.class);

            it.putExtra("atvCod", Integer.toString(atividades.get(pos).getAtvCod()));

            startActivity(it);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grade_atividades_completa, menu);
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
