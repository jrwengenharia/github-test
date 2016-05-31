package com.aula.atividades.datatotable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Evento_detalhes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhes);

        Intent dados = getIntent();
        SQLController sqlcon;
        sqlcon = new SQLController(this);
        final String id_evento = dados.getStringExtra("id_evento");
        final TextView Tv_data = (TextView)findViewById(R.id.Tv_detalhes_data);
        final TextView Tv_local = (TextView)findViewById(R.id.TV_local);

        sqlcon.open();
        Cursor c = sqlcon.getEvento(id_evento);
        Tv_data.setText(c.getString(1));
        Tv_local.setText(c.getString(2));

    }
}
