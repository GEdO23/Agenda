package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantActivities.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.StudentDAO;
import br.com.alura.agenda.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR_NEW_STUDENT = "Novo aluno";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edita aluno";
    private EditText nameField;
    private EditText telephoneField;
    private EditText emailField;
    private final StudentDAO dao = new StudentDAO();
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        intitializeFields();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_student_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_form_menu_save) {
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void intitializeFields() {
        nameField = findViewById(R.id.activity_student_form_name);
        telephoneField = findViewById(R.id.activity_student_form_telephone);
        emailField = findViewById(R.id.activity_student_form_email);
    }

    private void finishForm() {
        fillStudentFields();
        if (student.isIdValid()) {
            dao.edit(student);
        } else {
            dao.save(student);
        }
        finish();
    }

    private void fillStudentFields() {
        String nome = nameField.getText().toString();
        String telefone = telephoneField.getText().toString();
        String email = emailField.getText().toString();

        student.setName(nome);
        student.setTelephone(telefone);
        student.setEmail(email);
    }

    private void loadStudent() {
        Intent dados = getIntent();
        if (dados.hasExtra(STUDENT_KEY)) {
            setTitle(TITLE_APPBAR_EDIT_STUDENT);
            student = (Student) dados.getSerializableExtra(STUDENT_KEY);
            fillFields();
        } else {
            setTitle(TITLE_APPBAR_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillFields() {
        nameField.setText(student.getName());
        telephoneField.setText(student.getTelephone());
        emailField.setText(student.getEmail());
    }

}