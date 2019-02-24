package com.anukul.preferencesettingsheader;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String settings = getArguments().getString("settings");

        if (settings.equals("general")){
            addPreferencesFromResource(R.xml.general_settings_preference);
        }else if(settings.equals("about")){
            addPreferencesFromResource(R.xml.about_app_setting_preference);
        }
    }
}
