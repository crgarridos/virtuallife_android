package com.cristiangarrido.virtuallife

import android.os.Bundle
import com.cristiangarrido.virtuallife.base.BaseActivity
import com.cristiangarrido.virtuallife.tutorial.GithubRepo
import com.cristiangarrido.virtuallife.tutorial.GithubService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class MainActivity : BaseActivity() {


//    lateinit var d : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("users")
//
//        myRef.setValue(User(1, "misterharry"))
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(User::class.java)
//
//                mainText.text = value.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//
//                mainText.text = "Failed to read value." + error.toException()
//            }
//        })
//
//        goLoginActivityBtn.setOnClickListener {
//            startActivity(Intent(this, GoogleSignInActivity::class.java))
//        }

        lalalale()
    }

    private fun lalalale() {
        toast("Hello world !")

        val service = VirtualLifeApplication.get(this).retrofit

        val github = service.create(GithubService::class.java)

        github.getUserRepos("crgarridos").enqueue(object : Callback<List<GithubRepo>>{
            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Timber.w(t)
                toast(t.toString())
            }

            override fun onResponse(call: Call<List<GithubRepo>>,
                                    response: Response<List<GithubRepo>>) {
                Timber.i(response.body().joinToString("\n"))
                toast(response.body().toString())
            }

        })
    }
}
