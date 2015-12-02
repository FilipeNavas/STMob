package br.edu.ifsp.stmob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.Adapter.AvisoListAdapter;
import br.edu.ifsp.stmob.dao.AvisoExtraordinarioDAO;
import br.edu.ifsp.stmob.modelo.AvisoExtraordinario;

/**
 * Created by Fernando on 02/12/2015.
 */

public class ListaAvisoExtraordinarioActivity extends AppCompatActivity {

    private AvisoExtraordinario aviso;
    private List<AvisoExtraordinario> avisos;
    private AvisoExtraordinarioDAO avisoDAO;
    private ListView listaAvisos;

    @Override
    public void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lista_aviso_extraordinario);

        listaAvisos = (ListView) findViewById(R.id.listaAvisos);
        listaAvisos.setOnItemLongClickListener(excluirAviso);

        avisos = new ArrayList<AvisoExtraordinario>();
        avisoDAO = new AvisoExtraordinarioDAO(getApplicationContext());

        preencherLista();
    }

    public void preencherLista(){

        avisos = avisoDAO.listAll();
        if (avisos != null){
            if (avisos.size() > 0){
                AvisoListAdapter avisoAdapter = new AvisoListAdapter(getApplicationContext(), avisos);
                listaAvisos.setAdapter(avisoAdapter);
            }
        }

    }

    private void excluirAviso(final AvisoExtraordinario a) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Você está excluindo o aviso para " + a.getAviTitulo() + " deseja continuar?")
                .setCancelable(false)
                .setPositiveButton(getString(R.string.sim),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (avisoDAO.deletar(a.getAviCod())) {
                                    preencherLista();
                                    exibirMensagem("Aviso excluido!");
                                } else {
                                    exibirMensagem("Falha ao excluir aviso.");
                                }

                            }
                        })
                .setNegativeButton(getString(R.string.nao),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        builder.create();
        builder.show();

    }

    private AdapterView.OnItemLongClickListener excluirAviso = new AdapterView.OnItemLongClickListener() {

        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
            excluirAviso(avisos.get(pos));
            return true;
        }

    };

    private void exibirMensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
