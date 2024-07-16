package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    //declaration of the var
    lateinit var usernames: EditText;
    lateinit var password: EditText;
    lateinit var btn: Button;
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var firebaseDatabseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernames = findViewById(R.id.username)
        password = findViewById(R.id.pass)
        btn = findViewById(R.id.button)

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabseRef = firebaseDatabase.getReference().child("Users")
        val data=HashMap<String,String>()

        btn.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()

            val email: String = usernames.text.toString().trim()
            val pass: String = password.text.toString().trim()
            data.put("Email",email)
            data.put("Password",pass)
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
                Toast.makeText(this@MainActivity, "user register", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, " error", Toast.LENGTH_LONG).show()
            }
            firebaseDatabseRef.push().setValue(data).addOnSuccessListener {
                Toast.makeText(this@MainActivity,"data entered",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, "Error Occured", Toast.LENGTH_LONG).show()
            }
            

        }
    }
}
