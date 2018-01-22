package learning.memento.toto.my_memonto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Utilites.MyDividerItemDecoration;
import Utilites.RecyclerTouchListener;
import Utilites.SimpleCallbackTouchHelper;
import data.Category;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoriesAdapter mCategoriesAdapter;
    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.categories_rv);

        mCategoriesAdapter = new CategoriesAdapter(this, categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mCategoriesAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //add delete functionality to the recyclerview by swipe it left
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleCallbackTouchHelper(0, ItemTouchHelper.RIGHT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                categoryList.remove(position);
                mCategoriesAdapter.notifyDataSetChanged();
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

        addCategoryItems();
    }

    private void addCategoryItems(){
        Category c1 = new Category("new category 1", 2, "22 Dec, 2018");
        Category c2 = new Category("new category 2", 1, "02 Jan, 2018");
        Category c3 = new Category("new category 3", 0, "15 Mar, 2018");

        categoryList.add(c1);
        categoryList.add(c2);
        categoryList.add(c3);

        mCategoriesAdapter.notifyDataSetChanged();
    }
}
