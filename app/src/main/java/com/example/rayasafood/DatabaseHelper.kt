package com.example.rayasafood

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Contact(val id: Int, val name: String, val phone: String)

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val phone: String
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "rayasafood.db"
        private const val DATABASE_VERSION = 1

        // Tabel Kontak
        private const val TABLE_CONTACTS = "contacts"
        private const val COLUMN_CONTACT_ID = "id"
        private const val COLUMN_CONTACT_NAME = "name"
        private const val COLUMN_CONTACT_PHONE = "phone"

        // Tabel Profile
        private const val TABLE_PROFILE = "profile"
        private const val COLUMN_PROFILE_ID = "id"
        private const val COLUMN_PROFILE_NAME = "name"
        private const val COLUMN_PROFILE_EMAIL = "email"
        private const val COLUMN_PROFILE_ADDRESS = "address"
        private const val COLUMN_PROFILE_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Membuat tabel kontak
        val createContactsTable = """
            CREATE TABLE $TABLE_CONTACTS (
                $COLUMN_CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CONTACT_NAME TEXT NOT NULL,
                $COLUMN_CONTACT_PHONE TEXT NOT NULL
            )
        """
        db?.execSQL(createContactsTable)

        // Membuat tabel profil
        val createProfileTable = """
            CREATE TABLE $TABLE_PROFILE (
                $COLUMN_PROFILE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PROFILE_NAME TEXT NOT NULL,
                $COLUMN_PROFILE_EMAIL TEXT NOT NULL,
                $COLUMN_PROFILE_ADDRESS TEXT NOT NULL,
                $COLUMN_PROFILE_PHONE TEXT NOT NULL
            )
        """
        db?.execSQL(createProfileTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PROFILE")
        onCreate(db)
    }

    // Menambah kontak
    fun addContact(name: String, phone: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_CONTACT_NAME, name)
        values.put(COLUMN_CONTACT_PHONE, phone)
        return db.insert(TABLE_CONTACTS, null, values)
    }

    // Menambah profil
    fun saveProfile(name: String, email: String, address: String, phone: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PROFILE_NAME, name)
        values.put(COLUMN_PROFILE_EMAIL, email)
        values.put(COLUMN_PROFILE_ADDRESS, address)
        values.put(COLUMN_PROFILE_PHONE, phone)
        return db.insert(TABLE_PROFILE, null, values)
    }

    // Mendapatkan semua kontak
    fun getAllContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val db = readableDatabase
        val cursor = db.query(TABLE_CONTACTS, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_NAME))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_PHONE))
                contacts.add(Contact(id, name, phone))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contacts
    }

    // Mengambil profil
    fun getProfile(): Profile? {
        val db = readableDatabase
        val cursor = db.query(TABLE_PROFILE, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_NAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_EMAIL))
            val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_ADDRESS))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_PHONE))
            cursor.close()
            return Profile(id, name, email, address, phone)
        }

        cursor.close()
        return null
    }
}
