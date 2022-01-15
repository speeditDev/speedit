import * as React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import HomeTabScreen from '../screens/bottomGnb/home/HomeTabScreen';
import RegisterTabScreen from '../screens/bottomGnb/register/RegisterTabScreen';
import AlertTabScreen from '../screens/bottomGnb/alert/AlertTabScreen';
import SearchTabStackNavigator from './SearchTabStackNavigator';
import MyPageTabStackNavigator from './MyPageTabStackNavigator';

// 하단 5개 탭
type BottomGnbStackParam = {
  HomeTabScreen: undefined;
  RegisterTabScreen: undefined;
  SearchTabStackScreen: undefined;
  MyPageTabStackScreen: undefined;
  AlertTabScreen: undefined;
};

const BottomGnbStack = createBottomTabNavigator<BottomGnbStackParam>();

const BottomGnbStackNavigator = () => {
  return (
    <BottomGnbStack.Navigator
      initialRouteName={'HomeTabScreen'}
      screenOptions={{
        headerShown: false,
        tabBarActiveTintColor: '#2a9d8f',
        tabBarInactiveTintColor: '#f4a261',
        tabBarLabelStyle: {fontSize: 14, fontWeight: '400'},
      }}>
      <BottomGnbStack.Screen name={'HomeTabScreen'} component={HomeTabScreen} options={{tabBarLabel: '홈'}} />
      <BottomGnbStack.Screen name={'RegisterTabScreen'} component={RegisterTabScreen} options={{tabBarLabel: '등록'}} />
      <BottomGnbStack.Screen
        name={'SearchTabStackScreen'}
        component={SearchTabStackNavigator}
        options={{tabBarLabel: '검색'}}
      />
      <BottomGnbStack.Screen
        name={'MyPageTabStackScreen'}
        component={MyPageTabStackNavigator}
        options={{tabBarLabel: '마이'}}
      />
      <BottomGnbStack.Screen name={'AlertTabScreen'} component={AlertTabScreen} options={{tabBarLabel: '알림'}} />
    </BottomGnbStack.Navigator>
  );
};

export default BottomGnbStackNavigator;
