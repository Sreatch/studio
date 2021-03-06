package es.iessaladillo.pedrojoya.pr178.data.local;

import com.mooveit.library.Fakeit;

import java.util.ArrayList;
import java.util.List;

import es.iessaladillo.pedrojoya.pr178.data.model.Student;

public class Database {

    private static Database instance;

    private final ArrayList<Student> students;
    private int mCount;

    private Database() {
        students = new ArrayList<>();
        insertInitialData();
    }

    public synchronized static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void insertInitialData() {
        for (int i = 0; i < 5; i++) {
            addFakeStudent();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addFakeStudent() {
        mCount++;
        Student student = new Student(mCount, Fakeit.name()
                .name(), Fakeit
                .address().streetAddress(),
                "http://lorempixel.com/100/100/abstract/" + (mCount % 10 + 1) + "/");
        students.add(student);
    }

    public void deleteStudent(int position) {
        students.remove(position);
    }

}
