package uca.desapmov.econic.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

import uca.desapmov.econic.BuildConfig;
import uca.desapmov.econic.models.*;

public class UserConfig {

    private static final String USER_PREF_NAME = "user_prefs";
    private static final String PREF_FIRST_TIME = "is_first_time";
    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_USER_EMAIL = "user_email";
    private static final String PREF_USER_PWD = "user_pwd";

    private final SharedPreferences mPrefs;

    public UserConfig(@NonNull Context context) {
        mPrefs = context.getSharedPreferences(getPrefsName(), Context.MODE_PRIVATE);
    }

    public boolean isFirstTime() {
        return mPrefs.getBoolean(PREF_FIRST_TIME, true);
    }

    public boolean setIsFirstTime(Boolean value) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(PREF_FIRST_TIME, value);

        prefsEditor.apply();
        return true;
    }

    public boolean userExists() {
        return mPrefs.contains(PREF_USER_NAME) && mPrefs.contains(PREF_USER_EMAIL);
    }

    @Nullable
    public UserModel getUser() {
        final String name = mPrefs.getString(PREF_USER_NAME, null);
        final String email = mPrefs.getString(PREF_USER_EMAIL, null);
        final String password = mPrefs.getString(PREF_USER_PWD, null);
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
            return null;
        }
        return new UserModel(name, email, password);
    }

    public boolean setUser(@Nullable UserModel user) {
        if(user == null) return false;
        if(TextUtils.isEmpty(user.getFullname()) || TextUtils.isEmpty(user.getEmail()) || TextUtils.isEmpty(user.getPassword()))
            return false;

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PREF_USER_NAME, user.getFullname());
        prefsEditor.putString(PREF_USER_EMAIL, user.getEmail());
        prefsEditor.putString(PREF_USER_PWD, user.getPassword());
        prefsEditor.apply();
        return true;
    }

    //When naming your shared preference files, you should use a name that's uniquely identifiable to your app
    private String getPrefsName() {
        return String.format(
                Locale.getDefault(),
                "%s_%s",
                BuildConfig.APPLICATION_ID , USER_PREF_NAME
        );
    }
}
