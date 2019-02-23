package com.anukul.sqlitevsroomtest.sqlite;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.roomDatabase.MyAppDatabase;
import com.anukul.sqlitevsroomtest.roomModel.UserModel;
import com.anukul.sqlitevsroomtest.roomfragment.RoomAddFragment;
import com.anukul.sqlitevsroomtest.roomfragment.RoomReadUserFragment;
import com.anukul.sqlitevsroomtest.sqlite.activity.SqliteActivity;
import com.anukul.sqlitevsroomtest.sqlite.fragment.SqlitReadContactFragment;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;

    private FragmentManager fragmentManager;
    private RoomReadUserFragment roomReadUserFragment;
    public static MyAppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();


        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.appbar_menu_searchIcon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_insertData:
               /* RoomFragment roomFragment = new RoomFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.activity_room_fragment_container,roomFragment,RoomActivity.class.getSimpleName());
                fragmentTransaction.commit();*/
                RoomReadUserFragment roomReadUserFragmentt = new RoomReadUserFragment();

                RoomAddFragment roomAddFragment = new RoomAddFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_room_fragment_container,roomAddFragment,RoomActivity.class.getSimpleName());
                fragmentTransaction.hide(roomReadUserFragmentt);
                fragmentTransaction.commit();
                break;

            case R.id.appbar_menu_ViewData:
                RoomAddFragment roomAddFragmentt = new RoomAddFragment();

                RoomReadUserFragment roomReadUserFragment = new RoomReadUserFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.activity_room_fragment_container,roomReadUserFragment,RoomActivity.class.getSimpleName());
                fragmentTransaction1.hide(roomAddFragmentt);
                fragmentTransaction1.commit();
                break;
            case R.id.appbar_menu_searchIcon:

                MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        Toast.makeText(RoomActivity.this, "Action View Expanded", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        Toast.makeText(RoomActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                };

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        String usertext = s.toLowerCase();
        ArrayList<UserModel> searchArraylist = new ArrayList<>();

        for (int i = 0; i < RoomReadUserFragment.getUserModelArrayList().size(); i++) {
            if (RoomReadUserFragment.getUserModelArrayList().get(i).getName().toLowerCase().contains(usertext)
                    || RoomReadUserFragment.getUserModelArrayList().get(i).getEmail().toLowerCase().contains(usertext)
                    || RoomReadUserFragment.getUserModelArrayList().get(i).getPhoneNo().toLowerCase().contains(usertext)
                    || RoomReadUserFragment.getUserModelArrayList().get(i).getLastName().toLowerCase().toLowerCase().contains(usertext)) {
                searchArraylist.add(RoomReadUserFragment.getUserModelArrayList().get(i));
            }
        }
        Log.e("DOST1", RoomReadUserFragment.getUserModelArrayList()+ "");
        Log.e("DOST2", searchArraylist + "");
        RoomReadUserFragment.getRoomReadUserAdapter().updateList(searchArraylist);


        return true;
    }
}
