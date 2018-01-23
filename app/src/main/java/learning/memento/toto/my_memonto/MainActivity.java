package learning.memento.toto.my_memonto;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Utilites.MyDividerItemDecoration;
import Utilites.RecyclerTouchListener;
import Utilites.SimpleCallbackTouchHelper;
import data.Category;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoriesAdapter mCategoriesAdapter;
    private List<Category> categoryList = new ArrayList<>();
    private FloatingActionButton fab;
    private EditText addCategoryEditText;
    private AlertDialog.Builder alertDialog;
    private View view;
    private boolean add;
    private int edit_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.categories_rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        mCategoriesAdapter = new CategoriesAdapter(this, categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mCategoriesAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //add delete functionality to the recyclerview by swipe it left
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleCallbackTouchHelper(MainActivity.this, 0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    categoryList.remove(position);
                    mCategoriesAdapter.notifyItemRemoved(position);
                    mCategoriesAdapter.notifyItemRangeChanged(position, categoryList.size());
                } else {
//                    Toast.makeText(MainActivity.this, "Swipped Right", Toast.LENGTH_SHORT).show();
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Country");
                    addCategoryEditText.setText(categoryList.get(position).getCategoryName());
                    alertDialog.show();
                }


            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Single Click on position " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Long Click on position " + position, Toast.LENGTH_SHORT).show();
            }
        }));

//        addCategoryItems();

        //add onclicklistener to the fab button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView();
                add = true;
                alertDialog.setTitle("Add Country");
                addCategoryEditText.setText("");
                alertDialog.show();
            }
        });
    }

    private void addCategoryItems() {
        Category c1 = new Category("new category 1", 2, "22 Dec, 2018");
        Category c2 = new Category("new category 2", 1, "02 Jan, 2018");
        Category c3 = new Category("new category 3", 0, "15 Mar, 2018");

        categoryList.add(c1);
        categoryList.add(c2);
        categoryList.add(c3);

        mCategoriesAdapter.notifyDataSetChanged();
    }

    //action of the Floating Action Button
    public void addNewCategory(String categoryName) {
        Category cat = new Category(categoryName, (int) (Math.random() * 20), "23, May 2018");
        categoryList.add(0, cat);
        mCategoriesAdapter.notifyItemInserted(0);
        recyclerView.smoothScrollToPosition(0);
//        mCategoriesAdapter.notifyDataSetChanged();
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.add_category_dialog, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addNewCategory(addCategoryEditText.getText().toString());
                dialog.dismiss();
            }
        });
        addCategoryEditText = (EditText) view.findViewById(R.id.add_category_et);
    }

    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
