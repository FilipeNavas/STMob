package br.edu.ifsp.stmob;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Simione on 02/12/2015.
 */
public class EnviaFeedbackActivity extends Activity {


    Button btnFeedback;

    @Override
    protected void  onCreate(Bundle estadoInstancia){
        super.onCreate(estadoInstancia);
        setContentView(R.layout.activity_main);

        btnFeedback = (Button) findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                     emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"ifsp.feedback@gmail.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback Semana de Tecnologia IFSP");
                   // emailIntent.putExtra(Intent.EXTRA_TEXT,"STMob");
                    emailIntent.setType("message/rfc822");
                    startActivity(emailIntent);
                }catch (ActivityNotFoundException anfe){
                    Toast toast = Toast.makeText(EnviaFeedbackActivity.this,"email não encontrado", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


    }

}


