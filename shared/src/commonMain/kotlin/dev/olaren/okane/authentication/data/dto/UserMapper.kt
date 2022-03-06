package dev.olaren.okane.authentication.data.dto

import dev.gitlive.firebase.auth.FirebaseUser

class UserMapper {
    fun map(firebaseUser: FirebaseUser): User {
        return User(
            userId = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email,
            photoUrl = firebaseUser.photoURL,
            isAnonymous = firebaseUser.isAnonymous,
        )
    }
}