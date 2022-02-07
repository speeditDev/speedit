import * as React from 'react';
import {createNativeStackNavigator, NativeStackNavigationProp} from '@react-navigation/native-stack';
import {RouteProp} from '@react-navigation/native';
import SnsLoginScreen from '../screens/snsLogin/SnsLoginScreen';
import SignUpScreen from '../screens/singUp/SignUpScreen';
import BottomGnbStackNavigator from './BottomGnbStackNavigator';
import OcrSampleStackNavigator from '../temp/ocr/OcrSampleStackNavigator';
import KakaoLoginSample from '../temp/kakao/KakaoLogin';
import BookDetailScreen from '../screens/bottomGnb/search/BookDetailScreen';
import {Platform} from 'react-native';
import {TempBookInfoProps} from '../types/typed';
import SplashScreen from '../screens/intro/SplashScreen';

// root 스택에서 갈 수 있는 화면들
// - 스플래쉬 // todo : 각 네이티브에서 할지, RN 영역에서 할지
// - 간편로그인
// - 회원가입
// - GNB ( 해당 경로 진입 시는 이전 stack 을 reset 할 예정 )
// - 설정

// 사용할 수 있는 화면을 지정해서 사용하기 위함
// 네이밍은 추가하고 싶은 화면이 단일 화면이면 ~~Screen,
// 네이밍은 추가하고 싶은 화면이 스택으로 쌓여있는 화면이면 ~~StackScreen,
type RootStackParam = {
  SplashScreen: undefined;

  SnsLoginScreen: undefined;
  SignUpScreen: undefined;
  BottomGnbStackScreen: undefined;
  SettingStackScreen: undefined;

  // 도서상세 정보 ( 탭보다 위에 나와야해서 Root 에 선언 )
  BookDetailScreen: {bookDetail: TempBookInfoProps};

  // ocr 테스트 임시
  OcrSampleStackScreen: undefined;

  // kakao 로그인 테스트 임시
  KakaoLoginSample: undefined;
};

const RootStack = createNativeStackNavigator<RootStackParam>();

// js 와는 다르게, 해당하는 화면의 navigation 값과 route 값을 파악하기 위한 interface 설정
export interface RootStackNavigationProps<RouteName extends keyof RootStackParam> {
  navigation: NativeStackNavigationProp<RootStackParam, RouteName>;
  route: RouteProp<RootStackParam, RouteName>;
}

export type RootStackNavigationType = NativeStackNavigationProp<RootStackParam>;

const RootStackNavigator = () => (
  <RootStack.Navigator
    initialRouteName={'SplashScreen'}
    screenOptions={{presentation: 'card', headerShown: false, animation: 'slide_from_right'}}>
    <RootStack.Screen name={'SplashScreen'} component={SplashScreen} />

    <RootStack.Group screenOptions={{presentation: 'card', headerShown: false, animation: 'fade'}}>
      <RootStack.Screen name={'SnsLoginScreen'} component={SnsLoginScreen} />
    </RootStack.Group>

    <RootStack.Screen name={'SignUpScreen'} component={SignUpScreen} />

    <RootStack.Group screenOptions={{presentation: 'card', headerShown: false, animation: 'fade'}}>
      <RootStack.Screen name={'BottomGnbStackScreen'} component={BottomGnbStackNavigator} />
    </RootStack.Group>

    <RootStack.Group
      screenOptions={{
        headerShown: false,
        presentation: 'modal',
        animation: Platform.OS === 'android' ? 'fade_from_bottom' : 'default',
      }}>
      <RootStack.Screen name={'BookDetailScreen'} component={BookDetailScreen} />
    </RootStack.Group>

    {/* 임시화면, todo: remove */}
    <RootStack.Screen name={'OcrSampleStackScreen'} component={OcrSampleStackNavigator} />

    {/*임시화면*/}
    <RootStack.Screen name={'KakaoLoginSample'} component={KakaoLoginSample} />
  </RootStack.Navigator>
);

export default RootStackNavigator;
