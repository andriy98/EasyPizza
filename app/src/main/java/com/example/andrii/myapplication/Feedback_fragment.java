package com.example.andrii.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Feedback_fragment extends Fragment{
    private static final int LAYOUT = R.layout.feedback;
    private View view;
    private EditText editText;
    private Button button;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        editText = (EditText) view.findViewById(R.id.edit_feedback);
        button = (Button) view.findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.length()>1){
                    StartAsykcTask_feedback task = (StartAsykcTask_feedback) new StartAsykcTask_feedback(getContext(), "rtdmytryshyn@gmail.com", "Зворотній зв’язок",
                            "Повiдомлення:"+"\n"+editText.getText()).execute();
                    editText.setText("");
                }
            }
        });
        return view;
    }
}
