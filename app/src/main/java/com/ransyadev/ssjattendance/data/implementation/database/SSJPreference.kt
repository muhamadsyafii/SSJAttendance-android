package com.ransyadev.ssjattendance.data.implementation.database

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ransyadev.ssjattendance.SSJApp
import com.ransyadev.ssjattendance.utils.KEY_ACCESS_TOKEN
import com.ransyadev.ssjattendance.utils.KEY_DATA_USER
import com.ransyadev.ssjattendance.utils.KEY_EDIT_DATA_USER
import com.ransyadev.ssjattendance.utils.KEY_FCM_TOKEN
import com.ransyadev.ssjattendance.utils.KEY_IS_FIRST_INSTALL
import com.ransyadev.ssjattendance.utils.KEY_IS_LOGIN
import com.ransyadev.ssjattendance.utils.KEY_USER
import com.ransyadev.ssjattendance.utils.KEY_USER_ROLE
import com.ransyadev.ssjattendance.utils.PREF_NAME

object SSJPreference {
    private val mSharedPreferences: SharedPreferences =
        SSJApp.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = mSharedPreferences.edit()

    /**
     * Method to store `String` value corresponding to a key
     *
     * @param key   - Key
     * @param value - String value to be stored
     */
    fun saveString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }


    /**
     * Method to store `String` value corresponding to a key
     *
     * @param key   - Key
     * @param value - String value to be stored
     */
    fun saveStringFromObject(key: String?, value: Any?) {
        editor.putString(key, Gson().toJson(value))
        editor.apply()
    }


    /**
     * Method to store `int` value corresponding to a key
     *
     * @param key   - Key
     * @param value - int value to be stored
     */
    fun saveInt(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Method to store `long` value corresponding to a key
     *
     * @param key   - Key
     * @param value - long value to be stored
     */
    fun saveLong(key: String?, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Method to store `boolean` value corresponding to a key
     *
     * @param key   - Key
     * @param value - boolean value to be stored
     */
    fun saveBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * Method to store `float` value corresponding to a key
     *
     * @param key   - Key
     * @param value - Float value to be stored
     */
    fun saveFloat(key: String?, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * Method to clear all the shared preferences stored, this will be helpful in
     * scenarios like logout
     */
    fun clear() {
        editor.clear()
        editor.apply()
    }

    /**
     * Method to with `String` value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns null if not found
     */
    fun getString(key: String?): String? {
        return mSharedPreferences.getString(key, "")
    }

    /**
     * Method to with `long` value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    fun getLong(key: String?): Long {
        return mSharedPreferences.getLong(key, 0L)
    }

    /**
     * Method to with `int` value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    fun getInt(key: String?): Int {
        return mSharedPreferences.getInt(key, 0)
    }

    /**
     * Method to with `float` value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns 0 if not found
     */
    fun getFloat(key: String?): Float {
        return mSharedPreferences.getFloat(key, 0.0f)
    }

    /**
     * Method to with `boolean` value from preferences for a given Key
     *
     * @param key - Key to fetch the value
     * @return - Actual value, returns false if not found
     */
    fun getBoolean(key: String?): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }

    fun setLoginStatus(status: Boolean) {
        editor.putBoolean(KEY_IS_LOGIN, status)
        if (!status) {
            editor.putString(KEY_USER, "")
        }
        editor.apply()
    }


    var accessToken: String
        get() {
            return mSharedPreferences.getString(KEY_ACCESS_TOKEN, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_ACCESS_TOKEN, value)
            editor.apply()
        }

    var userRole: Int
        get() {
            return mSharedPreferences.getInt(KEY_USER_ROLE, 0)
        }
        set(value) {
            editor.putInt(KEY_USER_ROLE, value)
            editor.apply()
        }

    var dataUser: String
        get() {
            return mSharedPreferences.getString(KEY_DATA_USER, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_DATA_USER, value)
            editor.apply()
        }

    var editDataUser: String
        get() {
            return mSharedPreferences.getString(KEY_EDIT_DATA_USER, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_EDIT_DATA_USER, value)
            editor.apply()
        }

    var tokenFCM: String
        get() {
            return mSharedPreferences.getString(KEY_FCM_TOKEN, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_FCM_TOKEN, value)
            editor.apply()
        }

    var isFirstInstall: Boolean
        get() {
            return mSharedPreferences.getBoolean(KEY_IS_FIRST_INSTALL, false)
        }
        set(value) {
            editor.putBoolean(KEY_IS_FIRST_INSTALL, value)
            editor.apply()
        }

    var isLogin: Boolean
        get() {
            return mSharedPreferences.getBoolean(KEY_IS_LOGIN, false)
        }
        set(value) {
            editor.putBoolean(KEY_IS_LOGIN, value)
            editor.apply()
        }
}
