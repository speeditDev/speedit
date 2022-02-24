package com.speedit.server.graphql

import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class RootMutationResolver: GraphQLMutationResolver {
    fun checkHealth(message: String): String {
        return "$message is OK"
    }
}