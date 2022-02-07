import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import SearchDetailScreen from '../screens/bottomGnb/search/SearchDetailScreen';
import SearchTabScreen from '../screens/bottomGnb/search/SearchTabScreen';
import {RouteProp} from '@react-navigation/native';
import {HeaderBack} from '../components/HeaderControl';
import {COLORS} from '../styles/colors';
import {Category} from '../components/FixedText';

type SearchTabStackParam = {
  SearchTabScreen: undefined;
  SearchDetailScreen: {search: string};
};

const SearchTabStack = createNativeStackNavigator<SearchTabStackParam>();

export interface SearchTabStackNavigationProps<RouteName extends keyof SearchTabStackParam> {
  navigation: NativeStackNavigationProp<SearchTabStackParam, RouteName>;
  route: RouteProp<SearchTabStackParam, RouteName>;
}

// Navigator 의 상세 옵션에서 안드로이드와 아이폰이 동일한 헤더를 가질 수 있도록 설정한다.
// 각 스크린 하위에서 동일 속성을 사용하면, 우선순위가 더 높게 덮어씌여진다.
// (예, Navgiator 스크린옵션에서 background 값을 검은색으로 해도, 하위 스크린에서 다른색으로 덮어쓸수 있다 )

const SearchTabStackNavigator = () => (
  <SearchTabStack.Navigator
    screenOptions={{
      presentation: 'card',
      headerShown: true,
      animation: 'slide_from_right',
      headerStyle: {backgroundColor: COLORS.black},
      // headerBackTitle: '',
      headerBackVisible: false, // for android
      headerBackTitleVisible: false,
      headerTitleAlign: 'center',
      headerShadowVisible: false, // 헤더 구분선
    }}>
    <SearchTabStack.Screen name={'SearchTabScreen'} component={SearchTabScreen} options={{headerTitle: ''}} />
    <SearchTabStack.Screen
      name={'SearchDetailScreen'}
      component={SearchDetailScreen}
      options={({navigation}) => {
        return {
          headerLeft: () => <HeaderBack navigate={navigation.goBack} />,
          headerTitle: () => <Category>{'도서 검색 결과'}</Category>,
        };
      }}
    />
  </SearchTabStack.Navigator>
);

export default SearchTabStackNavigator;
