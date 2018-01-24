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

/**
 * Created by Hanan on 1/22/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<Category> categoryList;
    private Context context;


    public CategoriesAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent, false);
        return new CategoryViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        int notesNum = category.getCategoryNotesNum();
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryNotesNum.setText(context.getResources().getQuantityString(R.plurals.numberOfNotes, notesNum, notesNum));
        holder.categoryDate.setText(category.getCategoryDate());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryName, categoryNotesNum, categoryDate;
        private ImageView categoryIcon;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.category_name_tv);
            categoryNotesNum = itemView.findViewById(R.id.category_notes_num_tv);
            categoryDate = itemView.findViewById(R.id.category_date_tv);
            categoryIcon = itemView.findViewById(R.id.category_icon);
        }
    }
}
