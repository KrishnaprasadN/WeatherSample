package com.app.openweather.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {

    private static final String TAG = FragmentHelper.class.getSimpleName();

    public static void replaceContentFragment(FragmentActivity activity, final int containerId, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction().replace(containerId, fragment).commitAllowingStateLoss();
    }

    public static void replaceContentFragmentWithAnim(FragmentActivity activity, final int containerId, Fragment frgmt, int enterAnimId, int exitAnimId) {
        FragmentManager manager = activity.getSupportFragmentManager();

        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(enterAnimId, exitAnimId);
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction().replace(containerId, frgmt).commitAllowingStateLoss();
    }

    // this method will add fragment to backstack
    public static void replaceFragmentAndToBackStack(final FragmentActivity activity, final int containerId,
                                                     final Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment).addToBackStack(null)
                .commitAllowingStateLoss();
    }

    // this method will add fragment to backstack
    public static void addFragmentAndToBackStack(final FragmentActivity activity, final int containerId,
                                                 final Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().add(containerId, fragment).addToBackStack(null)
                .commitAllowingStateLoss();
    }

    // this method won't add fragment to backstack
    public static void replaceFragment(final FragmentActivity activity, final int containerId, final Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment)
                .commitAllowingStateLoss();
    }

    public static void popBackStackInclusive(FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            try {
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } catch (IllegalStateException e) {
                // ignore
                Logger.w(TAG, e);
            }
        }
    }

}
