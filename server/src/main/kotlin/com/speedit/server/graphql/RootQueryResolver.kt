package com.speedit.server.graphql

import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class RootQueryResolver: GraphQLQueryResolver {
    fun ping(): String {
        return "pong"
    }

}