package br.com.alura.agenda.dao;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Student;

public class StudentDAO implements AbstractDAO<Student> {

    private final static List<Student> STUDENTS = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(STUDENTS);
    }

    @Override
    public Student getById(@NonNull Student student) {
        for (Student a :
                STUDENTS) {
            if (a.getId() == student.getId()) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void save(@NonNull Student student) {
        student.setId(idCounter);
        STUDENTS.add(student);
        idCounter++;
    }

    @Override
    public void edit(@NonNull Student student) {
        Student studentEncontrado = getById(student);
        if (studentEncontrado != null) {
            int posicaoDoAluno = STUDENTS.indexOf(studentEncontrado);
            STUDENTS.set(posicaoDoAluno, student);
        }
    }

    @Override
    public void remove(@NonNull Student student) {
        Student studentDevolvido = getById(student);
        if (studentDevolvido != null) {
            STUDENTS.remove(studentDevolvido);
        }
    }
}
