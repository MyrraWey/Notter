package com.muravyovdmitr.notter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;
import com.muravyovdmitr.notter.recyclers.NotesAdapter;
import com.muravyovdmitr.notter.recyclers.NotesDecoration;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_notes)
public class NotesActivity extends RoboActionBarActivity {
    @InjectView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recycler)
    RecyclerView recyclerView;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        initToolbar();
        initFab();
        prepareRecyclerView();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initFab() {
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareRecyclerView() {
        this.adapter = new NotesAdapter(generateItems());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addItemDecoration(new NotesDecoration(this));
    }

    private List<Note> generateItems() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("First note", "It's my first note"));
        notes.add(new Note("Second note", "Second"));
        notes.add(new Note("Third note", "O_O"));
        notes.add(new Note("Fourth note", "Stop that"));
        notes.add(new Note("Fifth note", "Kill me pls"));
        notes.add(new Note("Sixth note", "Why me !!!!!"));

        return notes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}