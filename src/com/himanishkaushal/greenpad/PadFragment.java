package com.himanishkaushal.greenpad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


public class PadFragment extends Fragment {
	
	public final static String EXTRA_ENTRY_YEAR = "com.himanishkaushal.spent.ENTRY_YEAR",
							   EXTRA_ENTRY_MONTH = "com.himanishkaushal.spent.ENTRY_MONTH",
							   EXTRA_ENTRY_DAY = "com.himanishkaushal.spent.ENTRY_DAY",
							   EXTRA_ENTRY_PAYEE = "com.himanishkaushal.spent.ENTRY_PAYEE",
							   EXTRA_ENTRY_PAYMENT = "com.himanishkaushal.spent.ENTRY_PAYMENT";
	
	private EntrySource source;
	private ExpandableEntryAdapter adapter;
	private ExpandableListView exListView;
	private Cursor cursor;

	public PadFragment() {
		// TODO Auto-generated constructor stub
	}
	
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PadFragment newInstance(int sectionNumber) {
        PadFragment fragment = new PadFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        source = new EntrySource(getActivity());
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pad, container, false);
        return rootView;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	exListView = (ExpandableListView) getActivity().findViewById(R.id.list);
        refreshList();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    
    public void refreshList() {
    	
    	source.open();
    	source.insertFewInitialEntries();
    	cursor = source.fetchAllEntryDates();
    	source.close();
    	
        adapter = new ExpandableEntryAdapter(cursor, getActivity());
        exListView.setAdapter(adapter);
        for(int i=0; i < exListView.getCount(); i++)
        	exListView.expandGroup(i);
    }

}
