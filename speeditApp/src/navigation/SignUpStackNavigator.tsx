import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import {RouteProp} from '@react-navigation/native';
import {Platform} from 'react-native';
import {HeaderBack} from '../components/HeaderControl';
import {Category} from '../components/FixedText';
import {COLORS} from '../styles/colors';
import SnsLoginScreen from '../screens/singUp/SnsLoginScreen';
import SignNicknameScreen from '../screens/singUp/SignNicknameScreen';
import SignBirthScreen from '../screens/singUp/SignBirthScreen';
import SignJobScreen from '../screens/singUp/SignJobScreen';

type SignUpStackParam = {
  SnsLoginScreen: undefined;
  SignNicknameScreen: {loginName: string};
  SignBirthScreen: {nickname: string};
  SignJobScreen: {userName: string; userYear: string; userGender: string};
};

const SignUpStack = createNativeStackNavigator<SignUpStackParam>();

export interface SignUpStackNavigationProps<RouteName extends keyof SignUpStackParam> {
  navigation: NativeStackNavigationProp<SignUpStackParam, RouteName>;
  route: RouteProp<SignUpStackParam, RouteName>;
}

export type SignUpStackNavigationType = NativeStackNavigationProp<SignUpStackParam>;

const SignUpStackNavigator = () => (
  <SignUpStack.Navigator
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
    <SignUpStack.Screen
      name={'SnsLoginScreen'}
      component={SnsLoginScreen}
    />
    <SignUpStack.Screen
      name={'SignNicknameScreen'}
      component={SignNicknameScreen}
      options={({navigation}) => {
        return {
          headerLeft: () => <HeaderBack navigate={navigation.goBack} />,
        };
      }}
    />
    <SignUpStack.Screen
      name={'SignBirthScreen'}
      component={SignBirthScreen}
      options={({navigation}) => {
        return {
          headerLeft: () => <HeaderBack navigate={navigation.goBack} />,
        };
      }}
    />
    <SignUpStack.Screen
      name={'SignJobScreen'}
      component={SignJobScreen}
      options={({navigation}) => {
        return {
          headerLeft: () => <HeaderBack navigate={navigation.goBack} />,
        };
      }}
    />
  </SignUpStack.Navigator>
);

export default SignUpStackNavigator;
