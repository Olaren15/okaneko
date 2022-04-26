package app.okaneko.authentication.di

import app.okaneko.authentication.repository.AccessTokenSecretRepository
import app.okaneko.authentication.repository.RefreshTokenSecretRepository
import app.okaneko.authentication.repository.implementation.RedisAccessTokenSecretRepository
import app.okaneko.authentication.repository.implementation.RedisRefreshTokenRepository
import app.okaneko.authentication.repository.implementation.RedisRefreshTokenSecretRepository
import app.okaneko.authentication.use_case.*
import app.okaneko.authentication.use_case.implementation.*
import org.kodein.di.*

val authenticationServerModule = DI.Module("AuthenticationServer") {
    bind {
        provider {
            AuthenticationUseCases(instance())
        }
    }

    bind<GenerateAccessTokenAndRefreshToken> {
        provider {
            GenerateAccessTokenAndRefreshTokenImpl(
                generateAccessToken = instance(),
                generateRefreshToken = instance(),
                saveRefreshToken = instance(),
            )
        }
    }

    bind<GenerateAccessToken> {
        provider {
            GenerateAccessTokenImpl(
                getAccessTokenSecret = instance(),
                appConfig = instance()
            )
        }
    }

    bind<GenerateRefreshToken> {
        provider {
            GenerateRefreshTokenImpl(
                getRefreshTokenSecret = instance(),
                appConfig = instance()
            )
        }
    }

    bind<GetAccessTokenSecret> {
        provider {
            GetAccessTokenSecretSecretImpl(instance())
        }
    }

    bind<GetRefreshTokenSecret> {
        provider {
            GetRefreshTokenSecretImpl(instance())
        }
    }

    bind<SaveRefreshToken> {
        provider {
            SaveRefreshTokenImpl(instance())
        }
    }

    bind<AccessTokenSecretRepository> {
        singleton {
            RedisAccessTokenSecretRepository(instance())
        }
    }

    bind<RefreshTokenSecretRepository> {
        provider {
            RedisRefreshTokenSecretRepository(instance())
        }
    }

    bind {
        provider {
            { userId: String, refreshToken: String ->
                RedisRefreshTokenRepository(
                    client = instance(),
                    userId = userId,
                    refreshToken = refreshToken
                )
            }
        }
    }
}