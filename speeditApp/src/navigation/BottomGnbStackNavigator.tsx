import * as React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import HomeTabScreen from '../screens/bottomGnb/home/HomeTabScreen';
import RegisterTabScreen from '../screens/bottomGnb/register/RegisterTabScreen';
import AlertTabScreen from '../screens/bottomGnb/alert/AlertTabScreen';
import SearchTabStackNavigator from './SearchTabStackNavigator';
import MyPageTabStackNavigator from './MyPageTabStackNavigator';
import {COLORS} from '../styles/colors';
import {Image, View} from 'react-native';
import {spacing} from '../styles/spacing';

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
        tabBarStyle: {backgroundColor: COLORS.trueBlack, borderTopWidth: 1, borderTopColor: COLORS.grey7},
        tabBarActiveTintColor: COLORS.primaryWhite,
        tabBarInactiveTintColor: COLORS.grey1,
        tabBarIconStyle: {marginBottom: -spacing.xxs},
      }}>
      <BottomGnbStack.Screen
        name={'HomeTabScreen'}
        component={HomeTabScreen}
        options={{
          tabBarLabel: () => null,
          tabBarIcon: () => <Image source={require('../images/icon_home.png')} style={{width: 24, height: 24}} />,
        }}
      />
      <BottomGnbStack.Screen
        name={'RegisterTabScreen'}
        component={RegisterTabScreen}
        options={{
          tabBarLabel: () => null,
          tabBarIcon: () => <Image source={require('../images/icon_search.png')} style={{width: 24, height: 24}} />,
        }}
      />
      <BottomGnbStack.Screen
        name={'SearchTabStackScreen'}
        component={SearchTabStackNavigator}
        options={{
          tabBarLabel: () => null,
          tabBarIcon: () => (
            <Image source={require('../images/icon_create_feed.png')} style={{width: 36, height: 36}} />
          ),
        }}
      />
      <BottomGnbStack.Screen
        name={'MyPageTabStackScreen'}
        component={MyPageTabStackNavigator}
        options={{
          tabBarLabel: () => null,
          tabBarIcon: () => <Image source={require('../images/icon_noti.png')} style={{width: 24, height: 24}} />,
        }}
      />
      <BottomGnbStack.Screen
        name={'AlertTabScreen'}
        component={AlertTabScreen}
        options={{
          tabBarLabel: () => null,
          tabBarIcon: () => <View style={{width: 28, height: 28, borderRadius: 14, backgroundColor: COLORS.grey1}} />,
        }}
      />
    </BottomGnbStack.Navigator>
  );
};

export default BottomGnbStackNavigator;
