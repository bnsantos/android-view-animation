package com.bruno.simple.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by bruno on 19/03/15.
 */
public class PlaceholderFragment extends Fragment {
    private WeakReference<MainActivity> mActivity;
    private View mView;
    private ListView mListView;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView.findViewById(R.id.showButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActivity!=null&&mActivity.get()!=null){
                    mActivity.get().changeBottomFragmentVisibility((int) (mListView.getY()));
                }
            }
        });

        mListView = (ListView) mView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, getActivity().getResources().getStringArray(R.array.countries));
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Clicked on placeholder fragment [" + position + "]", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = new WeakReference<>((MainActivity) activity);
    }
}
