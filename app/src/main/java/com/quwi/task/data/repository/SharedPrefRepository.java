package com.quwi.task.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SharedPrefRepository {

    private static final String TAG = "SharedPrefRepository";

    public enum PrefKeys {
        TOKEN,
    }

    private static final String ENCRYPTED_PREFS_NAME = "encrypted_prefs_name";

    private SharedPreferences mSharedPreferences;

    public SharedPrefRepository(Context context) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            mSharedPreferences = EncryptedSharedPreferences.create(
                    ENCRYPTED_PREFS_NAME,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (IOException | GeneralSecurityException e) {
            Log.e(TAG, "Can't init EncryptedSharedPreferences");
        }
    }

    public void add(PrefKeys key, String val) {
        add(key.name(), val);
    }

    public void add(String key, String val) {
        SharedPreferences.Editor sharedPrefEditor = mSharedPreferences.edit();
        sharedPrefEditor.putString(key, val);
        sharedPrefEditor.apply();
    }

    public String get(PrefKeys key, String defaultValue) {
        return get(key.name(), defaultValue);
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void clear() {
        SharedPreferences.Editor sharedPrefEditor = mSharedPreferences.edit();
        for (PrefKeys key : PrefKeys.values()) {
            sharedPrefEditor.remove(key.name());
        }
        sharedPrefEditor.apply();
    }
}
