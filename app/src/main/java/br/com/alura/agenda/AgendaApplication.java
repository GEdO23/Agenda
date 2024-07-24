package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private static void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.save(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
        dao.save(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }
}
