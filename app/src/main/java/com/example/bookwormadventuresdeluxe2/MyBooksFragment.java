package com.example.bookwormadventuresdeluxe2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookwormadventuresdeluxe2.Utilities.RecyclerViewClickListener;
import com.example.bookwormadventuresdeluxe2.Utilities.RecyclerViewTouchListener;
import com.example.bookwormadventuresdeluxe2.Utilities.Status;

import java.util.ArrayList;


/**
 * A {@link Fragment} subclass for navbar menu item 1.
 */
public class MyBooksFragment extends Fragment
{
    private RecyclerView myBooksRecyclerView;
    private BookListAdapter myBooksRecyclerAdapter;
    private RecyclerView.LayoutManager myBooksRecyclerLayoutManager;
    private ArrayList<Book> myBooksList;

    public MyBooksFragment()
    {
        this.myBooksList = new ArrayList<Book>();
        // Temporary books to show how listview looks
        myBooksList.add(new Book("1984", "George Orwell", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis", "9780141036144", Status.Available));
        myBooksList.add(new Book("To Kill a Mockingbird", "Harper Lee", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis", "9780446310789", Status.Requested));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View myBooksView = inflater.inflate(R.layout.fragment_my_books, container, false);

        // Set visibility of desired custom header buttons
        myBooksView.findViewById(R.id.app_header_filter_button).setVisibility(View.VISIBLE);
        myBooksView.findViewById(R.id.app_header_scan_button).setVisibility(View.VISIBLE);
        myBooksView.findViewById(R.id.app_header_notification_button).setVisibility(View.VISIBLE);

        return myBooksView;
    }

    // https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment#:~:text=Use%20getView%20%28%29%20or%20the%20View%20parameter%20from,method%29.%20With%20this%20you%20can%20call%20findViewById%20%28%29.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        myBooksRecyclerView = (RecyclerView) view.findViewById(R.id.my_books_recycler_view);
        myBooksRecyclerView.setHasFixedSize(true);

        myBooksRecyclerLayoutManager = new LinearLayoutManager(this.getContext());
        myBooksRecyclerView.setLayoutManager(myBooksRecyclerLayoutManager);

        myBooksRecyclerAdapter = new BookListAdapter(myBooksList, this.getContext());
        myBooksRecyclerView.setAdapter(myBooksRecyclerAdapter);

        // Handles clicks on the RecyclerView
        myBooksRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this.getContext(), myBooksRecyclerView, new RecyclerViewClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {
                // Open book detail view when an item is clicked
                MyBooksDetailViewFragment bookDetailFragment = new MyBooksDetailViewFragment();
                // Send the selected book to MyBooksDetailViewFragment and open the fragment
                bookDetailFragment.onFragmentInteraction(myBooksList.get(position));
                getFragmentManager().beginTransaction().replace(R.id.frame_container, bookDetailFragment).commit();
            }

            @Override
            public void onLongClick(View view, int position)
            {
                // Required for now in case we need it later
            }
        }));
    }
}