package com.example.sendnotification

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var title=findViewById<EditText>(R.id.title)
        var descreiption=findViewById<EditText>(R.id.description)
        var  button=findViewById<Button>(R.id.sendNotification)



        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d("Krishan", it.toString())

            val user = hashMapOf(
                "token" to it.toString()
            )
            FirebaseFirestore.getInstance().collection("user")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }

        val tokenList: List<String> =
            mutableListOf(
                "dE2NkOZTRwiefbu5dcJhpD:APA91bFVOADayBJbMOoNbZF8lV4iUsRehucK4i-EM0CBWkv5Jo9y6HUcSUzWrerUNNPW-sLk-U5OWXmxaXeTnIPStpUHBtsTP97O_nSQ7raCndME2cpxPXhvxynOmZjhy5DTT9sBsBOW",
                "dE2NkOZTRwiefbu5dcJhpD:APA91bFVOADayBJbMOoNbZF8lV4iUsRehucK4i-EM0CBWkv5Jo9y6HUcSUzWrerUNNPW-sLk-U5OWXmxaXeTnIPStpUHBtsTP97O_nSQ7raCndME2cpxPXhvxynOmZjhy5DTT9sBsBOW",
                "e00qCaQwRo6R-cgbFo8DQ2:APA91bF1jrsgfTPG-JC4VgIr4NMlyvnxNlrrBYF8m712r1OTJWs9BP2IEPkCl6F-RGYrJC55As5WNDqpIJaLrItx1qnbWP0eX46DrDHhrCOOHxLP3oDqk0vxD1p7Yv1MNo4w-I1JoDKz",
                "dNMnW6gdR9u8plK_x0RkzN:APA91bEni9hHqXToAKUlV6yC7yIGf5zf8jmnuKEeqcuTapfVUBdyO5t5uBCV2VmC25wFPQ4uPawnVBQa3Yy5TpN_IAFtC8zL_3-kYtg-dK5kj9jC0p22q6xX5kx613PhyVZWiimVNU7T",
                "dgL2mTDUSpWqjQWNTEFIND:APA91bG93M8nGIw7FpwO5Y2m4GfNcLUOOui47kk360pr-Ua0YkG0hk8-ZKzBnPiKc_80gsPjpENKksSMW5nlTzDaxx2X5O0qXqkJ2iDy8qWOYwJNMmboHmWcKRk-xTEFzocBEZwFGVIs",
                "c5chV34CQ8ex4ZDjRzMn9H:APA91bG4-C6BAAnRcPcUT7psDjpcZSa1lEMiLLDyhT6drgI4tIPsn3p-EjjEWLP87IrqTT-o4IaO7OfUuvw6WmpLX6SRq0zUAKCCHbjuHrqOdHgKTVYdQT0eraHSr2z_lSVWJn-u2pya"
               )
        val headerMap =
            hashMapOf<String, String>("Authorization" to "key=AAAANK0c3iQ:APA91bHtglpO7LEOSyKTzvz248S9Sp6VB2N2Rlt5GbGHH4XC0jtDFdmOX1zIk3L8DeniW8FVcMrTo6FrTVqZl4-2pLcbdzXrxXM6QfKroN2kKzp2fKhEABlAdcMAjs2QLBCrXK_Bthhr")



        button.setOnClickListener{

            val notificationData = NotificationData(
                Notification(
                    "notoficationsss",
                    title.text.toString(),
                    true,
                    descreiption.text.toString()
                ), tokenList
            )
            ApiCalling.Create().send(headerMap, notificationData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val notification = it
                }

        }


    }
}