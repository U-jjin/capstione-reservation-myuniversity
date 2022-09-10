package com.example.myuniversity3.InnerReservation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class InnerPagerAdapter extends FragmentStatePagerAdapter {

    private  static  int PAGE_NUMBER = 6;

    private  String dbdate;
    public InnerPagerAdapter(FragmentManager fm, String dbdate) {
        super(fm);
        this.dbdate =dbdate;
    }

    @Override
    public Fragment getItem(int i) {
            switch (i) {
                case 0:       Log.i("instanceso","soccer");
                    return SoccerFragment.newInstance(dbdate);
                case 1: Log.i("instanceba","bask");
                    return BasketballFragment.newInstance(dbdate);
                case 2: Log.i("instancevo","vol");
                    return VolleyballFragment.newInstance(dbdate);
                case 3: Log.i("dbdate11","man");
                    return ManFragment.newInstance(dbdate);
                case 4:Log.i("dbdate11","woman");
                    return WomanFragment.newInstance(dbdate);
                case 5:Log.i("dbdate11","reading");
                    return ReadingroomFragment.newInstance();
                default:
                    return null;
            }
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0: return "SOCCER";
            case 1: return "BASKETBALL";
            case 2: return  "FOOT VOLLEY";
            case 3: return  "LOUNGE MAN";
            case 4: return "LOUNGE WOMAN";
            case 5: return  "READING ROOM";
            default: return null;
        }
    }
}
