import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import {RouteProp} from '@react-navigation/native';
import MyPageTabScreen from '../screens/bottomGnb/myPage/MyPageTabScreen';
import SettingScreen from '../screens/setting/SettingScreen';
import {Platform} from 'react-native';

type MyPageTabStackParams = {
  MyPageTabScreen: undefined;
  SettingScreen: undefined;
};

const MyPageTabStack = createNativeStackNavigator<MyPageTabStackParams>();

export interface MyPageStackNavigationProps<RouteName extends keyof MyPageTabStackParams> {
  navigation: NativeStackNavigationProp<MyPageTabStackParams, RouteName>;
  route: RouteProp<MyPageTabStackParams, RouteName>;
}

const MyPageTabStackNavigator = () => {
  return (
    <MyPageTabStack.Navigator
      screenOptions={{
        presentation: 'modal',
        headerShown: Platform.OS === 'android',
        animation: Platform.OS === 'android' ? 'slide_from_right' : undefined,
      }}>
      <MyPageTabStack.Screen name={'MyPageTabScreen'} component={MyPageTabScreen} options={{headerShown: false}} />
      <MyPageTabStack.Screen name={'SettingScreen'} component={SettingScreen} />
    </MyPageTabStack.Navigator>
  );
};

export default MyPageTabStackNavigator;
