package com.ajeco.boom.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ajeco.boom.R;

public class MessagesFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_messages, container, false);
        final TextView textView = root.findViewById(R.id.text_message);

        textView.setText("This is messages fragment");


        return root;
    }
}