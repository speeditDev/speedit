import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {ThemeProvider} from 'styled-components/native';
import {myTheme} from './myTheme';
import RootStackNavigator from './src/navigation/RootStackNavigator';

const App = () => {
  return (
    <ThemeProvider theme={myTheme}>
      <NavigationContainer>
        <RootStackNavigator />
      </NavigationContainer>
    </ThemeProvider>
  );
};

export default App;
