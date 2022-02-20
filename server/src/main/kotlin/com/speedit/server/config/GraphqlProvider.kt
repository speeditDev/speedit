//package com.speedit.server.config
//
//import com.google.common.io.Resources
//import graphql.GraphQL
//import graphql.schema.GraphQLSchema
//import graphql.schema.idl.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.stereotype.Component
//import java.io.IOException
//import java.net.URL
//import javax.annotation.PostConstruct
//
//
//@Component
//class GraphqlProvider {
//
////    @Autowired
////    private lateinit var graphQLDataFetchers: graphql
//
//    private lateinit var graphQL: GraphQL
//
//    @Bean
//    public fun graphQL(): GraphQL? {
//        return graphQL
//    }
//
//    @PostConstruct
//    @Throws(IOException::class)
//    fun init() {
//        val url: URL = Resources.getResource("classpath:graphql/**.graphqls")
//        val sdl: String = Resources.toString(url, Charsets.UTF_8)
//        val graphQLSchema: GraphQLSchema = buildSchema(sdl)
//        graphQL = GraphQL.newGraphQL(graphQLSchema).build()
//    }
//
//    private fun buildSchema(sdl: String): GraphQLSchema {
//        val typeRegistry: TypeDefinitionRegistry = SchemaParser().parse(sdl)
//        val runtimeWiring = buildWiring(sdl)
//        val schemaGenerator = SchemaGenerator()
//        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
//    }
//
//    private fun buildWiring(sdl: String): RuntimeWiring {
//        return RuntimeWiring.newRuntimeWiring()
////            .type(
////                TypeRuntimeWiring.newTypeWiring("Query")
////                    .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher())
////            )
////            .type(
////                newTypeWiring("Book")
////                    .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher())
////            )
//            .build()
//    }
//}