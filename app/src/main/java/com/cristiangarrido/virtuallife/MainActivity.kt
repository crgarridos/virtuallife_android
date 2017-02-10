package com.cristiangarrido.virtuallife

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cristiangarrido.virtuallife.base.BaseActivity
import com.cristiangarrido.virtuallife.model.User
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")

        myRef.setValue(User(1, "misterharry"))

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(User::class.java)

                mainText.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

                mainText.text = "Failed to read value." + error.toException()
            }
        })

        goLoginActivityBtn.setOnClickListener {
            startActivity(Intent(this, GoogleSignInActivity::class.java))
        }
    }
}
