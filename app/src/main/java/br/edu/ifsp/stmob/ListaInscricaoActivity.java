package br.edu.ifsp.stmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ifsp.stmob.Adapter.InscricaoListAdapter;
import br.edu.ifsp.stmob.dao.InscricaoDAO;
import br.edu.ifsp.stmob.modelo.GerenciadorSessao;
import br.edu.ifsp.stmob.modelo.Inscricao;

/**
 * Created by Fernando on 02/12/2015.
 */

public class ListaInscricaoActivity extends AppCompatActivity {

    private Inscricao inscricao;
    private List<Inscricao> inscricoes;
    private InscricaoDAO inscricaoDAO;
    private ListView listaInscricoes;

    //Sessao
    private GerenciadorSessao sessao;


    @Override
    public void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lista_inscricao);

        listaInscricoes = (ListView) findViewById(R.id.listaInscricoes);

        inscricoes = new ArrayList<Inscricao>();
        inscricaoDAO = new InscricaoDAO(getApplicationContext());

        preencherLista();
    }

    public void preencherLista(){

        //Pega a sessao
        sessao = new GerenciadorSessao(getApplicationContext());

        //pega os dados da sessao
        HashMap<String, String> user = sessao.pegarDadosUsuario();

        //pega o codigo do usuario da sessao
        int codigoUsuario = Integer.parseInt(user.get(GerenciadorSessao.CHAVE_CODIGO));

        inscricoes = inscricaoDAO.listaInscricoesUsuario(codigoUsuario);
        if (inscricoes != null){
            if (inscricoes.size() > 0){
                InscricaoListAdapter inscricaoAdapter = new InscricaoListAdapter(getApplicationContext(), inscricoes);
                listaInscricoes.setAdapter(inscricaoAdapter);
            }
        }

    }

    private void exibirMensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
