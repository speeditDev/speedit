import * as React from 'react';
import styled from 'styled-components/native';
import {RootStackNavigationProps} from '../../navigation/RootStackNavigator';
import {Image, TouchableOpacity, Dimensions} from 'react-native';
import {
  KakaoOAuthToken,
  KakaoProfile,
  getProfile as getKakaoProfile,
  login,
  logout,
  unlink,
} from '@react-native-seoul/kakao-login';
import {
  GoogleSignin,
  GoogleSigninButton,
  NativeModuleError,
  statusCodes,
} from '@react-native-google-signin/google-signin';

const {width: screenWidth} = Dimensions.get('screen');
const BUTTON_WIDTH_SIZE = screenWidth * 0.7;

// 카카오 디자인 가이드 정보
// https://developers.kakao.com/docs/latest/ko/reference/design-guide
const KAKAO_COLOR = '#FEE500';
const KAKAO_RADIUS = 12;

const View = styled.View`
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const Text = styled.Text`
  font-size: 16px;
  color: ${props => props.theme.textColor.main};
`;

const Button = styled.TouchableOpacity`
  width: 50%;
  margin-vertical: 10px;
  padding-vertical: 10px;
  align-items: center;
  background-color: #f4a261;
  border-radius: 6px;
`;

const SnsLoginScreen = ({navigation}: RootStackNavigationProps<'SnsLoginScreen'>) => {
  const onKakaoLogin = async () => {
    const kakaoToken = await login();
    const info = await getKakaoProfile();
    console.log(JSON.stringify(info));
    // info 안에 있는 정보들중 일부를 전역값으로 저장하고 있다가, 계정생성시 넘겨줘야함 ( 진석님 )
    navigation.navigate('SignUpScreen');
  };

  const onGoogleLogin = async () => {
    GoogleSignin.configure({
      offlineAccess: false,
    });

    const isSignedIn = await GoogleSignin.isSignedIn();
    if (isSignedIn) {
      await GoogleSignin.revokeAccess();
    }
    const info = await GoogleSignin.signIn();
    console.log(JSON.stringify(info));
    navigation.navigate('SignUpScreen');
  };

  return (
    <View>
      <Text>간편로그인</Text>
      <Button onPress={() => navigation.navigate('SignUpScreen')}>
        <Text>로그인하기</Text>
      </Button>

      <TouchableOpacity
        onPress={() => onKakaoLogin()}
        style={{
          alignItems: 'center',
          width: BUTTON_WIDTH_SIZE,
          backgroundColor: KAKAO_COLOR,
          borderRadius: KAKAO_RADIUS,
        }}>
        <Image source={require('../../images/kakao_login_medium_wide.png')} width={300} height={45} />
      </TouchableOpacity>

      <GoogleSigninButton
        onPress={() => onGoogleLogin()}
        size={GoogleSigninButton.Size.Standard}
        color={GoogleSigninButton.Color.Dark}
        style={{
          marginTop: 10,
        }}
      />

      <Button onPress={() => navigation.navigate('OcrSampleStackScreen')}>
        <Text>Ocr 임시 테스트</Text>
      </Button>
    </View>
  );
};

export default SnsLoginScreen;
