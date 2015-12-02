package br.edu.ifsp.stmob;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Simione on 01/12/2015.
 */
public class EmiteLembreteActivity extends Activity {

     Button btnEmiteLembrete;

    @Override
    protected void  onCreate(Bundle estadoInstancia){
        super.onCreate(estadoInstancia);
        setContentView(R.layout.activity_main);

        btnEmiteLembrete = (Button) findViewById(R.id.btnEmiteLembrete);
        btnEmiteLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   Intent emailIntent = new Intent(Intent.ACTION_SEND);
                  // emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"teste@gmail.com"});
                   emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Semana de Tecnologia IFSP");
                   emailIntent.putExtra(Intent.EXTRA_TEXT,"Não se esqueça Semana de Tecnologia IFSP São joão da Boa Vista na semana do dia 19/10 ao dia 23/10");
                   emailIntent.setType("message/rfc822");
                   startActivity(emailIntent);
               }catch (ActivityNotFoundException anfe){
                   Toast toast = Toast.makeText(EmiteLembreteActivity.this,"email não encontrado", Toast.LENGTH_LONG);
                    toast.show();
               }
            }
        });


    }

}
