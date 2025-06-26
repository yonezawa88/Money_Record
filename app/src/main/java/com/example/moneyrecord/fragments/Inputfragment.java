package com.example.moneyrecord.fragments;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.moneyrecord.R;
import com.example.moneyrecord.models.Record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;





public class Inputfragment extends Fragment {
    private String selectedGenre = "食費"; // デフォルトは「食費」
    // フィールドとして保存リストを定義（仮：メモリ上に保存）
    private final List<Record> recordList = new ArrayList<>();

    public Inputfragment() {
        // 空のコンストラクタ（必須）
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);



        // UI部品の取得
        Spinner spinner = view.findViewById(R.id.genre);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton radioButtonExpense = view.findViewById(R.id.radioButton1);
        EditText editTextAmount = view.findViewById(R.id.editTextNumber);
        EditText editTextNote = view.findViewById(R.id.editTextNote);
        EditText editTextDate = view.findViewById(R.id.editTextDate);
        Button okButton = view.findViewById(R.id.okbutton);


        String[] expenditureitem = {"食費", "交通費", "趣味", "家賃","光熱費", "日用品", "衣服", "美容"};
        String[] incomeItems = {"給料", "副業", "投資", "臨時収入","ギャンブル"};

        // 最初は支出のラジオボタンを選択状態にする
        radioButtonExpense.setChecked(true);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                expenditureitem
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // ドロップダウンのレイアウト
        spinner.setAdapter(adapter);
        spinner.setSelection(0);




        // ラジオボタンの選択が変更されたとき
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton1) {
                // 支出
                ArrayAdapter<String> expenseAdapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        expenditureitem
                );
                expenseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(expenseAdapter);
            } else if (checkedId == R.id.radioButton2) {
                // 収入
                ArrayAdapter<String> incomeAdapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        incomeItems
                );
                incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(incomeAdapter);
            }
        });



        Calendar calendar = Calendar.getInstance();
        // 表示用（UI）
        SimpleDateFormat uiFormat = new SimpleDateFormat("MM/dd", Locale.JAPAN);
        // 保存用（内部データ）
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN);

        // 初期表示
        editTextDate.setText(uiFormat.format(calendar.getTime()));

// カレンダー選択処理
        editTextDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(
                    requireContext(),
                    (view1, y, m, d) -> {
                        calendar.set(y, m, d);
                        editTextDate.setText(uiFormat.format(calendar.getTime()));

                        // 保存用の値（必要に応じて使ってください）
                        String fullDate = dataFormat.format(calendar.getTime());

                        // 例: ログに出す
                        Log.d("SelectedDate", "保存用: " + fullDate);
                    },
                    year, month, day
            ).show();
        });

        // 保存ボタン処理
        okButton.setOnClickListener(v -> {
            try {
                String date = dataFormat.format(calendar.getTime());
                String type = ((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String genre = spinner.getSelectedItem().toString();
                int amount = Integer.parseInt(editTextAmount.getText().toString());
                String note = editTextNote.getText().toString();

                Record record = new Record(date, amount, type, genre, note);
                recordList.add(record); // 今はメモリ上に保存

                Toast.makeText(requireContext(), "保存しました！", Toast.LENGTH_SHORT).show();
                Log.d("Record", record.toString());

                // フィールド初期化（任意）
                editTextAmount.setText("");
                editTextNote.setText("");
                calendar.setTimeInMillis(System.currentTimeMillis());
                editTextDate.setText(uiFormat.format(calendar.getTime()));

            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "金額を正しく入力してください", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(requireContext(), "保存に失敗しました", Toast.LENGTH_SHORT).show();
                Log.e("SaveError", "エラー: ", e);
            }
        });







        return view;

    }


}
