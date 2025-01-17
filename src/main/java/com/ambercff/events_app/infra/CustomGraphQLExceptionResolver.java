package com.ambercff.events_app.infra;

import com.ambercff.events_app.infra.exceptions.EventNotFoundException;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class CustomGraphQLExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        if(exception instanceof EventNotFoundException ex){
            return Mono.just(List.of(
                    GraphqlErrorBuilder.newError(environment)
                            .message(ex.getMessage())
                            .errorType(ErrorType.DataFetchingException)
                            .extensions(Map.of("code", ex.getCode())).build()

            ));
        }
        return Mono.empty();
    }
}
