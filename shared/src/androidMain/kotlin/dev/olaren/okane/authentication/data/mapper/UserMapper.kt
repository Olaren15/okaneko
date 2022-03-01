package dev.olaren.okane.authentication.data.mapper

import com.google.firebase.auth.FirebaseUser
import dev.olaren.okane.authentication.data.dto.User

class UserMapper {
    fun map(firebaseUser: FirebaseUser): User {
        return User(
            userId = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email,
            photoUrl = firebaseUser.photoUrl.toString(),
            isAnonymous = firebaseUser.isAnonymous
        )
    }
}