package com.muravyovdmitr.notter.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;

import java.util.List;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */

public class NotesAdapter extends RecyclerView.Adapter {
    private List<Note> items;

    public NotesAdapter(List<Note> notes) {
        this.items = notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.holder_note_item, parent, false);

        return new NotesViewHolder(view, new Removable() {
            @Override
            public void remove(int position) {
                items.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // TODO: 31.07.2016 Add NotImplemented exception
        ((Bindeable) holder).bind(this.items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
