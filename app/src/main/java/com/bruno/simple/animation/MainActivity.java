package com.bruno.simple.animation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;


public class MainActivity extends ActionBarActivity {
    private WeakReference<BottomFragment> mBottomFragment;
    private FrameLayout mContainer;
    private FrameLayout mBottomContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.placeholderFragment, new PlaceholderFragment())
                    .commit();
        }
        mContainer = (FrameLayout) findViewById(R.id.container);
        mBottomContainer = (FrameLayout) findViewById(R.id.bottomFragment);
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

    public void changeBottomFragmentVisibility(int offset){
        if(mBottomContainer.getVisibility()==View.VISIBLE){
            setBottomFragmentMargin(0);
            slideToBottom(mBottomContainer, offset);
        }else{
            initFragment();
            setBottomFragmentMargin(offset);
            slideFromBottom(mBottomContainer);
        }
    }

    private void slideFromBottom(View view){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bottomFragment, mBottomFragment.get())
                .commit();

        TranslateAnimation animate = new TranslateAnimation(0,0,mContainer.getHeight(),0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    private void slideToBottom(View view, int offset){
        getSupportFragmentManager().beginTransaction()
                .remove(mBottomFragment.get())
                .commit();
        TranslateAnimation animate = new TranslateAnimation(0,0,offset,mContainer.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    private void initFragment(){
        if(mBottomFragment==null||mBottomFragment.get()==null){
            mBottomFragment = new WeakReference<>(BottomFragment.newInstance());
        }
    }

    private void setBottomFragmentMargin(int offset){
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, offset, 0, 0);
        mBottomContainer.setLayoutParams(params);
    }
}
