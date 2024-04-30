
package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    AlertDialog dialog;
    LinearLayout containerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add);
        containerLayout = findViewById(R.id.container);

        buildDialog();
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.show();
            }
        });
    }

    public void buildDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText taskName = view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Enter your Task")
                .setPositiveButton("SAVE",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick (DialogInterface dialog,int which){
                        addCard(taskName.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }

    private void addCard(String name){
        final View view = getLayoutInflater().inflate(R.layout.card,null);

        TextView nameView = view.findViewById(R.id.name);
        Button deleteButton = view.findViewById(R.id.delete);
        nameView.setText(name);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerLayout.removeView(view);
            }
        });
        containerLayout.addView(view);
    }
}
