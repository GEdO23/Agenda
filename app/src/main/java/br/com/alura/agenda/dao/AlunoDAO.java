package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();
    private static int contadorDeIds = 1;

    public List<Aluno> getAll() {
        return new ArrayList<>(alunos);
    }

    public void save(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private static void atualizaIds() {
        contadorDeIds++;
    }

    public void edit(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    private static Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }
}
