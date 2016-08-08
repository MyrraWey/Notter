package com.muravyovdmitr.notter.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.muravyovdmitr.notter.ParamAction;
import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;
import com.muravyovdmitr.notter.utils.SnackBar;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */
@ContentView(R.layout.activity_create_note)
public class NoteActivity extends RoboActionBarActivity {
    private static final String INTENT_KEY = "intent";
    private static final String INTENT_CREATE = "create";
    private static final String INTENT_EDIT = "edit";
    private static final String INTENT_EDIT_NOTE = "note";

    @InjectView(R.id.activity_note_item_title)
    private TextInputEditText title;
    @InjectView(R.id.activity_note_item_title_layout)
    private TextInputLayout titleLayout;
    @InjectView(R.id.activity_note_item_message)
    private TextInputEditText message;
    @InjectView(R.id.activity_note_item_message_layout)
    private TextInputLayout messageLayout;
    @InjectView(R.id.coordinator_layout)
    private CoordinatorLayout coordinatorLayout;

    private Note note;

    public static Intent createNoteIntent(Context context) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NoteActivity.INTENT_KEY, NoteActivity.INTENT_CREATE);
        return intent;
    }

    public static Intent editNoteIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NoteActivity.INTENT_KEY, NoteActivity.INTENT_EDIT);
        intent.putExtra(NoteActivity.INTENT_EDIT_NOTE, note);
        return intent;
    }

    public static void parseResult(ParamAction<Note> saveNote, Intent data) {
        Note note = (Note) data.getSerializableExtra(INTENT_EDIT_NOTE);
        saveNote.execute(note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String intentValue;
        if (intent != null) {
            intentValue = intent.getStringExtra(this.INTENT_KEY);
            if (intentValue != null) {
                switch (intentValue) {
                    case NoteActivity.INTENT_CREATE:
                        return;
                    case NoteActivity.INTENT_EDIT:
                        this.note = (Note) intent.getSerializableExtra(NoteActivity.INTENT_EDIT_NOTE);
                        setNoteTitle(this.note.getTitle());
                        setNoteMessage(this.note.getMessage());
                        return;
                }
            }
        }

        throw new RuntimeException("Use activity methods for intent creation");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        if (note != null) {
            menu.findItem(R.id.menu_create_note_ok).setVisible(false);
        } else {
            menu.findItem(R.id.menu_save_edited_note).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create_note_ok:
                if (isNoteValid()) {
                    prepareActivityResult();
                    finish();
                } else {
                    new SnackBar().showShortSnackBarMessage(this.coordinatorLayout, "Fill all fields");
                }
                return true;
            case R.id.menu_save_edited_note:
                if (isNoteValid()) {
                    prepareActivityResult();
                    finish();
                } else {
                    new SnackBar().showShortSnackBarMessage(this.coordinatorLayout, "Fill all fields");
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareActivityResult() {
        Intent data = new Intent();
        if (this.note == null) {
            this.note = new Note(getNoteTitle(), getNoteMessage());
        } else {
            this.note.setTitle(getNoteTitle());
            this.note.setMessage(getNoteMessage());
        }
        data.putExtra(INTENT_EDIT_NOTE, this.note);
        setResult(Activity.RESULT_OK, data);
    }

    private boolean isNoteValid() {
        return getNoteTitle().length() > 0 && getNoteMessage().length() > 0;
    }

    private String getNoteTitle() {
        return this.title.getText().toString();
    }

    private String getNoteMessage() {
        return this.message.getText().toString();
    }

    private void setNoteTitle(String text) {
        if (this.title == null) {
            this.title = (TextInputEditText) findViewById(R.id.activity_note_item_title);
        }
        this.title.setText(text);
    }

    private void setNoteMessage(String text) {
        if (this.message == null) {
            this.message = (TextInputEditText) findViewById(R.id.activity_note_item_message);
        }
        this.message.setText(text);
    }
}
