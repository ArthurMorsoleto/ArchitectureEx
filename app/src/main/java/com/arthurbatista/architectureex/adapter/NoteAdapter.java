package com.arthurbatista.architectureex.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arthurbatista.architectureex.R;
import com.arthurbatista.architectureex.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private List<Note> noteList = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_layout, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Note actualNote = noteList.get(i);
        myViewHolder.textViewPriority.setText(String.valueOf(actualNote.getPriority()));
        myViewHolder.textViewTitle.setText(actualNote.getTitle());
        myViewHolder.textViewDescription.setText(actualNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNotes(List<Note> list){
        this.noteList = list;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return noteList.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewPriority;
        private TextView textViewDescription;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtTitle);
            textViewDescription = itemView.findViewById(R.id.txtDescription);
            textViewPriority = itemView.findViewById(R.id.txtPriority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(noteList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
