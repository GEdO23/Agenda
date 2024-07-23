package br.com.alura.agenda.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();
    private static int contadorDeIds = 1;

    public List<Aluno> getAll() {
        Log.i("lista_alunos", "getAll chamado:" + alunos);
        return new ArrayList<>(alunos);
    }

    public void save(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        Log.i("new_aluno", "Novo aluno adcionado:" + aluno.getNome());
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
            Log.i("edit_aluno", "Aluno editado:" + aluno.getNome());
        }
    }

    private static Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                Log.i("aluno", "Aluno encontrado:" + aluno.getNome());
                return a;
            }
        }
        return null;
    }


    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
            Log.i("remove_aluno", "Aluno removido:" + aluno.getNome());
        }
    }
}
