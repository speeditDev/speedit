import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import SearchDetailScreen from '../screens/bottomGnb/search/SearchDetailScreen';
import SearchTabScreen from '../screens/bottomGnb/search/SearchTabScreen';
import {RouteProp} from '@react-navigation/native';
import {Platform} from 'react-native';

type SearchTabStackParam = {
  SearchTabScreen: undefined;
  SearchDetailScreen: undefined;
};

const SearchTabStack = createNativeStackNavigator<SearchTabStackParam>();

export interface SearchTabStackNavigationProps<RouteName extends keyof SearchTabStackParam> {
  navigation: NativeStackNavigationProp<SearchTabStackParam, RouteName>;
  route: RouteProp<SearchTabStackParam, RouteName>;
}

const SearchTabStackNavigator = () => (
  <SearchTabStack.Navigator
    screenOptions={{
      presentation: 'modal',
      headerShown: Platform.OS === 'android',
      animation: Platform.OS === 'android' ? 'slide_from_right' : undefined,
    }}>
    <SearchTabStack.Screen name={'SearchTabScreen'} component={SearchTabScreen} options={{headerShown: false}} />
    <SearchTabStack.Screen name={'SearchDetailScreen'} component={SearchDetailScreen} />
  </SearchTabStack.Navigator>
);

export default SearchTabStackNavigator;
