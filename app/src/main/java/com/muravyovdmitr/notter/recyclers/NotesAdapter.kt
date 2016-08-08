package com.muravyovdmitr.notter.recyclers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.muravyovdmitr.notter.ParamAction
import com.muravyovdmitr.notter.models.Note
import com.muravyovdmitr.notter.notter.R
import java.util.UUID

/**
 * Created by Dima Muravyov on 31.07.2016.
 */

class NotesAdapter(private val items: MutableList<Note>) : RecyclerView.Adapter<NotesViewHolder>() {
    private var onItemClick: ParamAction<Int>? = null

    fun setOnItemClick(onItemClick: ParamAction<Int>) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.holder_note_item, parent, false)

        return NotesViewHolder(
                view,
                object : Removable {
                    override fun remove(position: Int) {
                        items.removeAt(position)
                        notifyItemRemoved(position)
                    }
                },
                this.onItemClick!!)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        // TODO: 31.07.2016 Add NotImplemented exception
        holder.bind(this.items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): Note {
        return this.items[position]
    }

    fun findItemPositionById(id: UUID): Int {
        for (item in this.items) {
            if (item.id == id) {
                return this.items.indexOf(item)
            }
        }
        return -1
    }
}
