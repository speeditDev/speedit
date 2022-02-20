import {ApolloClient, ApolloLink, concat, HttpLink, InMemoryCache} from '@apollo/client';
import {storeGetData} from '../util/storage/storeUtil';

const BAST_URL = 'https://sample.com'; // todo : update

const httpLink = new HttpLink({uri: '/graphql'});

const authMiddleware = new ApolloLink((operation, forward) => {
  // add the authorization to the headers
  const accessToken = async () => await storeGetData('@ACCESS_TOKEN');
  operation.setContext(({headers = {}}) => ({
    headers: {
      ...headers,
      authorization: accessToken ? `Bearer ${accessToken}` : '',
    },
  }));

  return forward(operation);
});

export const apolloClient = new ApolloClient({
  cache: new InMemoryCache(),
  link: concat(authMiddleware, httpLink),
});
