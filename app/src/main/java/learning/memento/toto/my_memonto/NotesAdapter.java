package learning.memento.toto.my_memonto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import data.Category;
import data.Note;

/**
 * Created by Nono on 1/24/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private Context context;

    public NotesAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
//        Category category = categoryList.get(position);
//        int notesNum = category.getCategoryNotesNum();
//        holder.categoryName.setText(category.getCategoryName());
//        holder.categoryNotesNum.setText(context.getResources().getQuantityString(R.plurals.numberOfNotes, notesNum, notesNum));
//        holder.categoryDate.setText(category.getCategoryDate());

        Note note = noteList.get(position);
        holder.noteTitleTV.setText(note.getNoteTitle());
        holder.noteDateTV.setText(note.getNoteDate());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private ImageView noteImageView;
        private TextView noteTitleTV, noteDateTV;

        public NoteViewHolder(View itemView) {
            super(itemView);

            noteImageView = itemView.findViewById(R.id.note_icon);
            noteTitleTV = itemView.findViewById(R.id.note_title_tv);
            noteDateTV = itemView.findViewById(R.id.note_date_tv);
        }
    }
}
