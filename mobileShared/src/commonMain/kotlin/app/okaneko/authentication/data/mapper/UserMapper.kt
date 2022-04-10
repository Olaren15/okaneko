package app.okaneko.authentication.data.mapper

import app.okaneko.user.data.dto.User
import dev.gitlive.firebase.auth.FirebaseUser

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