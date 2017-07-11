package info.androidhive.navigationdrawer.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.subfragment.BloggerFragment;
import info.androidhive.navigationdrawer.subfragment.MyFeedFragment;
import info.androidhive.navigationdrawer.subfragment.MyLocalFeedFragment;
import info.androidhive.navigationdrawer.subfragment.PhotosFragment;
import info.androidhive.navigationdrawer.subfragment.ReviewsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoresFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public TopStoresFragment() {
        // Required empty public constructor
    }

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    public static TopStoresFragment newInstance(String param1, String param2) {
        TopStoresFragment fragment = new TopStoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate tab layout and setup views

        View x = inflater.inflate(R.layout.fragment_top_stores, null);
                tabLayout = (TabLayout) x.findViewById(R.id.tabs);
                viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        // set and adapter for view pager
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ReviewsFragment();
                case 1:
                    return new PhotosFragment();
                case 2:
                    return new BloggerFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "REVIEWS";
                case 1:
                    return "PHOTOS";
                case 2:
                    return "BLOGGERS";
            }
            return null;

        }
    }
}
