package com.aula.atividades.datatotable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Add_Event extends Activity {

    private Calendar cal;
    private int day;
    private int month;
    private int year, hour,minute;

    private CheckBox dia_inteiro,ck_repeticao;
    private Date TDB_data_ini;
    private String hoje;
    private Spinner repeticao_sp;


    private EditText data_evento, hora_inicio,hora_fim,local_evento,participantes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);

        final SQLController sqlcon;
        final Button BTnovoTipo = (Button) findViewById(R.id.BT_add_add_Tipo);
        data_evento = (EditText)findViewById(R.id.ET_add_Date);
        hora_inicio = (EditText)findViewById(R.id.ET_add_Time_Ini);
        hora_fim = (EditText)findViewById(R.id.ET_add_Time_Fim);
        dia_inteiro=(CheckBox)findViewById(R.id.CKB_add_Dia_interio);
        local_evento=(EditText)findViewById(R.id.ET_add_Local);
        ck_repeticao=(CheckBox)findViewById(R.id.CKB_Repetir);
        final Button TDBGRavar =(Button)findViewById(R.id.BT_add_evento);
        final Button BT_calendario = (Button)findViewById(R.id.BT_add_DatePicker);
        final Spinner tipos_e = (Spinner)findViewById(R.id.SP_add_tipo);
        final TextView TV_tipo=(TextView)findViewById(R.id.TV_add_tipo);

        final List<String> categories = new ArrayList<String>();

        final SupportMethods supportMethods = new SupportMethods();

        sqlcon = new SQLController(this);


        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);


/*
        if(month<10 && day<10) {
            data_evento.setText("0" + day + "/0" + month + "/" + year);
        }else if(day<10){
            data_evento.setText("0"+day + "/" + month+"/"+year);
        }else if(month<10){
            data_evento.setText(day + "/0" + month+"/"+year);
        }else{
            data_evento.setText(day + "/" + month+"/"+year);
        }*/
        data_evento.setText(supportMethods.FormataDataVW(day + "/" + (month + 1) + "/" + year).toString());
        hoje=day + "-" + (month+1) + "-" + year;
        //----------------------Abrir DataPicker quando clickar------------------------------
        data_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });

        //------------------Abrir o TimePicker Quando Clickar na hora Inicio

        hora_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog(hora_inicio);
            }
        });

        //------------------Abrir o TimePicker Quando Clickar na Hora fim

        hora_fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog(hora_fim);
            }
        });

        BT_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });
        //---------------------Dia inteiro: desabilita os ET ----------//

        dia_inteiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dia_inteiro.isChecked()) {
                    hora_inicio.setEnabled(false);
                    hora_inicio.setClickable(false);
                    hora_fim.setEnabled(false);
                    hora_fim.setClickable(false);
                } else {
                    hora_inicio.setEnabled(true);
                    hora_inicio.setClickable(true);
                    hora_fim.setEnabled(true);
                    hora_fim.setClickable(true);
                }
            }
        });

//-----------------------Inicializa limpo---------------------------------------//



        AtualizaSpRepeticao();

        repeticao_sp = (Spinner)findViewById(R.id.SP_tipo_Repeticao);
        ck_repeticao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ck_repeticao.isChecked()){
                    repeticao_sp.setEnabled(false);
                    repeticao_sp.setClickable(false);
                    repeticao_sp.setFocusable(false);
                    repeticao_sp.setVisibility(View.INVISIBLE);
                    View positiveButton = findViewById(R.id.TV_add_tipo);
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams)positiveButton.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.BELOW, R.id.CKB_Repetir);
                    positiveButton.setLayoutParams(layoutParams);

                }else {
                    View positiveButton = findViewById(R.id.TV_add_tipo);
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams)positiveButton.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.BELOW, R.id.SP_tipo_Repeticao);
                    positiveButton.setLayoutParams(layoutParams);
                    repeticao_sp.setEnabled(true);
                    repeticao_sp.setClickable(true);
                    repeticao_sp.setFocusable(true);
                    repeticao_sp.setVisibility(View.VISIBLE);
                }
            }
        });

        AtualizaSpTipos();   //---------------------------Carrega Spinner-------------------------------//

//-----------------------Criar novo tipo de evento ---------------------------------------//
        BTnovoTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //------------------Novo intent para cadastrar Tipo
                Intent novotipo = new Intent(Add_Event.this, add_Tipo.class);
                startActivity(novotipo);

            }
        });

        TDBGRavar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantes=(EditText)findViewById(R.id.ET_add_Participantes);
                local_evento=(EditText)findViewById(R.id.ET_add_Local);
                sqlcon.open();
                sqlcon.insertEvento(hoje,local_evento.getText().toString(),participantes.getText().toString());
                sqlcon.close();

            }
        });
    }//fim onCreate

    public void AtualizaSpTipos(){
        final SQLController sqlcon;
        sqlcon = new SQLController(this);
        final Spinner tipos_e = (Spinner)findViewById(R.id.SP_add_tipo);
        final List<String> categories = new ArrayList<String>();
        sqlcon.open();
        Cursor c = sqlcon.VerTipos();
        categories.clear();
        if(c.moveToFirst()){
            do{
                categories.add(c.getString(1));
            }while (c.moveToNext());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        tipos_e.setAdapter(dataAdapter);
    }

    public void AtualizaSpRepeticao(){
        final SQLController sqlcon;
        sqlcon = new SQLController(this);
        repeticao_sp = (Spinner)findViewById(R.id.SP_tipo_Repeticao);
        final List<String> categories = new ArrayList<String>();
        sqlcon.open();
        Cursor c = sqlcon.VerRepeticoes();
        categories.clear();
        if(c.moveToFirst()){
            do{
                categories.add(c.getString(1));
            }while (c.moveToNext());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        repeticao_sp.setAdapter(dataAdapter);
    }

    public void DateDialog(){

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {if(monthOfYear<10 && dayOfMonth<10) {
                data_evento.setText("0" + dayOfMonth + "/0" + monthOfYear + "/" + year);
            }else if(dayOfMonth<10){
                data_evento.setText("0"+dayOfMonth + "/" + monthOfYear+"/"+year);
            }else if(monthOfYear<10){
                data_evento.setText(dayOfMonth + "/0" + monthOfYear+"/"+year);
            }else{
                data_evento.setText(dayOfMonth + "/" + monthOfYear+"/"+year);
            }

            }
        };

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

    public void TimeDialog(final EditText hora){

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                if(hourOfDay<10 && minute<10) {
                    hora.setText("0"+hourOfDay + ":0" + minute);
                }else if(hourOfDay<10){
                    hora.setText("0"+hourOfDay + ":" + minute);
                }else if(minute<10){
                    hora.setText( hourOfDay + ":0" + minute);
                }else{
                hora.setText(hourOfDay+":"+minute);
                }
                view.setIs24HourView(true);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,listener,hour+12,minute,true);
        timePickerDialog.show();

    }
    @Override
    public void onResume(){
        AtualizaSpRepeticao();
        AtualizaSpTipos();
        super.onResume();
    }
}
