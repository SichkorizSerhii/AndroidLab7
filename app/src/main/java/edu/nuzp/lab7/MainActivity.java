package edu.nuzp.lab7;

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

    private void showDrugDetails(String selectedDrug) {
        DrugInstructionsFragment drugInstructionsFragment = new DrugInstructionsFragment();
        Bundle args = new Bundle();
        args.putString(DrugInstructionsFragment.ARG_DRUG_NAME, selectedDrug);
        drugInstructionsFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, drugInstructionsFragment)
                .addToBackStack(null)
                .commit();
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
}