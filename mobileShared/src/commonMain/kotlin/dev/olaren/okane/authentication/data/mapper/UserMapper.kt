package dev.olaren.okane.authentication.data.mapper

import dev.gitlive.firebase.auth.FirebaseUser
import dev.olaren.okane.authentication.data.dto.User

class UserMapper {
    fun map(firebaseUser: FirebaseUser): User {
        return User(
            id = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email,
            photoUrl = firebaseUser.photoURL,
            isAnonymous = firebaseUser.isAnonymous,
        )
    }
}