import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {Text, View} from 'react-native';
import styled, {ThemeProvider} from 'styled-components/native';
import {myTheme} from './myTheme';

const Title = styled.Text`
  font-size: 16px;
  color: ${props => props.theme.primaryColor};
`;

const App = () => {
  return (
    <ThemeProvider theme={myTheme}>
      <NavigationContainer>
        <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
          <Text>speedit</Text>
          <Title>speedit</Title>
        </View>
      </NavigationContainer>
    </ThemeProvider>
  );
};

export default App;
