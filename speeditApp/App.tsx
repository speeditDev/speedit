import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {Text, View} from 'react-native';

const App = () => {
  return (
    <NavigationContainer>
      <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
        <Text>speedit</Text>
      </View>
    </NavigationContainer>
  );
};

export default App;
