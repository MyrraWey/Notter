package com.muravyovdmitr.notter.recyclers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muravyovdmitr.notter.ParamAction;
import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */

public class NotesAdapter extends RecyclerView.Adapter {
    private List<Note> items;
    private ParamAction<Integer> onItemClick;

    public NotesAdapter(List<Note> notes) {
        this.items = notes;
    }

    public void setOnItemClick(ParamAction<Integer> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.holder_note_item, parent, false);

        return new NotesViewHolder(
                view,
                new Removable() {
                    @Override
                    public void remove(int position) {
                        items.remove(position);
                        notifyItemRemoved(position);
                    }
                },
                this.onItemClick);
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

    public Note getItem(int position) {
        return this.items.get(position);
    }

    public int findItemPositionById(UUID id) {
        for (Note item : this.items) {
            if (item.getId().equals(id)) {
                return this.items.indexOf(item);
            }
        }
        return -1;
    }
}
