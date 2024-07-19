package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public List<Aluno> getAll() {
        return new ArrayList<>(alunos);
    }

    public void save(Aluno aluno) {
        alunos.add(aluno);
    }
}
