package com.muravyovdmitr.notter.recyclers;

import com.muravyovdmitr.notter.ParamAction;
import com.muravyovdmitr.notter.models.Note;

import java.util.List;

/**
 * Created by Dima Muravyov on 07.08.2016.
 */

public class NotesAdapterBuilder {
    private List<Note> notes;
    private ParamAction<Integer> onItemClick;

    public NotesAdapterBuilder setNotes(List<Note> notes) {
        this.notes = notes;
        return this;
    }

    public NotesAdapterBuilder setOnItemClick(ParamAction<Integer> onItemClick) {
        this.onItemClick = onItemClick;
        return this;
    }

    public NotesAdapter build() {
        NotesAdapter notesAdapter = new NotesAdapter(this.notes);
        notesAdapter.setOnItemClick(this.onItemClick);
        return notesAdapter;
    }
}
