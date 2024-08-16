package com.example.prova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prova.Dao.TarefaDao;
import com.example.prova.Database.AppDatabase;
import com.example.prova.RecyclerView.Item;
import com.example.prova.model.Tarefa;

public class CriarTarefa extends AppCompatActivity implements View.OnClickListener {

    Button btn_salvar_tarefa;
    TarefaDao tarefaDao;
    Tarefa tarefaExistente;
    EditText et_titulo_tarefa, et_descricao_tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);

        btn_salvar_tarefa = findViewById(R.id.btn_salvar_tarefa);

        tarefaDao = initiateDatabase();

        et_titulo_tarefa = findViewById(R.id.et_titulo_tarefa);
        et_descricao_tarefa = findViewById(R.id.et_descricao_tarefa);

        Intent intent = getIntent();
        int tarefaId = intent.getIntExtra("tarefaId", -1);

        if (tarefaId != -1) {
            tarefaExistente = tarefaDao.getById(tarefaId);
            preencherCamposComDadosExistentes();
        }

        btn_salvar_tarefa.setOnClickListener(this);
    }

    @NonNull
    private TarefaDao initiateDatabase() {
        AppDatabase appDatabase = AppDatabase.getInstance(this);
        return appDatabase.tarefaDao();
    }

    private void preencherCamposComDadosExistentes() {
        if (tarefaExistente != null) {
            et_titulo_tarefa.setText(tarefaExistente.getTitulo());
            et_descricao_tarefa.setText(tarefaExistente.getDescricao());
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btn_salvar_tarefa) {
            String titulo = et_titulo_tarefa.getText().toString();
            String descricao = et_descricao_tarefa.getText().toString();

            if (titulo.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Informe dados válidos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tarefaExistente != null) {
                tarefaExistente.setTitulo(titulo);
                tarefaExistente.setDescricao(descricao);
                tarefaDao.update(tarefaExistente);
                Toast.makeText(this, "Tarefa " + tarefaExistente.getTitulo() + " atualizada!", Toast.LENGTH_SHORT).show();
            } else {
                Tarefa novaTarefa = new Tarefa(titulo, descricao);
                tarefaDao.insert(novaTarefa);
                Toast.makeText(this, "Tarefa " + novaTarefa.getTitulo() + " adicionada!", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(CriarTarefa.this, MainActivity.class);
            startActivity(intent);
        }
    }
}