package com.example.bookwormadventuresdeluxe2;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A {@link Fragment} subclass for navbar menu item 2.
 */
public class RequestsFragment extends Fragment implements View.OnClickListener
{
    View view;
    Button toggle;
    TextView current;
    boolean borrow;

    public RequestsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_requests, container, false);
        current = view.findViewById(R.id.current);
        current.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        toggle = view.findViewById(R.id.toggle_btn);
        borrow = false;

        toggle.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        ConstraintLayout layout = this.view.findViewById(R.id.requests);
        ConstraintSet cons = new ConstraintSet();
        cons.clone(layout);

        if(borrow) { // currently in borrow
            current.setText(getResources().getString(R.string.my_requests));
            toggle.setText((getResources().getString(R.string.borrow)));

            cons.connect(R.id.toggle_btn, ConstraintSet.END, R.id.requests, ConstraintSet.END);
            cons.connect(R.id.toggle_btn, ConstraintSet.START, R.id.current, ConstraintSet.END);
            cons.connect(R.id.current, ConstraintSet.END, R.id.toggle_btn, ConstraintSet.START);
            cons.connect(R.id.current, ConstraintSet.START, R.id.requests, ConstraintSet.START);
        } else { // currently in my requests
            current.setText(getResources().getString(R.string.borrow));
            toggle.setText((getResources().getString(R.string.my_requests)));

            cons.connect(R.id.current, ConstraintSet.END, R.id.requests, ConstraintSet.END);
            cons.connect(R.id.current, ConstraintSet.START, R.id.toggle_btn, ConstraintSet.END);
            cons.connect(R.id.toggle_btn, ConstraintSet.END, R.id.current, ConstraintSet.START);
            cons.connect(R.id.toggle_btn, ConstraintSet.START, R.id.requests, ConstraintSet.START);
        }

        cons.applyTo(layout);
        borrow = !borrow;
    }
}
