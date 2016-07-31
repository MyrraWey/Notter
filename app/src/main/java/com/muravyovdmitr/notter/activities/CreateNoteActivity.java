package com.muravyovdmitr.notter.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.muravyovdmitr.notter.notter.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */
@ContentView(R.layout.activity_create_note)
public class CreateNoteActivity extends RoboActivity {
    @InjectView(R.id.holder_note_item_title)
    private TextInputEditText title;
    @InjectView(R.id.holder_note_item_title_layout)
    private TextInputLayout titleLayout;
    @InjectView(R.id.holder_note_item_message)
    private TextInputEditText message;
    @InjectView(R.id.holder_note_item_message_layout)
    private TextInputLayout messageLayout;
}
