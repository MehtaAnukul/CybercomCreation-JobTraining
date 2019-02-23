package com.anukul.preferencessettingdefaultvalue;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences_group_setting);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                if (key.equals(AppContact.PREF_SMS_LIMIT)){
                    Preference smsPref = findPreference(key);
                    smsPref.setSummary(sharedPreferences.getString(key,"")+" message per conversation");
                }

                if (key.equals(AppContact.PREF_MMS_LIMIT)){
                    Preference mmsPref = findPreference(key);
                    mmsPref.setSummary(sharedPreferences.getString(key,"")+" message per conversation");
                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference smsPref = findPreference(AppContact.PREF_SMS_LIMIT);
        smsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(AppContact.PREF_SMS_LIMIT,"")+" message per conversation");

        Preference mmsPref = findPreference(AppContact.PREF_MMS_LIMIT);
        mmsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(AppContact.PREF_MMS_LIMIT,"")+" message per conversation");
    }

    @Override
    public void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

    }
}
