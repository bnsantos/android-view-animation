package com.bruno.simple.animation;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private View mHiddenView;
        private View mView;

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
            mHiddenView = mView.findViewById(R.id.hiddenView);
            mView.findViewById(R.id.showButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeViewVisibility();
                }
            });

            ListView listView = (ListView) mView.findViewById(R.id.listView);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, getActivity().getResources().getStringArray(R.array.countries));
            listView.setAdapter(adapter);
        }

        private void changeViewVisibility(){
            if(mHiddenView.getVisibility()==View.VISIBLE){
                hideView();
            }else{
                showView();
            }
        }

        private void hideView(){
            slideToBottom(mHiddenView);
        }

        private void showView(){
            slideFromBottom(mHiddenView);
        }

        public void slideFromBottom(View view){
            TranslateAnimation animate = new TranslateAnimation(0,0,mView.getHeight(),0);
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
            view.setVisibility(View.VISIBLE);
        }

        public void slideToBottom(View view){
            TranslateAnimation animate = new TranslateAnimation(0,0,0,mView.getHeight());
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
            view.setVisibility(View.GONE);
        }
    }
}
