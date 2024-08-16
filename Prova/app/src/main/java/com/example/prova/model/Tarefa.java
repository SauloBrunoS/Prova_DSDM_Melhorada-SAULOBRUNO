package com.example.prova.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tarefa")
public class Tarefa {
        @PrimaryKey(autoGenerate = true)
        private int id;
        @ColumnInfo(name = "titulo")
        private String titulo;
        @ColumnInfo(name = "descricao")
        private String descricao;

        public Tarefa(String titulo, String descricao) {
                this.titulo = titulo;
                this.descricao = descricao;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        @NonNull
        @Override
        public String toString() {
                String nomeRetorno = this.id + " | " + this.titulo + " | " + this.descricao;
                return nomeRetorno;
        }
}
