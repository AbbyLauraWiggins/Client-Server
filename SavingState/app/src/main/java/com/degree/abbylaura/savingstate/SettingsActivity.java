package com.degree.abbylaura.savingstate;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

/**
 * Created by abbylaura on 15/02/2018.
 */

public class SettingsActivity extends PreferenceActivity{

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);



    }
}
