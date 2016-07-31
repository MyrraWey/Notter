package com.muravyovdmitr.notter.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;


/**
 * Created by Dima Muravyov on 31.07.2016.
 */

class NotesViewHolder extends RecyclerView.ViewHolder implements Bindeable<Note> {
    // TODO: 31.07.2016 Injects this views
    private TextView title;
    private TextView message;

    NotesViewHolder(View itemView) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.holder_note_item_title);
        this.message = (TextView) itemView.findViewById(R.id.holder_note_item_message);
    }

    @Override
    public void bind(Note item) {
        this.title.setText(item.getTitle());
        this.message.setText(item.getMessage());
    }
}
