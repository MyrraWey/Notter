package com.muravyovdmitr.notter.recyclers

import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.muravyovdmitr.notter.ParamAction
import com.muravyovdmitr.notter.models.Note
import com.muravyovdmitr.notter.notter.R


/**
 * Created by Dima Muravyov on 31.07.2016.
 */

public class NotesViewHolder(itemView: View, private val removable: Removable, private val onItemClick: ParamAction<Int>) : RecyclerView.ViewHolder(itemView), Bindeable<Note> {
    // TODO: 31.07.2016 Injects this views
    private val title: TextView
    private val message: TextView

    private val clickListener: View.OnClickListener
    private val longClickListener: View.OnLongClickListener

    init {
        this.title = itemView.findViewById(R.id.activity_note_item_title) as TextView
        this.message = itemView.findViewById(R.id.activity_note_item_message) as TextView

        this.clickListener = View.OnClickListener { onItemClick.execute(adapterPosition) }

        this.longClickListener = View.OnLongClickListener {
            removeItemDialog().show()
            false
        }

        itemView.setOnClickListener(this.clickListener)
        itemView.setOnLongClickListener(this.longClickListener)
    }

    override fun bind(item: Note) {
        this.title.text = item.title
        this.message.text = item.message
    }

    private fun removeItemDialog(): AlertDialog.Builder {
        return AlertDialog.Builder(this.itemView.context).setTitle("Delete item #" + this.adapterPosition).setMessage("Are you sure?").setPositiveButton("Delete") { dialogInterface, i -> removable.remove(adapterPosition) }.setNegativeButton(android.R.string.cancel, null)
    }
}
