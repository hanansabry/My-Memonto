package learning.memento.toto.my_memonto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Utilites.CustomRecyclerView;
import data.Note;

public class NotesActivity extends AppCompatActivity {

    private CustomRecyclerView notesRecylerView;
    private NotesAdapter mNotesAdapter;
    private List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesRecylerView = (CustomRecyclerView) findViewById(R.id.notes_rv);
        //set empty view
        View emptyView = findViewById(R.id.empty_recyclerview);
        notesRecylerView.setEmptyView(emptyView);

        mNotesAdapter = new NotesAdapter(this, noteList);
        notesRecylerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecylerView.setAdapter(mNotesAdapter);

        populateNotes();
    }

    private void populateNotes(){
        noteList.add(new Note("note1", "content1"));
        noteList.add(new Note("note2", "content2"));
        noteList.add(new Note("note3", "content3"));
        noteList.add(new Note("note4", "content4"));
    }
}
