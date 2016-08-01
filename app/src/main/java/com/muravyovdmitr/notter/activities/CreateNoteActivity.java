package com.muravyovdmitr.notter.activities;

import android.app.Activity;
import android.content.Intent;
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
public class CreateNoteActivity extends RoboActionBarActivity {
    private static final String NOTE_TITLE_KEY = "title";
    private static final String NOTE_MESSAGE_KEY = "message";

    @InjectView(R.id.holder_note_item_title)
    private TextInputEditText title;
    @InjectView(R.id.holder_note_item_title_layout)
    private TextInputLayout titleLayout;
    @InjectView(R.id.holder_note_item_message)
    private TextInputEditText message;
    @InjectView(R.id.holder_note_item_message_layout)
    private TextInputLayout messageLayout;
    @InjectView(R.id.coordinator_layout)
    private CoordinatorLayout coordinatorLayout;

    public static void parseResult(ParamAction<Note> saveNote, Intent data) {
        Note note = new Note(data.getStringExtra(NOTE_TITLE_KEY), data.getStringExtra(NOTE_MESSAGE_KEY));
        saveNote.execute(note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create_note_ok:
                if (isNoteValid()) {
                    preapreActivityResult();
                    finish();
                } else {
                    new SnackBar().showShortSnackBarMessage(this.coordinatorLayout, "Fill all fields");
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void preapreActivityResult() {
        Intent data = new Intent();
        data.putExtra(NOTE_TITLE_KEY, getNoteTitle());
        data.putExtra(NOTE_MESSAGE_KEY, getNoteMessage());
        setResult(Activity.RESULT_OK, data);
    }

    private boolean isNoteValid() {
        return getNoteTitle().length() > 0 && getNoteMessage().length() > 0;
    }

    private String getNoteTitle() {
        return this.titleLayout.getEditText().getText().toString();
    }

    private String getNoteMessage() {
        return this.messageLayout.getEditText().getText().toString();
    }
}
