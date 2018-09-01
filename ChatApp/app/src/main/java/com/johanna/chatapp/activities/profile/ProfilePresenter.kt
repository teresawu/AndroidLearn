package com.johanna.chatapp.activities.profile

import com.google.firebase.database.*

class ProfilePresenter constructor(private val profileActivity: ProfileActivity) {
    private val userReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(profileActivity.userIdValue)
    }

    fun setUpProfile() {
        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val displayName = dataSnapshot.child("display_name").value.toString()
                val status = dataSnapshot.child("status").value.toString()
                val image = "https://api.adorable.io/avatars/260/$status.png"

                profileActivity.linkProfileDetails(displayName, status, image)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}