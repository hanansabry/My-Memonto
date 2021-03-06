package learning.memento.toto.my_memonto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Utilites.CustomRecyclerView;
import Utilites.MyDividerItemDecoration;
import Utilites.RecyclerTouchListener;
import Utilites.SimpleCallbackTouchHelper;
import data.Category;

public class MainActivity extends AppCompatActivity {

    private CustomRecyclerView recyclerView;
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

        recyclerView = (CustomRecyclerView) findViewById(R.id.categories_rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        View emptyView = findViewById(R.id.empty_recyclerview);
        recyclerView.setEmptyView(emptyView);

        mCategoriesAdapter = new CategoriesAdapter(this, categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mCategoriesAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        initDialog();

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
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Category");
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
                Intent notesIntent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(notesIntent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Long Click on position " + position, Toast.LENGTH_SHORT).show();
            }
        }));

        //add onclicklistener to the fab button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView();
                add = true;
                alertDialog.setTitle("Add Category");
                addCategoryEditText.setText("");
                alertDialog.show();
            }
        });
    }

    private void addCategoryItems() {
        Category c1 = new Category("new category 1", 0);
        Category c2 = new Category("new category 2", 0);
        Category c3 = new Category("new category 3", 0);

        categoryList.add(c1);
        categoryList.add(c2);
        categoryList.add(c3);

        mCategoriesAdapter.notifyDataSetChanged();
    }

    public void addNewCategory(String categoryName) {
        Category cat = new Category(categoryName, 0);
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
                if(add){
                    add =false;
                    addNewCategory(addCategoryEditText.getText().toString());
                    dialog.dismiss();
                } else {
                    Category editedCat = categoryList.get(edit_position);
                    editedCat.setCategoryName(addCategoryEditText.getText().toString());
                    mCategoriesAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(MainActivity.this, "Dialog is cancelled", Toast.LENGTH_SHORT).show();
                mCategoriesAdapter.notifyDataSetChanged();
            }
        });
        addCategoryEditText = view.findViewById(R.id.add_category_et);
    }

    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.settings_action){
            Toast.makeText(this, "Setting action selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
