package com.app.openweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {
	private static final String SHARED_PREFS = "App_Shared_Pref";
	private static SharedPreferences mSharedPrefs;
	private static PreferenceManager sInstance;
	private static Editor mEditor;
	
	private PreferenceManager(Context context) {
		mSharedPrefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
		mEditor = mSharedPrefs.edit();
	}

	/**
	 * Returns singleton synchronized instance of PreferenceManager
	 * @param Context
	 * @return Instance of PreferenceManager
	 */
	public synchronized static PreferenceManager getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new PreferenceManager(context);
		}

		return sInstance;
	}

	/**
	 * Returns byte value for given preference key
	 * @param Preference key
	 * @return byte
	 */
	public byte getByte(String key, int defValue) {
		return (byte)mSharedPrefs.getInt(key, defValue);
	}
	
	/**
	 * Adds byte-value to Preference
	 * @param Preference key
	 * @param byte value to store
	 */
	public void setByte(String key, byte value) {
		mEditor.putInt(key, value);
		mEditor.commit();
	}
	
	/**
	 * Returns boolean value for given preference key
	 * @param Preference key
	 * @return boolean
	 */
	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPrefs.getBoolean(key, defValue);
	}
	
	/**
	 * Adds boolean-value to Preference
	 * @param Preference key
	 * @param boolean value to store
	 */
	public void setBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value);
		mEditor.commit();
	}
		
	/**
	 * Returns integer value for given preference key
	 * @param Preference key
	 * @return int
	 */
	public int getInt(String key, int defValue) {
		return mSharedPrefs.getInt(key, defValue);
	}
	
	/**
	 * Adds integer to Preference
	 * @param Preference key
	 * @param int value
	 */
	public void setInt(String key, int value) {
		mEditor.putInt(key, value);
		mEditor.commit();
	}
	
	/**
	 * Returns long value for given preference key
	 * @param Preference key
	 * @return long value
	 */
	public long getLong(String key, long defValue) {
		return mSharedPrefs.getLong(key, defValue);
	}
	
	/**
	 * Adds long-value to Preference
	 * @param Preference key
	 * @param long value
	 */
	public void setLong(String key, long value) {
		mEditor.putLong(key, value);
		mEditor.commit();
	}
	
	/**
	 * Returns string value for given preference key
	 * @param Preference key
	 * @return string
	 */
	public String getString(String key, String defString) {
		return mSharedPrefs.getString(key, defString);
	}
		
	/**
	 * Adds string-value to Preference
	 * @param Preference key
	 * @param string value
	 */
	public void setString(String key, String value) {
		mEditor.putString(key, value);
		mEditor.commit();
	}
	
}
