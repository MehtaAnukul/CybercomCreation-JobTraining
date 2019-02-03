package com.anukul.listviewcontextactioncheckbox;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView carListView;
    private CarListViewAdapter carListViewAdapter;
    private List<String> carList = new ArrayList<>();
    public static List<String> userSelectionList = new ArrayList<>();

    public static ActionMode actionMode = null;

    private CoordinatorLayout coordinatorLayout;

    public static boolean isActionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCars();

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);

        carListView = findViewById(R.id.activity_main_carListView);

        carListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        carListView.setMultiChoiceModeListener(multiChoiceModeListener);

        carListViewAdapter = new CarListViewAdapter(carList,this);
        carListView.setAdapter(carListViewAdapter);


    }
    private void getCars() {
        String carItem[] = getResources().getStringArray(R.array.cars);

        for (String item : carItem) {
            carList.add(item);

        }
    }

    AbsListView.MultiChoiceModeListener multiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.contextual_menu,menu);

            isActionMode = true;
            actionMode = mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.contextual_menu_delete:
                    carListViewAdapter.removeItem(userSelectionList);
                    Snackbar.make(coordinatorLayout,+userSelectionList.size() +" Item Deleted",Snackbar.LENGTH_SHORT).show();
                    actionMode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            actionMode = null;
            userSelectionList.clear();
        }
    };
}
