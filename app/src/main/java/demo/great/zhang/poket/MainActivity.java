package demo.great.zhang.poket;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import demo.great.zhang.poket.base.BaseActivity;
import demo.great.zhang.poket.base.BaseFragment;
import demo.great.zhang.poket.eventmsg.Meberid;
import demo.great.zhang.poket.fragment.FragmentNew;
import demo.great.zhang.poket.fragment.FragmentPersonal;
import demo.great.zhang.poket.fragment.PoketFragment;
import demo.great.zhang.poket.net.URLConst;
import okhttp3.Call;

public class MainActivity extends BaseActivity {



    private FragmentNew fragmentSecon;
    private FragmentPersonal fragmentThrid;
    private PoketFragment fragmentfirst;
    private BaseFragment[] fragments;

    private int lastfragment;

    @BindView(R.id.mainview)
    LinearLayout mainview;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {
        initFragment();
    }



    private void initFragment(){
        fragmentfirst = new PoketFragment();
        fragmentSecon = new FragmentNew();
        fragmentThrid = new FragmentPersonal();
        Bundle bundle = new Bundle();
        bundle.putString("meberid",getIntent().getStringExtra("meberid"));
        fragmentfirst.setArguments(bundle);
        fragments = new BaseFragment[]{fragmentfirst, fragmentSecon, fragmentThrid};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview, fragmentfirst).show(fragmentfirst).commit();
        navigation.setOnNavigationItemSelectedListener(changeFragment);
        adjustNavigationIcoSize(navigation);
    }

    private void adjustNavigationIcoSize(BottomNavigationView navigation){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;

                    }
                    return true;
                }
                case R.id.navigation_subtype: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;

                    }
                    return true;
                }
                case R.id.navigation_search: {
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;

                    }
                    return true;
                }
            }
            return false;
        }
    };

    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.mainview, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

    }

}
