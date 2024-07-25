package br.com.alura.agenda.dao;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO implements AbstractDAO<Aluno> {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    @Override
    public List<Aluno> getAll() {
        return new ArrayList<>(alunos);
    }

    @Override
    public Aluno getById(@NonNull Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void save(@NonNull Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    @Override
    public void update(@NonNull Aluno aluno) {
        Aluno alunoEncontrado = getById(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Override
    public void remove(@NonNull Aluno aluno) {
        Aluno alunoDevolvido = getById(aluno);
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
        }
    }
}
