package com.am.steammarketanalyzer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements DialogItem.ItemListener {

    static SharedPreferences getSharedPreferences(Context context) { return context.getSharedPreferences("sharedPreferences", MODE_PRIVATE); }

    private ImageView mainStateView, translationView, addView;
    private ListView listView;

    private boolean mainState;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainStateView = findViewById(R.id.mainState);
        mainStateView.setOnClickListener(v -> switchMainState());

        translationView = findViewById(R.id.translation);
        translationView.setOnClickListener(v -> setTranslation());

        addView = findViewById(R.id.add);
        addView.setOnClickListener(v -> addItem());

        listView = findViewById(R.id.list);

        loadSettings();
    }

    @Override
    public void onStop() { super.onStop(); saveSettings(); }

    private void switchMainState() { mainState = !mainState; setViews(); }

    private void setViews() {
        mainStateView.setColorFilter(mainState ? getColor(R.color.white) : getColor(R.color.steam_4));

        translationView.setEnabled(!mainState);
        addView.setEnabled(!mainState);

        renderListView();
    }

    private void setTranslation() {
        DialogTranslation dialogTranslation = new DialogTranslation();
        dialogTranslation.show(getSupportFragmentManager(), getString(R.string.translation_dialog));
    }

    private void addItem() {
        DialogItem dialogItem = new DialogItem();
        dialogItem.show(getSupportFragmentManager(), getString(R.string.item_dialog));
    }

    @Override
    public void getData(int id, String imageLink, String name, String link) {
        if (id != 0) {
            items.add(new Item(id, imageLink, name, link));
            renderListView();
        } else
            Toast.makeText(this, getSharedPreferences(this).getString(DialogTranslation.getValues("notFound")[1], DialogTranslation.getValues("notFound")[0]), Toast.LENGTH_SHORT).show();
    }

    public void renderListView() {
        sortItems();

        String[] imageLinks = new String[items.size()];
        String[] names = new String[items.size()];
        String[] links = new String[items.size()];
        boolean[] states = new boolean[items.size()];

        for (int i = 0; i < items.size(); i++) {
            imageLinks[i] = items.get(i).getImageLink();
            names[i] = items.get(i).getName();
            links[i] = items.get(i).getLink();
            states[i] = items.get(i).getState();
        }

        ListAdapter adapter = new ListAdapter(this, imageLinks, names, links, states, mainState);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> showRemoveAlert(position));
        listView.setOnItemClickListener((parent, view, position, id) -> showItem(position));
    }

    private void showItem(int index) {
        Intent intent = new Intent(getApplicationContext(), ActivityItem.class);
        intent.putExtra(getString(R.string.e_item), items.get(index));
        startActivity(intent);
    }

    private boolean showRemoveAlert(int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getSharedPreferences(this).getString(DialogTranslation.getValues("remove")[1], DialogTranslation.getValues("remove")[0]));

        builder.setNegativeButton(getSharedPreferences(this).getString(DialogTranslation.getValues("removeCancel")[1], DialogTranslation.getValues("removeCancel")[0]), (dialogInterface, i) -> { });
        builder.setPositiveButton(getSharedPreferences(this).getString(DialogTranslation.getValues("removeOk")[1], DialogTranslation.getValues("removeOk")[0]), (dialogInterface, i) -> removeItem(index));
        builder.create().show();

        return true;
    }

    private void removeItem(int index) { items.remove(index); renderListView(); }

    private void sortItems() { items.sort((object1, object2) -> object1.getName().compareTo(object2.getName())); }

    private void loadSettings() {
        mainState = getSharedPreferences(this).getBoolean(getString(R.string.k_main_state), mainState);
        items = new Gson().fromJson(getSharedPreferences(this).getString(getString(R.string.k_items), null), new TypeToken<List<Item>>(){}.getType());
        if (items == null) items = new ArrayList<>();

        setViews();
        renderListView();
    }

    private void saveSettings() {
        getSharedPreferences(this).edit().putBoolean(getString(R.string.k_main_state), mainState).apply();
        getSharedPreferences(this).edit().putString(getString(R.string.k_items), new Gson().toJson(items)).apply();
    }
}