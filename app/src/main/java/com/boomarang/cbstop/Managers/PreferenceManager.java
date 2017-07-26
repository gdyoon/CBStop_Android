package com.boomarang.cbstop.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.boomarang.cbstop.Constants.PreferenceKey;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by SangHun on 2017-07-25.
 */

public class PreferenceManager {

    private static final String token = ";";

    private static PreferenceManager instance;
    private static SharedPreferences preference;
    private static SharedPreferences.Editor editor;

    private PreferenceManager() {}

    public static PreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceManager();
            preference = context.getSharedPreferences(PreferenceKey.PREF_SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public SharedPreferences getSharedPreference() {
        return preference;
    }

    public void reset() {
        beginWrite()
//                .writeString(PreferenceKey.PREF_USER_PHOTO_STRING, null)
//                .writeBoolean(PreferenceKey.PREF_APP_FIRST_OPEN_BOOL, true)
//                .writeString(PreferenceKey.PREF_DRIVE_FOLDER_ID_STRING, null)
//                .writeString(PreferenceKey.PREF_REGISTER_TOKEN_STRING, null)
//                .writeInt(PreferenceKey.PREF_USER_UID_INT, -1)
//                .writeString(PreferenceKey.PREF_LOCAL_FOLDER_PATH_STRING, null)
//                .writeString(PreferenceKey.PREF_CARE_LOCATION_LATLNG, null)
//                .writeString(PreferenceKey.PREF_CARE_LOCATION_STRING, null)
//                .writeString(PreferenceKey.PREF_USER_FID_STRING, null)
//                .writeString(PreferenceKey.PREF_USER_EMAIL_STRING, null)
//                .writeString(PreferenceKey.PREF_USER_NAME_STRING, null)
//                .writeString(PreferenceKey.PREF_USER_PHONE_STRING, null)
//                .writeBoolean(PreferenceKey.PREF_APP_LOCK_BOOL, false)
//                .writeString(PreferenceKey.PREF_APP_LOCK_PIN_STRING, null)
//                .writeBoolean(PreferenceKey.PREF_FAKE_CALL_BOOL, false)
//                .writeBoolean(PreferenceKey.PREF_SOS_SIREN_BOOL, false)
//                .writeBoolean(PreferenceKey.PREF_MODE_LOCK_BOOL, false)
//                .writeBoolean(PreferenceKey.PREF_VOICE_RECORD_START_BOOL, false)
//                .writeString(PreferenceKey.PREF_BLE_DEVICE_ADDRESS, null)
//                .writeString(PreferenceKey.PREF_EMERGENCY_STATE_STRING, null)
//                .writeInt(PreferenceKey.PREF_BLE_DEVICE_BATTERY, -1)
                .apply();
    }

    /****** READ ******/
    public boolean readBoolean(String prefKey) {
        return preference.getBoolean(prefKey, false);
    }

    public boolean readBoolean(String prefKey, boolean defValue) {
        return preference.getBoolean(prefKey, defValue);
    }

    public float readFloat(String prefKey) {
        return preference.getFloat(prefKey, 0.0f);
    }

    public float readFloat(String prefKey, float defValue) {
        return preference.getFloat(prefKey, defValue);
    }

    public int readInt(String prefKey) {
        return preference.getInt(prefKey, 0);
    }

    public int readInt(String prefKey, int defValue) {
        return preference.getInt(prefKey, defValue);
    }

    public long readLong(String prefKey) {
        return preference.getLong(prefKey, 0L);
    }

    public long readLong(String prefKey, long defValue) {
        return preference.getLong(prefKey, defValue);
    }

    public String readString(String prefKey) {
        return preference.getString(prefKey, null);
    }

    public String readString(String prefKey, @Nullable String defValue) {
        return preference.getString(prefKey, defValue);
    }

    public LatLng readLatLng(String prefKey) {
        String val = preference.getString(prefKey, null);
        if (val == null) return null;
        String[] split = val.split(token);
        return new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
    }

    /****** WRITE ******/
    /****** You Must Use begin & apply When You Try WRITE Functions *******/
    public PreferenceManager beginWrite() {
        editor = preference.edit();
        return instance;
    }

    public PreferenceManager apply() {
        editor.apply();
        return instance;
    }

    public PreferenceManager writeBoolean(String prefKey, boolean value) {
        editor.putBoolean(prefKey, value);
        return instance;
    }

    public PreferenceManager writeFloat(String prefKey, float value) {
        editor.putFloat(prefKey, value);
        return instance;
    }

    public PreferenceManager writeInt(String prefKey, int value) {
        editor.putInt(prefKey, value);
        return instance;
    }

    public PreferenceManager writeLong(String prefKey, long value) {
        editor.putLong(prefKey, value);
        return instance;
    }

    public PreferenceManager writeString(String prefKey, String value) {
        editor.putString(prefKey, value);
        return instance;
    }

    public PreferenceManager writeLatLng(String prefKey, LatLng latLng) {
        String val = latLng.latitude + token + latLng.longitude;
        editor.putString(prefKey, val);
        return instance;
    }
}
