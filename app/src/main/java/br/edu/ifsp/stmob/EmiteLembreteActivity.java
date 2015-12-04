package br.edu.ifsp.stmob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Simione on 01/12/2015.
 */
public class EmiteLembreteActivity extends Activity {

     Button btnEmiteLembrete;

    @Override
    protected void  onCreate(Bundle estadoInstancia){
        super.onCreate(estadoInstancia);
        setContentView(R.layout.activity_main);

        /*
        * Codigo migrado para o botao emitir lembrete na activity MeuPefil
        btnEmiteLembrete = (Button) findViewById(R.id.btnEmiteLembrete);
        btnEmiteLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   Intent emailIntent = new Intent(Intent.ACTION_SEND);
                  // emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"teste@gmail.com"});
                   emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Semana de Tecnologia IFSP");
                   emailIntent.putExtra(Intent.EXTRA_TEXT,"Não se esqueça o evento ocorrerá entre 19 e 22 de outubro de 2015 no câmpus São João da Boa Vista do Instituto Federal de Educação, Ciência e Tecnologia de São Paulo (IFSP-SBV). O tema deste ano será: \"Luz, Ciência e Vida\". Atividades com horários previstos das 8:30 às 22:00.");
                   emailIntent.setType("message/rfc822");
                   startActivity(emailIntent);
               }catch (ActivityNotFoundException anfe){
                   Toast toast = Toast.makeText(EmiteLembreteActivity.this,"email não encontrado", Toast.LENGTH_LONG);
                    toast.show();
               }
            }
        });

        */
    }

}
