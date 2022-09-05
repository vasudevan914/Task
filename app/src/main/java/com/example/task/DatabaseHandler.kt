package com.example.task

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.String
import kotlin.Any
import kotlin.Int
import kotlin.arrayOf


class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_PROFILE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_URL + "TEXT,"
                + KEY_DESC + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE)

        // Create tables again
        onCreate(db)
    }

    // code to add the new contact
    fun addContact(profileData: ProfileData) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, profileData.name) // Contact Name
        values.put(KEY_DESC, profileData.desc) // Contact Phone

        // Inserting Row
        db.insert(TABLE_PROFILE, null, values)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
    }

    // code to get the single contact
    fun getContact(id: Int): ProfileData {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(
            TABLE_PROFILE,
            arrayOf(
                KEY_ID,
                KEY_NAME, KEY_DESC
            ),
            KEY_ID + "=?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )
        if (cursor != null) cursor.moveToFirst()
        // return contact
        return ProfileData(
            cursor!!.getString(0).toInt(),
            cursor!!.getString(1), cursor!!.getString(2), cursor!!.getString(3)
        )
    }// Adding contact to list

    // return contact list
// Select All Query

    // looping through all rows and adding to list
    // code to get all contacts in a list view
    val allContacts: List<ProfileData>
        get() {
            val profileList: MutableList<ProfileData> = ArrayList<ProfileData>()
            // Select All Query
            val selectQuery = "SELECT  * FROM " + TABLE_PROFILE
            val db = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val profileData = ProfileData(
                    cursor.getString(0).toInt(),
                    cursor.getString(1), cursor.getString(2),cursor.getString(3))
                    // Adding contact to list
                    profileList.add(profileData)
                } while (cursor.moveToNext())
            }

            // return contact list
            return profileList
        }

    // code to update the single contact
    fun updateContact(profileData: ProfileData): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, profileData.name)
        values.put(KEY_DESC, profileData.desc)

        // updating row
        return db.update(
            TABLE_PROFILE,
            values,
            KEY_ID + " = ?",
            arrayOf(String.valueOf(profileData.id))
        )
    }

    // Deleting single contact
    fun deleteContact(profileData: ProfileData) {
        val db = this.writableDatabase
        db.delete(TABLE_PROFILE, KEY_ID + " = ?", arrayOf(String.valueOf(profileData.id)))
        db.close()
    }// return count

    // Getting contacts Count
    val contactsCount: Int
        get() {
            val countQuery = "SELECT  * FROM " + TABLE_PROFILE
            val db = this.readableDatabase
            val cursor: Cursor = db.rawQuery(countQuery, null)
            cursor.close()

            // return count
            return cursor.getCount()
        }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME ="profiledata"
        private const val TABLE_PROFILE = "profile"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_URL = "url"
        private const val KEY_DESC = "description"
    }
}