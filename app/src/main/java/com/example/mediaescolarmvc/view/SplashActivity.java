package com.example.mediaescolarmvc.view;

import com.example.mediaescolarmvc.R;//Caso tenha problema com a class R, tem que importar ela com o package.
import com.example.mediaescolarmvc.controller.MediaEscolarController;
import com.example.mediaescolarmvc.datasource.DataSource;
import com.example.mediaescolarmvc.model.MediaEscolar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 5000;//tempo splash

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        apresentarTelaSplash();
    }
    private void apresentarTelaSplashs() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MediaEscolarController mediaEscolarController =
                        new MediaEscolarController(getBaseContext());

                List<MediaEscolar> objetos = mediaEscolarController.listar();

                for (MediaEscolar obj: objetos) {

                    Log.i("CRUD LISTAR", "ID: "+obj.getId()+" - Matéria: "+obj.getMateria()+" - Situação: "+obj.getSituacao());

                }


                Intent telaPrincipal
                        = new Intent(SplashActivity.this,
                        MainActivity.class);

                startActivity(telaPrincipal);

                finish();

            }
        }, SPLASH_TIME_OUT);


    }
    private void apresentarTelaSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                //media Escolar utilizado para realização dos crus (Insert,update,delete)
                MediaEscolar obj = new MediaEscolar();
                obj.setMateria("Matematica");
                obj.setBimestre("1º - Bimestre");
                obj.setSituacao("Aprovado");
                obj.setNotaProva(10);
                obj.setNotaMateria(10);
                obj.setMediaFinal(10);

                //Salvar dados
               //MediaEscolarController mediaEscolarController = new MediaEscolarController(getBaseContext());
               //for(int i=0;i<10;i++)
               //{
                //   mediaEscolarController.salvar(obj);//método salvar
               //}

                //Deletar Dados
                // obj.setId(3);
                // MediaEscolarController mediaEscolarController = new MediaEscolarController(getBaseContext());
                // mediaEscolarController.deletar(obj);

                //Alterar Dados
                //MediaEscolarController mediaEscolarController = new MediaEscolarController(getBaseContext());
                //obj.setId(4);
                //mediaEscolarController.alterar(obj);

                //Método Listar
                //MediaEscolarController mediaEscolarController = new MediaEscolarController(getBaseContext());
               // List<MediaEscolar> objetos = mediaEscolarController.listar();
                //for (MediaEscolar objs : objetos)
                //{
                //    Log.i("Crud Listar", "ID:" + objs.getId() + "Materia:" + objs.getMateria()+" Situação:"+objs.getSituacao());
                //}

                //Método Listar
                Intent telaPrincipal = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
