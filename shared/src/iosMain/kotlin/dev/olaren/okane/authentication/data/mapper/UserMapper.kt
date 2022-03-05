package dev.olaren.okane.authentication.data.mapper

import cocoapods.FirebaseAuth.FIRUser
import dev.olaren.okane.authentication.data.dto.User

class UserMapper {
    fun map(firebaseUser: FIRUser): User {
        return User(
            userId = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email,
            photoUrl = firebaseUser.photoURL.toString(),
            isAnonymous = firebaseUser.anonymous
        )
    }
}