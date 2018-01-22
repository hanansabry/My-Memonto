package learning.memento.toto.my_memonto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Decoration.MyDividerItemDecoration;
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

        mCategoriesAdapter = new CategoriesAdapter(categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mCategoriesAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

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
