package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantActivities.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.StudentDAO;
import br.com.alura.agenda.model.Student;
import br.com.alura.agenda.ui.adapter.StudentListAdapter;

public class StudentListActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Lista de alunos";
    private final StudentDAO dao = new StudentDAO();
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setTitle(TITLE_APPBAR);
        configFabNewStudent();
        configList();
        dao.save(new Student("Alex", "1122223333", "alex@alura.com.br"));
        dao.save(new Student("Fran", "1122223333", "fran@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStudents();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_list_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (menuInfo != null) {
                Student chosenStudent = adapter.getItem(menuInfo.position);
                remove(chosenStudent);
            } else {
                Log.e("erroMenuInfo", "menuInfo é nulo");
            }
        } else {
            Log.e("erroMenuInfo", "tituloDoMenu é nulo");
        }
        return super.onContextItemSelected(item);
    }

    private void configFabNewStudent() {
        FloatingActionButton buttonNewStudent = findViewById(R.id.activity_student_list_fab_new_student);
        buttonNewStudent.setOnClickListener(v -> openFormInsertMode());
    }

    private void openFormInsertMode() {
        startActivity(new Intent(StudentListActivity.this,
                StudentFormActivity.class));
    }

    private void updateStudents() {
        adapter.update(dao.getAll());
    }

    private void configList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        configAdapter(studentList);
        configOnItemClickListener(studentList);
        registerForContextMenu(studentList);
    }

    private void configAdapter(@NonNull ListView studentList) {
        adapter = new StudentListAdapter(this);
        studentList.setAdapter(adapter);
    }

    private void configOnItemClickListener(@NonNull ListView studentList) {
        studentList.setOnItemClickListener((parent, view, position, id) -> {
            Student chosenStudent = (Student) parent.getItemAtPosition(position);
            openFormEditMode(chosenStudent);
        });
    }

    private void remove(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    private void openFormEditMode(Student student) {
        Intent goToFormActivity = new Intent(StudentListActivity.this, StudentFormActivity.class);
        goToFormActivity.putExtra(STUDENT_KEY, student);
        startActivity(goToFormActivity);
    }
}
