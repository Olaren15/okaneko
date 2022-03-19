package app.okaneko.plugin

//fun Application.configureAuthentication() {
//    val audience = EnvConfig.getString("jwt.audience")
//    val issuer = EnvConfig.getString("jwt.issuer")
//    val jwkUrl = EnvConfig.getString("jwt.jwkUrl")
//
//    val jwkProvider = JwkProviderBuilder(URL(jwkUrl))
//        .cached(10, 5, TimeUnit.HOURS)
//        .rateLimited(10, 1, TimeUnit.MINUTES)
//        .build()
//
//    install(Authentication) {
//        jwt {
//            verifier(jwkProvider, issuer) {
//                acceptLeeway(3)
//                withAudience(audience)
//                withClaimPresence(PublicClaims.SUBJECT)
//            }
//
//            validate { jwtCredential ->
//                if (jwtCredential.payload.subject.isBlank()) {
//                    null
//                } else {
//                    JWTPrincipal(jwtCredential.payload)
//                }
//            }
//        }
//    }
//}