package com.example.lab2bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonAdd;
    TextView textViewSelected;
    ListView listView;

    // Khai báo ArrayList để lưu tên
    ArrayList<String> nameList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Liên kết các thành phần giao diện với Java code
        editTextName = findViewById(R.id.editTextName);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewSelected = findViewById(R.id.textViewSelected);
        listView = findViewById(R.id.listView);

        // Khởi tạo ArrayList và Adapter
        nameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(arrayAdapter);

        // Xử lý sự kiện khi nhấn nút "Nhập"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                if (!name.isEmpty()) {
                    nameList.add(name);
                    arrayAdapter.notifyDataSetChanged(); // Cập nhật ListView
                    editTextName.setText(""); // Xóa nội dung sau khi thêm
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào phần tử trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = nameList.get(position);
                textViewSelected.setText("position: " + position + ", value = " + selectedName);
            }
        });

        // Xử lý sự kiện long click để xóa phần tử khỏi ListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nameList.remove(position); // Xóa phần tử
                arrayAdapter.notifyDataSetChanged(); // Cập nhật ListView
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}