package com.muravyovdmitr.notter.recyclers;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.muravyovdmitr.notter.ParamAction;
import com.muravyovdmitr.notter.models.Note;
import com.muravyovdmitr.notter.notter.R;


/**
 * Created by Dima Muravyov on 31.07.2016.
 */

class NotesViewHolder extends RecyclerView.ViewHolder implements Bindeable<Note> {
    // TODO: 31.07.2016 Injects this views
    private TextView title;
    private TextView message;

    private Removable removable;
    private ParamAction<Integer> onItemClick;
    ;

    NotesViewHolder(View itemView, Removable removable, ParamAction<Integer> onItemClick) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.activity_note_item_title);
        this.message = (TextView) itemView.findViewById(R.id.activity_note_item_message);

        this.removable = removable;
        this.onItemClick = onItemClick;

        itemView.setOnClickListener(this.clickListener);
        itemView.setOnLongClickListener(this.longClickListener);
    }

    @Override
    public void bind(Note item) {
        this.title.setText(item.getTitle());
        this.message.setText(item.getMessage());
    }

    private AlertDialog.Builder removeItemDialog() {
        return new AlertDialog.Builder(this.itemView.getContext())
                .setTitle("Delete item #" + this.getAdapterPosition())
                .setMessage("Are you sure?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removable.remove(getAdapterPosition());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onItemClick.execute(getAdapterPosition());
        }
    };

    private final View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            removeItemDialog().show();
            return false;
        }
    };
}
