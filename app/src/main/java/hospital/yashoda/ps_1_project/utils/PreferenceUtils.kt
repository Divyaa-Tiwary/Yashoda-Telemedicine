package hospital.yashoda.ps_1_project.utils

import android.preference.PreferenceManager
import hospital.yashoda.ps_1_project.YashodaApp
import hospital.yashoda.ps_1_project.pojos.Users

object PreferenceUtils {

    private val pm = PreferenceManager.getDefaultSharedPreferences(YashodaApp.instance)

    private const val NAME = "name"
    private const val EMAIL = "email"
    private const val PHONE = "phone"
    private const val AGE = "age"
    private const val GENDER = "gender"
    private const val STATE = "state"
    private const val COUNTRY = "country"
    private const val DOCTOR = "doctor"

    var name: String
        get() = pm.getString(
            NAME, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                NAME, value
            ).apply()
        }

    var email: String
        get() = pm.getString(
            EMAIL, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                EMAIL, value
            ).apply()
        }

    var phone: String
        get() = pm.getString(
            PHONE, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                PHONE, value
            ).apply()
        }

    var age: String
        get() = pm.getString(
            AGE, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                AGE, value
            ).apply()
        }

    var gender: String
        get() = pm.getString(
            GENDER, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                GENDER, value
            ).apply()
        }

    var state: String
        get() = pm.getString(
            STATE, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                STATE, value
            ).apply()
        }

    var country: String
        get() = pm.getString(
            COUNTRY, ""
        ) ?: ""
        set(value) {
            pm.edit().putString(
                COUNTRY, value
            ).apply()
        }

    var doctor: Boolean
        get() = pm.getBoolean(DOCTOR, false)
        set(value) {
            pm.edit().putBoolean(DOCTOR, value).apply()
        }

    fun setUser(users: Users) {
        val pm = PreferenceUtils
        pm.name = users.name
        pm.age = users.age
        pm.gender = users.gender
        pm.phone = users.phoneNumber
        pm.email = users.email
        pm.state = pm.state
        pm.country = pm.country
    }

    fun getUser(): Users {
        val pm = PreferenceUtils
        return Users(pm.name, pm.age, pm.gender, pm.phone, pm.email, pm.state, pm.country, "", "", "")
    }
}