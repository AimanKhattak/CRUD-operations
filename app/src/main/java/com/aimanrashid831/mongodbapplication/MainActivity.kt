package com.aimanrashid831.mongodbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.widget.Button
import org.bson.Document

class MainActivity : AppCompatActivity() {
    companion object{
        private const val APP_NAME = "application-0-cakkx"
    }
    lateinit var app: App
    var user: User
    var collection: MongoCollection<DocumentsContract.Document>

    init{
        Realm.init(this)
        var app = App(AppConfiguration.Builder(APP_NAME).build())
        user = app.currentUser()!!
        val mongoClient =
            user!!.getMongoClient("mongodb-atlas")

        val database = mongoClient.getDatabase("Mongodb")
        collection = database.getCollection("collection")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val createButton: Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         createButton = findViewById(R.id.createButton)
        createButton.setOnClickListener {
            collection.insertOne(DocumentsContract.Document().apply {
                put(name, email)
            })



        }
            collection.findOneAndUpdate(
                DocumentsContract.Document().apply{},
                DocumentsContract.Document().apply {
                put(name, email)
            }, )
            collection.findOneAndDelete(DocumentsContract.Document().apply{})

    }

}