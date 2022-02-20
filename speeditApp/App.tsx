import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {ThemeProvider} from 'styled-components/native';
import {myTheme} from './myTheme';
import RootStackNavigator from './src/navigation/RootStackNavigator';
import {ApolloProvider} from '@apollo/client';
import {apolloClient} from './src/apollo/apolloClient';

const App = () => {
  return (
    <ThemeProvider theme={myTheme}>
      <ApolloProvider client={apolloClient}>
        <NavigationContainer>
          <RootStackNavigator />
        </NavigationContainer>
      </ApolloProvider>
    </ThemeProvider>
  );
};

export default App;
