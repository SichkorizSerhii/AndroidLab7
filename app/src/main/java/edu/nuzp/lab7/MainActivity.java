package edu.nuzp.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewDrugs;
    private EditText searchEditText;
    private ArrayAdapter<String> adapter;
    private DrugDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DrugDatabaseHelper(this);

        listViewDrugs = findViewById(R.id.listViewDrugs);
        searchEditText = findViewById(R.id.editTextSearch);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewDrugs.setAdapter(adapter);

        populateListView();

        listViewDrugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedDrug = adapter.getItem(position);
                showDrugDetails(selectedDrug);
            }
        });
        searchEditText = findViewById(R.id.editTextSearch);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void populateListView() {
        adapter.clear();
        Cursor cursor = dbHelper.getDrugsCursor();
        while (cursor.moveToNext()) {
            String drugName = cursor.getString(cursor.getColumnIndexOrThrow(DrugDatabaseHelper.COLUMN_NAME));
            adapter.add(drugName);
        }
        cursor.close();
    }

    private void showDrugDetails(String drugName) {
        Intent intent = null;
        switch (drugName) {
            case "Парацетамол":
                intent = new Intent(this, ParacetamolDetailsActivity.class);
                break;
            case "Аспірин":
                intent = new Intent(this, AspirinDetailsActivity.class);
                break;
            case "Ібупрофен":
                intent = new Intent(this, IbuprofenDetailsActivity.class);
                break;
            case "Цитрамон-Дарниця":
                intent = new Intent(this, CitramonDetailsActivity.class);
                break;
            case "Анальгін-Дарниця":
                intent = new Intent(this, AnalginDetailsActivity.class);
                break;
            case "Нурофєн":
                intent = new Intent(this, NyrofenDetailsActivity.class);
                break;
            case "Но-шпа":
                intent = new Intent(this, NoshpaDetailsActivity.class);
                break;
            case "Амізон":
                intent = new Intent(this, AmizonDetailsActivity.class);
                break;
            case "Терафлю":
                intent = new Intent(this, TerafluDetailsActivity.class);
                break;
            case "Німесил":
                intent = new Intent(this, NimesilDetailsActivity.class);
                break;
            default:
        }
        startActivity(intent);
    }

}