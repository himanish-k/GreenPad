package com.himanishkaushal.greenpad;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EntryInformationFragment extends Fragment {

	public EntryInformationFragment() {
		
	}
	
	public static EntryInformationFragment newInstance() {
		EntryInformationFragment eif = new EntryInformationFragment();
		return eif;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_entry_information, container, false);
        return rootView;
	}
	
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
		
	}

}
