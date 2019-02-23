package com.anukul.preferencessettingstest;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addPreferencesFromResource(R.xml.preferences);
        //addPreferencesFromResource(R.xml.preferences_group_setting);
        addPreferencesFromResource(R.xml.preferences_subscreen);
    }
}
